package com.form;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.format.annotation.DateTimeFormat;

import com.validation.First;
import com.validation.Second;
import com.validation.Third;

import lombok.Data;

@Data
public class PhysicalDetailForm {

	@NotNull(groups=First.class)
	@Digits(integer = 3, fraction = 1, groups=Second.class)
	@Positive(groups=Third.class)
	private double weight;
	
	@NotNull(groups=First.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate recordedDate;
	
	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime createdAt;
	
	private int userId;

}
