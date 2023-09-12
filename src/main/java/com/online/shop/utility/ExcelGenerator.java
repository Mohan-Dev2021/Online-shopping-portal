package com.online.shop.utility;

import java.math.BigDecimal;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.online.shop.dto.ProductDto;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ExcelGenerator {

	public List<ProductDto> productDto;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	

	public ExcelGenerator(List<ProductDto> prodtDto) {
		this.productDto = prodtDto;
		workbook = new XSSFWorkbook();
	}
	
	public void writeHeader() {
		sheet=workbook.createSheet("Products");
		
		Row row=sheet.createRow(0);
		
		CellStyle style=workbook.createCellStyle();
		XSSFFont font=workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);
		
		createCell(row,0,"S.NO",style);
		createCell(row,1,"productName",style);
		createCell(row,2,"productId",style);
		createCell(row,3,"quantity",style);
		createCell(row,4,"price",style);
	
	}
	public void createCell(Row row,int columnCount,Object value,CellStyle style) {
		sheet.autoSizeColumn(columnCount);
		Cell cell=row.createCell(columnCount);
		if (value instanceof String) {
			cell.setCellValue((String) value);
		}
		else if (value instanceof Double) {
			cell.setCellValue((Double) value);
		}
		else if (value instanceof Integer) {
			cell.setCellValue((Integer) value);
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
	    

	    for (int i = 0; i < prodtDto.size(); i++) {
	        Row row = sheet.createRow(i+1);
	        int columnCount = 0;
	        ProductDto productDto = prodtDto.get(i);
	        createCell(row, columnCount++, row.getRowNum(), style);
	        createCell(row, columnCount++, productDto.getProductName(), style);
	        createCell(row, columnCount++, productDto.getProductId(), style);
	        createCell(row, columnCount++, productDto.getQuantity(), style);
	        createCell(row, columnCount++, productDto.getPrice(), style);
	    }
	}


	
}
