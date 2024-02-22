package com.bank.account.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.account.entity.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

	Optional<CustomerEntity> findByMobileNumber(String mobileNumber);

}
