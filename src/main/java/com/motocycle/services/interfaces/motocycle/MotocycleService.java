package com.motocycle.services.interfaces.motocycle;

import com.motocycle.dtos.MotocycleDTO;
import com.motocycle.entities.Response;

public interface MotocycleService {
	public Response findAll();
	public Response findById(Long id);
	public Response save(MotocycleDTO motocycle);
	public Response delete(Long id);
	public Response update(Long id, MotocycleDTO motocycleDTO);
}
