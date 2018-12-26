package com.expedia.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.expedia.base.BaseClass;

public class ExcelDriver extends BaseClass {
	XSSFWorkbook wb;
	XSSFSheet ws;

	private void loadExcelSheet() {
		try {
			FileInputStream fis = new FileInputStream(prop.getProperty("ExpediaTestData"));
			wb = new XSSFWorkbook(fis);
			ws = wb.getSheet("Sheet2");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void getData() {

		loadExcelSheet();

		if (ws != null) {

			Map<Object, Object> testData = new HashMap<Object, Object>();

			for (int i = 1; i < ws.getLastRowNum(); i += 2) {
				if(ws.getRow(i).getCell(0).getCellType() == 3) {
				for (int j = 1; j < ws.getRow(0).getLastCellNum(); j++) {

					// Checking Celltype of cell from Excel and then retrieving the data from cell
					// accordingly
					// CellType : 3 == BLANK
					if (ws.getRow(i + 1).getCell(j).getCellType() == 3) {
						testData.put("", "");
					}
					// CellType : 0 == Integer
					else if (ws.getRow(i + 1).getCell(j).getCellType() == 0) {
						testData.put(ws.getRow(i).getCell(j).getStringCellValue(),
								ws.getRow(i + 1).getCell(j).getNumericCellValue());

					}
					// CellType : 1 == String
					else if (ws.getRow(i + 1).getCell(j).getCellType() == 1) {
						testData.put(ws.getRow(i).getCell(j).getStringCellValue(),
								ws.getRow(i + 1).getCell(j).getStringCellValue());
					} else {
						System.out.println(
								"Invalid data in format in Excel Sheet. Please provide data in String or Numeric form");
					}
				}
			}
			System.out.println(testData);
		}
	}

}
}