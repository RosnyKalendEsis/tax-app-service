package com.province.taxappservice.controller;

import com.province.taxappservice.model.Paiement;
import com.province.taxappservice.repository.NoteRepository;
import com.province.taxappservice.repository.PaiementRepository;
import com.province.taxappservice.repository.TransactionRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

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
}
