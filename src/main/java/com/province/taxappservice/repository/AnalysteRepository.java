package com.province.taxappservice.repository;

import com.province.taxappservice.model.Analyste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalysteRepository extends JpaRepository<Analyste, String> {
}
