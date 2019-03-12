package br.com.itau.investimentocliente.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.itau.investimentocliente.models.Cliente;
import br.com.itau.investimentocliente.services.ClienteService;

@RestController
@RequestMapping("/")
public class ClienteController {
	@Autowired
	ClienteService clienteService;

	@GetMapping("/{cpf}")
	public ResponseEntity buscar(@PathVariable String cpf) {
		Optional<Cliente> clienteOptional = clienteService.buscar(cpf);
		
		if(clienteOptional.isPresent()) {
			return ResponseEntity.ok(clienteOptional.get());
		}
		
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping
	public ResponseEntity criar(@RequestBody Cliente cliente){
		cliente = clienteService.cadastrar(cliente);
		
		return ResponseEntity.status(201).body(cliente);
	}
}
