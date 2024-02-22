package com.bank.loan.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass

public class BaseEntity {

	@Column(name = "created_time", insertable = true, updatable = false)
	protected LocalDateTime creationDate;
	@Column(name = "created_by")
	protected String createdBy;
	@Column(name = "updated_time", insertable = false, updatable = true)
	protected LocalDateTime updatedTime;
	@Column(name = "updated_by")
	protected String updatedBy;

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(LocalDateTime updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public BaseEntity(LocalDateTime creationDate, String createdBy, LocalDateTime updatedTime, String updatedBy) {

		this.creationDate = creationDate;
		this.createdBy = createdBy;
		this.updatedTime = updatedTime;
		this.updatedBy = updatedBy;
	}

	public BaseEntity() {

	}

}
