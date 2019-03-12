package br.com.itau.investimentocliente.services;

import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.itau.investimentocliente.models.Cliente;
import br.com.itau.investimentocliente.repositories.ClienteRepository;

@Service
public class ClienteService {
	@Autowired
	ClienteRepository clienteRepository;
	
	public Cliente cadastrar(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	public Optional<Cliente> buscar(String cpf){
		return clienteRepository.findByCpf(cpf);
	}
	
	@PostConstruct
	public void inicializarBase() {
		Cliente cliente = new Cliente();
		
		cliente.setCpf("123.123.123-12");
		cliente.setNome("José Silva");
		cliente.setEmail("jose@gmail.com");
		
		clienteRepository.save(cliente);
	}
}
