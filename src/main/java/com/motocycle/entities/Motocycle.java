package com.motocycle.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Motocycle {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "motocycleId")
	private Long id;
	@Column(name= "brand")
	private String brand;
	@Column(name= "name")
	private String name;
	@Column(name= "year")
	private int year;
	@Column(name= "description")
	private String description;
}
