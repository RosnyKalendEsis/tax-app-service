package com.province.taxappservice.repository;

import com.province.taxappservice.model.Inspecteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InspecteurRepository extends JpaRepository<Inspecteur, String> {
}

