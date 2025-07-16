package com.province.taxappservice.service;

import com.province.taxappservice.model.*;
import com.province.taxappservice.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class InspectionValidationService {

    private final InspectionRepository inspectionRepository;
    private final NoteRepository noteRepository;
    private final PaiementRepository paiementRepository;
    private final NotificationRepository notificationRepository;
    @Scheduled(fixedRate = 60000)
    public void verifierPaiementsDesInspections() {
        log.info("🔁 Vérification des notes expirées...");

        List<Inspection> inspections = inspectionRepository.findAll();

        for (Inspection inspection : inspections) {
            Optional<Note> noteOpt = noteRepository.findByInspection(inspection);

            if (noteOpt.isEmpty()) {
                log.warn("🚫 Aucune note liée à l'inspection {}", inspection.getIdinspection());
                continue;
            }

            Note note = noteOpt.get();

            // Vérifier que la note a une date d'expiration et qu'elle est dépassée
            if (note.getExpiration() == null || !LocalDate.now().isAfter(note.getExpiration())) {
                log.debug("📅 La note {} n’est pas encore expirée ou n’a pas de date d’expiration", note.getIdnote());
                continue;
            }

            // Vérifier s'il y a un paiement valide pour cette note
            Optional<Paiement> paiementOpt = paiementRepository.findByNote(note);
            boolean paiementValide = paiementOpt
                    .map(Paiement::getTransaction)
                    .map(Transaction::isValide)
                    .orElse(false);

            if (paiementValide) {
                log.info("✅ Paiement valide trouvé pour la note expirée {}", note.getIdnote());
                continue;
            }

            if (inspection.getContribuable() == null) {
                log.warn("❌ Inspection {} sans contribuable associé", inspection.getIdinspection());
                continue;
            }

            // Sinon : créer une notification
            log.warn("❌ Note expirée sans paiement valide : {}", note.getIdnote());
            if(!note.isExpired()){
                note.setExpired(true);
                noteRepository.save(note);
            }
            Notifications exist = notificationRepository.findByNoteAndRecipient(note,inspection.getContribuable());
            if (exist == null) {
                Notifications notification = new Notifications();
                notification.setTitle("Note fiscale expirée");
                notification.setBody("Votre note fiscale du " + note.getDate() +
                        " liée à l'inspection du " + inspection.getDate() +
                        " a expiré sans paiement valide. Veuillez régulariser votre situation.");
                notification.setRecipient(inspection.getContribuable());
                notification.setNote(note);
                notification.setDateTime(LocalDateTime.now());
                notification.setMsgRead(false);
                notificationRepository.save(notification);
            }
        }
    }

}