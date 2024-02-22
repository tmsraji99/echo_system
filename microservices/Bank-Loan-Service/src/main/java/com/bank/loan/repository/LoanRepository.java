package com.bank.loan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.loan.entity.LoanEntity;

public interface LoanRepository extends JpaRepository<LoanEntity, Long> {

}
