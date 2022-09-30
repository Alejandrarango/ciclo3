package com.motocycle.services.impl.motocycle;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.motocycle.dtos.MotocycleDTO;
import com.motocycle.entities.Motocycle;
import com.motocycle.entities.Response;
import com.motocycle.repositories.MotocycleRepository;
import com.motocycle.services.interfaces.motocycle.MotocycleService;

@Service
public class MotocycleImpl implements MotocycleService{
	
	@Autowired
	private MotocycleRepository motocycleRepo;
	
	@Override
	public Response findAll() {
		
		List<Motocycle> listOfRecoveredMotocycles = motocycleRepo.findAll();
		
		if(listOfRecoveredMotocycles.isEmpty()) {
			return formatResponse("There are not motocycles", 404);
		}
		
		return formatResponse("OK", 200, listOfRecoveredMotocycles);
	}

	@Override
	public Response findById(Long id) {

		Optional<Motocycle> recoveredMotocycle = motocycleRepo.findById(id);
		
		if(recoveredMotocycle.isEmpty()) {
			String message = "There is not a motocycle with the id provided" + id + "in the path";
			return formatResponse(message, 404);
		}
		
		return formatResponse("OK", 200, recoveredMotocycle.get());
	}

	@Override
	public Response save(MotocycleDTO motocycle) {
		
		if(motocycle.getBrand().isEmpty() || 
				motocycle.getDescription().isEmpty() || 
				motocycle.getName().isEmpty() || motocycle.getYear() == 0) {
			return formatResponse("All fields are necessary", 400);
		}
		Motocycle newMotocycle = new Motocycle();
		newMotocycle.setBrand(motocycle.getBrand());
		newMotocycle.setDescription(motocycle.getDescription());
		newMotocycle.setName(motocycle.getName());
		newMotocycle.setYear(motocycle.getYear());
		
		Motocycle savedMotocycle =  motocycleRepo.save(newMotocycle);
		motocycle.setId(savedMotocycle.getId());
		if(savedMotocycle.getId() == 0) formatResponse("There was an error saving the model of the new motocycle", 500);
		return formatResponse("OK", 200, motocycle);
	}

	@Override
	public Response delete(Long id) {
		
		Optional<Motocycle> motocycleToRemove = motocycleRepo.findById(id);
		if(motocycleToRemove.isEmpty()) return formatResponse("There is not a motocycle with id provided in the request", 400);
		motocycleRepo.deleteById(id);
		
		return formatResponse("OK", 200);
	}
	
	
	public Response formatResponse(String message, int status, Object result) {
		Response response = new Response();
		response.setMessage(message);
		response.setResult(result);
		response.setStatus(status);
		
		return response;
	}
	public Response formatResponse(String message, int status) {
		Response response = new Response();
		response.setMessage(message);
		response.setStatus(status);
		
		return response;
	}

	@Override
	public Response update(Long id, MotocycleDTO motocycleDTO) {
		
		if(motocycleDTO.getBrand().isEmpty() 
				|| motocycleDTO.getDescription().isEmpty()
				|| id == 0
				|| motocycleDTO.getName().isEmpty()
				|| motocycleDTO.getYear() == 0) {
			return formatResponse("All fields are neccessary", 500);
		}
		Motocycle newMotocycle = new Motocycle();
		newMotocycle.setBrand(motocycleDTO.getBrand());
		newMotocycle.setDescription(motocycleDTO.getDescription());
		newMotocycle.setId(id);
		newMotocycle.setName(motocycleDTO.getName());
		newMotocycle.setYear(motocycleDTO.getYear());
		
		
		return  formatResponse("Ok", 200, motocycleRepo.save(newMotocycle));
	}
	
}
