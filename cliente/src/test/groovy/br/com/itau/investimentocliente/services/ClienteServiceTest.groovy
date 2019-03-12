package br.com.itau.investimentocliente.services

import spock.lang.Specification
import br.com.itau.investimentocliente.models.Cliente
import br.com.itau.investimentocliente.repositories.ClienteRepository
import br.com.itau.investimentocliente.services.ClienteService

class ClienteServiceTest extends Specification {
	
	ClienteService clienteService;
	ClienteRepository clienteRepository;
	
	def setup() {
		
		clienteService = new ClienteService();
		clienteRepository = Mock();
		
		clienteService.clienteRepository = clienteRepository;
		
	}
	
	def 'deve salvar um cliente'() {
		given: 'Os dados de um cliente são informados'
		
		Cliente cliente = new Cliente();
		cliente.setCpf("12345678909");
		cliente.setNome("Claudio Alves Jr");
		
		when: 'Cliente é salvo'
		def clienteSalvo = clienteService.cadastrar(cliente);
		
		then: 'Retorna um cliente'
		1 * clienteRepository.save(_) >> cliente;
		clienteSalvo != null;
	}
	
	def 'deve buscar um cliente pelo CPF'() {
		given: 'Os dados de um cliente existem na base'
		
		String cpf = "12345678909";
		
		Cliente cliente = new Cliente();
		cliente.setCpf(cpf);
		cliente.setNome("Claudio Alves Jr");
		def clienteOptional = Optional.of(cliente);
		
		when: 'Cliente é pesquisado a partir do CPF informado'
		def clientePesquisado = clienteService.buscar("12345678900");
		
		then: 'Retorna um cliente a partir da pesquisa'
		1 * clienteRepository.findByCpf(_) >> clienteOptional;
		clientePesquisado.isPresent() == true;
	}
	
}
