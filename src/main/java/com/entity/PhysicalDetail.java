package com.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PhysicalDetail {

	private double weight;
	private LocalDate recordedDate;
	private LocalDateTime createdAt;
	private int userId;

}
