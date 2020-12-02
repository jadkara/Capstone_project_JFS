package com.wipro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.wipro.model.Batch;

@Repository
public interface BatchRepository extends JpaRepository<Batch, String>, JpaSpecificationExecutor<Batch> {

}
