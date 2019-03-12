package br.com.itau.investimentocliente.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.com.itau.investimentocliente.models.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Integer> {
	
	public Optional<Cliente> findByCpf(String cpf);
}
