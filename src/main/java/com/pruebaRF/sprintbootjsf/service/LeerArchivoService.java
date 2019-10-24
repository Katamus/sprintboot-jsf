package com.pruebaRF.sprintbootjsf.service;

import java.util.List;

import org.primefaces.model.UploadedFile;

/**
 * 
 * @author <a href="mailto:Christian.Catamuscay.h@gmail.com">Christian Eduardo Catamuscay Hurtado</a>
 * @project sprintboot-jsf
 * @class LeerArchivoService 
 * @date 24/10/2019
 */
public interface LeerArchivoService {

	/**
	 * 
	 * @author <a href="mailto:Christian.Catamuscay.h@gmail.com">Christian Eduardo Catamuscay Hurtado</a> 
	 * @date 24/10/2019
	 * @param file
	 * @return List<String[]>
	 */
	public List<String[]> leerArchivo(UploadedFile file);
	
}
