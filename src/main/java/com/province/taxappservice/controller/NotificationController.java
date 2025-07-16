package com.province.taxappservice.controller;

import com.province.taxappservice.model.Contribuable;
import com.province.taxappservice.model.Notifications;
import com.province.taxappservice.model.Notifications;
import com.province.taxappservice.service.ContribuableService;
import com.province.taxappservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;
    private final ContribuableService contribuableService;

    @PostMapping
    public ResponseEntity<Notifications> create(@RequestBody Notifications notification) {
        return ResponseEntity.ok(notificationService.save(notification));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notifications> getById(@PathVariable String id) {
        Notifications notification = notificationService.findById(id);
        return notification != null ? ResponseEntity.ok(notification) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Notifications>> getAll() {
        return ResponseEntity.ok(notificationService.findAll());
    }

    @GetMapping("/recipient/{recipient}")
    public ResponseEntity<List<Notifications>> getByRecipient(@PathVariable String recipient) {
        Contribuable contribuable = contribuableService.getById(recipient);
        if (contribuable == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(notificationService.findAllByRecipient(contribuable));
    }

    @GetMapping("/read/{read}")
    public ResponseEntity<List<Notifications>> getByReadStatus(@PathVariable boolean read) {
        return ResponseEntity.ok(notificationService.findAllByRead(read));
    }

    @GetMapping("/recipient/{recipient}/read/{read}")
    public ResponseEntity<List<Notifications>> getByRecipientAndRead(@PathVariable String recipient, @PathVariable boolean read) {
        Contribuable contribuable = contribuableService.getById(recipient);
        if (contribuable == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(notificationService.findAllByRecipientAndRead(contribuable, read));
    }

    @PutMapping("/{id}/read")
    public ResponseEntity<Notifications> markAsRead(@PathVariable String id) {
        Notifications updated = notificationService.markAsRead(id);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        notificationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
