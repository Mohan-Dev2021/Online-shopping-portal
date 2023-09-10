package com.online.shop.excel.file;

import java.math.BigDecimal;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.online.shop.dto.ProductDto;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

public class ExcelGenerator {

	private List<ProductDto> productDto;
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	

	public ExcelGenerator(List<ProductDto> prodtDto) {
		this.productDto = prodtDto;
		workbook = new XSSFWorkbook();
	}
	
	private void writeHeader() {
		sheet=workbook.createSheet("Product List");
		
		Row row=sheet.createRow(0);
		
		CellStyle style=workbook.createCellStyle();
		XSSFFont font=workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);
		
		createCell(row,0,"id",style);
		createCell(row,1,"productName",style);
		createCell(row,2,"productId",style);
		createCell(row,3,"quantity",style);
		createCell(row,4,"price",style);
	
	}
	private void createCell(Row row,int columnCount,Object value,CellStyle style) {
		sheet.autoSizeColumn(columnCount);
		Cell cell=row.createCell(columnCount);
		if (value instanceof String) {
			cell.setCellValue((String) value);
		}
		else if (value instanceof Double) {
			cell.setCellValue((Double) value);
		}
		
		else {
			cell.setCellValue(String.valueOf((BigDecimal)value) );
		}
		cell.setCellStyle(style);
	}
	

	public void write(List<ProductDto> prodtDto) {
	    int rowCount = 1;
	    
	    CellStyle style = workbook.createCellStyle();
	    XSSFFont font = workbook.createFont();
	    font.setFontHeight(14);
	    style.setFont(font);
	    
//	    Row row = sheet.createRow(rowCount++);
//	    int columnCount = 0;
//	    for(ProductDto productDto : prodtDto) {
//	    createCell(row, columnCount++, productDto.getId(), style);
//	    createCell(row, columnCount++, productDto.getProductId(), style);
//	    createCell(row, columnCount++, productDto.getProductName(), style);
//	    createCell(row, columnCount++, productDto.getQuantity(), style);
//	    createCell(row, columnCount++, productDto.getPrice(), style);
//	}
	    for (int i = 0; i < prodtDto.size(); i++) {
	        Row row = sheet.createRow(rowCount++);
	        int columnCount = 0;
	        ProductDto productDto = prodtDto.get(i);
	        createCell(row, columnCount++, productDto.getId(), style);
	        createCell(row, columnCount++, productDto.getProductId(), style);
	        createCell(row, columnCount++, productDto.getProductName(), style);
	        createCell(row, columnCount++, productDto.getQuantity(), style);
	        createCell(row, columnCount++, productDto.getPrice(), style);
	    }
	}
	public void generate(List<ProductDto> prodtDto, HttpServletResponse response)  throws IOException, java.io.IOException {
		writeHeader();
		write(prodtDto);
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
		
	}

	
}
