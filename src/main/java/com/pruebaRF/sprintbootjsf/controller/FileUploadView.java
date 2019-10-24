package com.pruebaRF.sprintbootjsf.controller;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;

import com.pruebaRF.sprintbootjsf.service.LeerArchivoService;

/**
 * 
 * @author <a href="mailto:Christian.Catamuscay.h@gmail.com">Christian Eduardo Catamuscay Hurtado</a>
 * @project sprintboot-jsf
 * @class FileUploadView 
 * @date 24/10/2019
 */
@ManagedBean
public class FileUploadView {
	
	
	private UploadedFile file;
	 
	private List<String[]> cargaArchivo;
	
	@Autowired
	private LeerArchivoService leerArchivoService;
	
	/**
	 * 
	 * @author <a href="mailto:Christian.Catamuscay.h@gmail.com">Christian Eduardo Catamuscay Hurtado</a> 
	 * @date 24/10/2019
	 * @return UploadedFile
	 */
    public UploadedFile getFile() {
        return file;
    }
 
    /**
     * 
     * @author <a href="mailto:Christian.Catamuscay.h@gmail.com">Christian Eduardo Catamuscay Hurtado</a> 
     * @date 24/10/2019 
     * @param UploadedFile file
     */
    public void setFile(UploadedFile file) {
        this.file = file;
    }
    
    /**
     * 
     * @author <a href="mailto:Christian.Catamuscay.h@gmail.com">Christian Eduardo Catamuscay Hurtado</a> 
     * @date 24/10/2019
     */
    public void upload() {
    	if(file != null) {
    		
    		if(file.getFileName().endsWith(".xlsx")) {
	        	this.readExcelFileToArray(file);
	            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
	            FacesContext.getCurrentInstance().addMessage(null, message);
    		}else {
    			FacesMessage message = new FacesMessage("Error", file.getFileName() + " is not an excel file");
	            FacesContext.getCurrentInstance().addMessage(null, message);
    		}
        }
    }
    
    /**
     * 
     * @author <a href="mailto:Christian.Catamuscay.h@gmail.com">Christian Eduardo Catamuscay Hurtado</a> 
     * @date 24/10/2019
     * @param FileUploadEvent event
     */
    public void handleFileUpload(FileUploadEvent event) {
    	this.readExcelFileToArray(file);
        FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    /**
     * 
     * @author <a href="mailto:Christian.Catamuscay.h@gmail.com">Christian Eduardo Catamuscay Hurtado</a> 
     * @date 24/10/2019 
     * @param file
     */
    public void readExcelFileToArray(UploadedFile file){
    	this.setCargaArchivo(leerArchivoService.leerArchivo(file));
    }

    /**
     * 
     * @author <a href="mailto:Christian.Catamuscay.h@gmail.com">Christian Eduardo Catamuscay Hurtado</a> 
     * @date 24/10/2019 
     * @return List<String[]>
     */
	public List<String[]> getCargaArchivo() {
		return cargaArchivo;
	}

	/**
	 * 
	 * @author <a href="mailto:Christian.Catamuscay.h@gmail.com">Christian Eduardo Catamuscay Hurtado</a> 
	 * @date 24/10/2019 
	 * @param List<String[]> cargaArchivo
	 */
	public void setCargaArchivo(List<String[]> cargaArchivo) {
		this.cargaArchivo = cargaArchivo;
	}
    
}
