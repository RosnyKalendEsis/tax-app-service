package com.province.taxappservice.repository;

import com.province.taxappservice.model.Contribuable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContribuableRepository extends JpaRepository<Contribuable, String> {
}
