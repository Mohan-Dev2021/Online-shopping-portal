package com.online.shop.utility;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.online.shop.dto.ProductDto;

import io.jsonwebtoken.io.IOException;

public class ExcelHelper {

	public List<ProductDto> productDto;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public EShopConstants constants;

	public static String TYPE = EShopConstants.FORMAT;
	static String[] HEADERs = { "Id", "ProductName", "ProductId", "Quantity", "Price" };
	static String SHEET = "ExecelFile";

	public static boolean hasExcelFormat(MultipartFile file) {
		if (file.getOriginalFilename().endsWith(".xlsx")) {
			return true;
		} else {
			return false;
		}
	}

	public static List<ProductDto> excelToProducts(InputStream is) throws java.io.IOException {
		try {
			Workbook workbook = new XSSFWorkbook(is);

			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rows = sheet.iterator();
			List<ProductDto> listOfProducts = new ArrayList<ProductDto>();

			int rowNumber = 0;
			while (rows.hasNext()) {
				Row currentRow = rows.next();

				// skip header
				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}
				Iterator<Cell> cellsInRow = currentRow.iterator();
				ProductDto productDto = new ProductDto();
				int cellIdx = 0;
				while (cellsInRow.hasNext()) {
					Cell currentCell = cellsInRow.next();
					switch (cellIdx) {
					case 0:
						break;
					case 1:
						productDto.setProductName(currentCell.getStringCellValue());
						break;
					case 2:
						productDto.setProductId(currentCell.getStringCellValue());
						break;
					case 3:
						productDto.setQuantity(currentCell.getNumericCellValue());
						break;
					case 4:
						productDto.setPrice((BigDecimal.valueOf(currentCell.getNumericCellValue())));
						break;
					default:
						break;
					}
					cellIdx++;
				}
				listOfProducts.add(productDto);
			}
			workbook.close();
			return listOfProducts;
		} catch (IOException e) {
			throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
		}
	}
}