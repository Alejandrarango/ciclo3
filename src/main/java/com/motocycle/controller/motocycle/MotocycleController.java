package com.motocycle.controller.motocycle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.motocycle.dtos.MotocycleDTO;
import com.motocycle.entities.Response;
import com.motocycle.services.interfaces.motocycle.MotocycleService;

@Controller
@RequestMapping("/api/Motorbike")
public class MotocycleController {
	
	@Autowired
	private MotocycleService motocycleService;

	public MotocycleController(MotocycleService motocycleService) {
		this.motocycleService = motocycleService;
	}
	
	@PostMapping()
	public ResponseEntity<Response> save(@RequestBody MotocycleDTO motocycleDto){
		Response response = motocycleService.save(motocycleDto);
		int status = response.getStatus();
		if(status==400) {
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}
	
	@GetMapping
	public ResponseEntity<Response> readAll(){
		return ResponseEntity.ok(motocycleService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Response> readById(@PathVariable(name="id") Long id){
		return ResponseEntity.ok(motocycleService.findById(id));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Response> delete(@PathVariable(name="id") Long id ) {
		Response response = motocycleService.delete(id);
		if(response.getStatus() == 400) {
			return ResponseEntity.internalServerError().body(response);
		}
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Response> update(@PathVariable(name="id") Long id, @RequestBody MotocycleDTO motocycleDto){
		Response response = motocycleService.update(id, motocycleDto);
		if(response.getStatus() == 500) {
			ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}
}
