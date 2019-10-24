package com.pruebaRF.sprintbootjsf.serviceImpl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.model.UploadedFile;
import org.springframework.stereotype.Service;

import com.pruebaRF.sprintbootjsf.service.LeerArchivoService;

/**
 * 
 * @author <a href="mailto:Christian.Catamuscay.h@gmail.com">Christian Eduardo Catamuscay Hurtado</a>
 * @project sprintboot-jsf
 * @class LeerArchivoServiceImpl
 * @date 24/10/2019
 */
@Service
public class LeerArchivoServiceImpl implements LeerArchivoService {

	/**
	 * 
	 * @author <a href="mailto:Christian.Catamuscay.h@gmail.com">Christian Eduardo Catamuscay Hurtado</a>
	 * @date 24/10/2019 
	 * @param file
	 * @return 
	 * @see com.pruebaRF.sprintbootjsf.service.LeerArchivoService#leerArchivo(org.primefaces.model.UploadedFile)
	 */
	@Override
	public List<String[]> leerArchivo(UploadedFile file) {
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
    	return arrayDatos;
	}

	

}
