package com.fotorest.foto;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FotoService {
	@Autowired
	FotoRepository fotoRepository;
	
	public Foto inserir(Foto foto) {
		foto.setTimestamp(LocalDateTime.now());
		
		return fotoRepository.save(foto);
	}
	
	public Iterable<Foto> buscarTodas(){
		return fotoRepository.findAll();
	}
}
