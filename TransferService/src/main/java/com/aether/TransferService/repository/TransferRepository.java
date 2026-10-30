package com.aether.TransferService.repository;

import com.aether.TransferService.model.TransferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepository extends JpaRepository<TransferEntity,String> {
}
