package com.province.taxappservice.repository;

import com.province.taxappservice.model.Contribuable;
import com.province.taxappservice.model.Note;
import com.province.taxappservice.model.Notifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notifications, String> {
    List<Notifications> findAllByRecipient(Contribuable recipient);
    List<Notifications> findAllByMsgRead(boolean read);
    List<Notifications> findAllByRecipientAndMsgRead(Contribuable recipient, boolean read);
    Notifications findByNoteAndRecipient(Note note, Contribuable recipient);
}
