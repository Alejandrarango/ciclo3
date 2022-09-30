package com.motocycle.dtos;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MotocycleDTO {
	
	private Long id;
	private String brand;
	private String name;
	private int year;
	private String description;
}
