package com.bank.account.mapper;

import com.bank.account.dto.CustomerDTO;
import com.bank.account.entity.CustomerEntity;

public class CustomerMapper {

	private CustomerMapper() {
		
	}
	public static CustomerDTO mapToCustomerDto(CustomerEntity customer, CustomerDTO customerDto) {
        customerDto.setName(customer.getCustomerName());
        customerDto.setEmail(customer.getCustomerEmail());
        customerDto.setMobileNumber(customer.getMobileNumber());
        return customerDto;
    }

    public static CustomerEntity mapToCustomerEntity(CustomerDTO customerDto, CustomerEntity customer) {
        customer.setCustomerName(customerDto.getName());
        customer.setCustomerEmail(customerDto.getEmail());
        customer.setMobileNumber(customerDto.getMobileNumber());
        return customer;
    }
}
