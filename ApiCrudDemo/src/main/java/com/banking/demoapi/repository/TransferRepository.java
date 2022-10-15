package com.banking.demoapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banking.demoapi.entity.TransferEntity;

@Repository
public interface TransferRepository extends JpaRepository<TransferEntity, Long> {

	List<TransferEntity> findByFromAccountEquals(String fromAccount);

}
