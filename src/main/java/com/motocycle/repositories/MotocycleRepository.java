package com.motocycle.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.motocycle.entities.Motocycle;

@Repository
public interface MotocycleRepository extends JpaRepository<Motocycle, Long>{	
}
