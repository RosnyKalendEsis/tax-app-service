package com.province.taxappservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Notifications {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String title;
    private String body;
    private LocalDateTime dateTime;
    private boolean msgRead;
    @ManyToOne
    private Contribuable recipient;
    @ManyToOne
    private Note note;
}
