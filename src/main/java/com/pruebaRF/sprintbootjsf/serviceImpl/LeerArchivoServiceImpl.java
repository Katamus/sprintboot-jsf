package com.pruebaRF.sprintbootjsf.serviceImpl;

import org.springframework.stereotype.Service;

import com.pruebaRF.sprintbootjsf.service.LeerArchivoService;

@Service
public class LeerArchivoServiceImpl implements LeerArchivoService {

	@Override
	public String leerArchivo(String titulo) {
		// TODO Auto-generated method stub
		System.out.println(titulo);
		return "este es el titulo del archivo: "+titulo;
	}

	
}
