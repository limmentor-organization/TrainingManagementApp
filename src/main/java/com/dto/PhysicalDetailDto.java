package com.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PhysicalDetailDto {
	
	private double weight;
	private LocalDate recordedDate;
	private LocalDateTime createdAt;
	private int userId;
	
}
