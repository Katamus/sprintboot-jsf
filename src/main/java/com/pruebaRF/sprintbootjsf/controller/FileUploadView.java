package com.pruebaRF.sprintbootjsf.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean
public class FileUploadView {
	
	private UploadedFile file;
	 
	private List<String[]> cargaArchivo;
	
    public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }
     
    public void upload() {
        if(file != null) {
        	this.readExcelFileToArray(file);
            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
     
    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    
    public void readExcelFileToArray(UploadedFile file){
    	List<String[]> arrayDatos = new ArrayList<String[]>();
    	InputStream excelStream = null;
    	try {
    		excelStream = file.getInputstream();
    		XSSFWorkbook  hssfWorkbook = new XSSFWorkbook(excelStream);
    		XSSFSheet xssfSheet = hssfWorkbook.getSheetAt(0);
    		XSSFRow xssfrow = xssfSheet.getRow(xssfSheet.getTopRow());
    		String [] datos = new String[xssfrow.getLastCellNum()];
    		for (Row row: xssfSheet) {                    
    		    for (Cell cell : row) {
    		        datos[cell.getColumnIndex()] =  
    		                (cell.getCellType() == CellType.STRING )?cell.getStringCellValue():
    		                (cell.getCellType() == CellType.NUMERIC)?"" + cell.getNumericCellValue():
    		                (cell.getCellType() == CellType.BOOLEAN)?"" + cell.getBooleanCellValue():
    		                (cell.getCellType() == CellType.BLANK)?"BLANK":
    		                (cell.getCellType() == CellType.FORMULA)?"FORMULA":
    		                (cell.getCellType() == CellType.ERROR)?"ERROR":"";
    		    }
    		    arrayDatos.add(datos); 
    		    datos = new String[xssfrow.getLastCellNum()];  
            }
    		
			
		} catch (FileNotFoundException fileNotFoundException) {
			// TODO: handle exception
		}catch (IOException ex) {
            System.out.println("Error in file procesing (Error al procesar el fichero): " + ex);
        } finally {
            try {
                excelStream.close();
            } catch (IOException ex) {
                System.out.println("Error in file processing after close it (Error al procesar el fichero después de cerrarlo): " + ex);
            }
        }
    	this.setCargaArchivo(arrayDatos);
    }

	public List<String[]> getCargaArchivo() {
		return cargaArchivo;
	}

	public void setCargaArchivo(List<String[]> cargaArchivo) {
		this.cargaArchivo = cargaArchivo;
	}
    
    
	
}
