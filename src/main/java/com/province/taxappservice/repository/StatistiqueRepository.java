package com.province.taxappservice.repository;

import com.province.taxappservice.model.Statistique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatistiqueRepository extends JpaRepository<Statistique,String> {
}
