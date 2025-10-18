package api.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtility {

	public FileInputStream fis;
	public FileOutputStream fos;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle cellStyle;
	String path;
	
	public XLUtility(String path) {
		this.path = path;
	}
	
	public int getRowCount(String sheetname) throws IOException {
		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetname);
		int rowCount = sheet.getLastRowNum();
		workbook.close();
		fis.close();
		return rowCount;
	}
	
	public int getCellCount(String sheetName, int rownum) throws Exception {
		
		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		int cellNum = row.getLastCellNum();
		workbook.close();
		fis.close();
		return cellNum;
		
	}
	
	public String getCellData(String sheetName, int rownum, int colnum ) throws Exception {
		
		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		cell =row.getCell(colnum);
		
		DataFormatter dataFormatter = new DataFormatter();
		String data;
		try {
			data = dataFormatter.formatCellValue(cell);
		} catch (Exception e) {
			data ="";
		}	
		workbook.close();
		fis.close();
		
		return data;	
	} 
	
	public void setCellData(String sheetName, int rownum, int colnum, String data) throws Exception {
		
		File xlfile = new File(path);
		if(!xlfile.exists()) {
			workbook = new XSSFWorkbook();
			fos = new FileOutputStream(path);
			workbook.write(fos);
		}
		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
		if(workbook.getSheetIndex(sheetName)== -1) 
			workbook.createSheet(sheetName);
		sheet = workbook.getSheet(sheetName);

		if(sheet.getRow(rownum)==null) 
			sheet.createRow(rownum);
		row = sheet.getRow(rownum);
		cell = row.createCell(colnum);
		cell.setCellValue(data);
		fos=new FileOutputStream(path);
		workbook.write(fos);
		workbook.close();
		fos.close();
		fis.close(); 
		
		
	}
	
}
