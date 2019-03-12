package br.com.itau.investimentocliente.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc

import br.com.itau.investimentocliente.App
import br.com.itau.investimentocliente.models.Cliente
import br.com.itau.investimentocliente.repositories.ClienteRepository
import br.com.itau.investimentocliente.services.ClienteService
import spock.lang.Specification
import spock.lang.Stepwise
import spock.mock.DetachedMockFactory

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@SpringBootTest(classes = App)
@Stepwise
class ClienteControllerTest extends Specification {
	
	@Autowired
	ClienteController clienteController;
	
	@Autowired
	ClienteService clienteService;
	
	@Autowired
	ClienteRepository clienteRepository;
	
	/*
	@TestConfiguration
	static class MockConfig {
		def factory = DetachedMockFactory();
		
		@Bean
		ClienteService clienteService() {
			return factory.Mock(ClienteService);
		}
		
		@Bean
		ClienteRepository clienteRepository() {
			return factory.Mock(ClienteRepository);
		}
		
	}
	*/
	
	def 'deve retornar status HTTP valido'() {
		given: 'Com a requisição HTTP for válida'
		String cpf = "123.123.123-11";
		
		when: 'invocamos a requisição de pesquisa'
		def resposta = clienteController.buscar(cpf);
		
		then: 'Retorna um codigo de requisição sem conteudo'
		resposta.statusCode == HttpStatus.NO_CONTENT
		!resposta.body
	}
	
	def 'deve buscar um cliente pelo CPF'() {
		given: 'Os dados de um cliente existem na base'
		
		String cpf = "123.123.123-12";
		
		when: 'Cliente é pesquisado a partir do CPF informado'
		def resposta = clienteController.buscar(cpf);
		
		then: 'Retorna um cliente a partir da pesquisa'
		resposta.statusCode == HttpStatus.OK
		resposta.body
	}
	
	def 'deve cadastrar um novo cliente'() {
		given: 'Os dados cadastrais do novo cliente'
		
		String cpf = "123.456.789-09";
		
		Cliente cliente = new Cliente();
		cliente.setCpf(cpf);
		cliente.setNome("Claudio Alves Jr");
		cliente.setEmail("claudio@gmail.com")
		def clienteOptional = Optional.of(cliente);
		
		when: 'Cliente é cadastrado a partir dos dados informados'
		def resposta = clienteController.criar(cliente);
		
		then: 'Retorna o codigo de cliente criado (201 - CREATED)'
		resposta.statusCode == HttpStatus.CREATED
		resposta.body.properties == cliente.properties
	}
}
