package com.fotorest.foto;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FotoController {
	@Autowired
	FotoService fotoService;

	@GetMapping
	public Iterable<Foto> listar(){
		return fotoService.buscarTodas();
	}
	
	@PostMapping
	public Foto inserir(@RequestBody Foto foto, Principal principal) {
		foto.setNomeUsuario(principal.getName());
		return fotoService.inserir(foto);
	}
}
