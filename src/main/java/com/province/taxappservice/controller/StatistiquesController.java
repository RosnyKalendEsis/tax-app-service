package com.province.taxappservice.controller;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.LineSeparator;
import com.province.taxappservice.model.Note;
import com.province.taxappservice.model.Paiement;
import com.province.taxappservice.repository.NoteRepository;
import com.province.taxappservice.repository.PaiementRepository;
import com.province.taxappservice.repository.TransactionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/rapports")
public class StatistiquesController {

    private final NoteRepository noteRepository;
    private final PaiementRepository paiementRepository;
    private final TransactionRepository transactionRepository;

    public StatistiquesController(NoteRepository noteRepository, PaiementRepository paiementRepository, TransactionRepository transactionRepository) {
        this.noteRepository = noteRepository;
        this.paiementRepository = paiementRepository;
        this.transactionRepository = transactionRepository;
    }

    @GetMapping("/statistiques")
    public Map<String, Object> stats() {
        Map<String, Object> data = new HashMap<>();
        data.put("totalNotes", noteRepository.count());
        data.put("totalPaiements", paiementRepository.count());
        data.put("totalTransactions", transactionRepository.count());
        data.put("montantTotalPayé", paiementRepository.findAll().stream().mapToDouble(Paiement::getMontant).sum());
        return data;
    }

    @GetMapping("/export-statistiques-pdf")
    public ResponseEntity<String> exportStatsToPdf() {
        List<Note> notes = noteRepository.findAll();

        long notesExpirees = notes.stream().filter(Note::isExpired).count();
        long totalNotes = notes.size();
        long totalPaiements = paiementRepository.count();
        long totalTransactions = transactionRepository.count();
        double montantTotal = paiementRepository.findAll().stream().mapToDouble(Paiement::getMontant).sum();

        String fileName = "statistiques-" + LocalDate.now() + ".pdf";
        String folder = "reports";
        File directory = new File(folder);
        if (!directory.exists()) directory.mkdirs();

        String path = folder + "/" + fileName;

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(path));
            document.open();

            // === EN-TÊTE ===
            Font titleFont = new Font(Font.HELVETICA, 20, Font.BOLD);
            Paragraph title = new Paragraph("📊 Rapport Statistique Global", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(new Paragraph(" "));
            document.add(new Paragraph("🕒 Généré le : " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))));
            document.add(new Paragraph(" "));

            // === SECTION STATISTIQUES ===
            Font sectionTitleFont = new Font(Font.HELVETICA, 14, Font.BOLD);
            document.add(new Paragraph("📌 Résumé :", sectionTitleFont));
            document.add(new Paragraph(" "));

            Font normalFont = new Font(Font.HELVETICA, 12);
            document.add(new Paragraph("📄 Total de notes : " + totalNotes, normalFont));
            document.add(new Paragraph("⏳ Notes expirées : " + notesExpirees, normalFont));
            document.add(new Paragraph("💳 Total de paiements : " + totalPaiements, normalFont));
            document.add(new Paragraph("💰 Montant total payé : " + String.format("%,.2f", montantTotal) + " FC", normalFont));
            document.add(new Paragraph("🔁 Total de transactions : " + totalTransactions, normalFont));

            document.add(new Paragraph(" "));
            document.add(new LineSeparator());

            // === (Optionnel) TABLEAU DES NOTES EXPIRÉES ===
            List<Note> expiredNotes = notes.stream().filter(Note::isExpired).toList();

            if (!expiredNotes.isEmpty()) {
                document.add(new Paragraph("📋 Détails des notes expirées :", sectionTitleFont));
                document.add(new Paragraph(" "));

                PdfPTable table = new PdfPTable(3); // 3 colonnes : ID, date d'expiration, état
                table.setWidthPercentage(100);
                table.setSpacingBefore(10f);

                // En-têtes
                Stream.of("Contribuable" ,"Date d'expiration", "Statut")
                        .forEach(header -> {
                            PdfPCell cell = new PdfPCell(new Phrase(header, new Font(Font.HELVETICA, 12, Font.BOLD)));
                            cell.setBackgroundColor(new Color(230, 230, 230)); // Gris clair
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                        });

                // Lignes
                for (Note n : expiredNotes) {
                    table.addCell(n.getInspection().getContribuable().getNom());
                    table.addCell(n.getExpiration().toString());
                    table.addCell("Expirée");
                }

                document.add(table);
            }

            document.add(new Paragraph(" "));
            document.add(new LineSeparator());

            // === Pied de page
            Paragraph footer = new Paragraph("Généré automatiquement par le système -  TaxApp", new Font(Font.HELVETICA, 9, Font.ITALIC));
            footer.setAlignment(Element.ALIGN_CENTER);
            document.add(footer);

            document.close();

            return ResponseEntity.ok(path);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erreur lors de la génération du PDF");
        }
    }


}
