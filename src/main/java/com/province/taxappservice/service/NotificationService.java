package com.province.taxappservice.service;

import com.province.taxappservice.model.Contribuable;
import com.province.taxappservice.model.Notifications;
import com.province.taxappservice.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public Notifications save(Notifications notification){
        return notificationRepository.save(notification);
    }

    public Notifications findById(String id){
        return notificationRepository.findById(id).orElse(null);
    }

    public List<Notifications> findAllByRecipient(Contribuable recipient){
        return notificationRepository.findAllByRecipient(recipient);
    }

    public List<Notifications> findAllByRead(boolean read){
       return notificationRepository.findAllByMsgRead(read);
    }

    public List<Notifications> findAllByRecipientAndRead(Contribuable recipient, boolean read){
        return notificationRepository.findAllByRecipientAndMsgRead(recipient, read);
    }

    public List<Notifications> findAll(){
        return notificationRepository.findAll();
    }

    public Notifications markAsRead(String id){
        Notifications notification = notificationRepository.findById(id).orElse(null);
        if(notification == null){
            return null;
        }
        notification.setMsgRead(true);
        return notificationRepository.save(notification);
    }

    public void deleteById(String id){
        notificationRepository.deleteById(id);
    }
}
