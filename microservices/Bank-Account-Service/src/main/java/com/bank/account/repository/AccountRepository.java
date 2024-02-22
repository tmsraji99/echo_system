package com.bank.account.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.account.entity.AccountEntity;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
	
	Optional<AccountEntity> findByCustomerId(Long customerId);

}
