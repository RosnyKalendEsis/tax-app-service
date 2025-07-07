package com.province.taxappservice.repository;

import com.province.taxappservice.model.Inspection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InspectionRepository extends JpaRepository<Inspection, String> {
}