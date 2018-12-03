package com.expedia.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.expedia.base.BaseClass;

public class TestUtils extends BaseClass {

	// Screenshot utitlity
	// Takes screenshot and copies it to destination directory with
	// currenttimeMillis.png name
	public static void getScreenshot() {

		TakesScreenshot ts = (TakesScreenshot) driver;
		File srcFile = ts.getScreenshotAs(OutputType.FILE);
		String destinationDirectory = prop.getProperty("ScreenShotDirectory");
		try {
			FileUtils.copyFileToDirectory(srcFile,
					new File(destinationDirectory + "\\screenShot\\" + System.currentTimeMillis() + ".png"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	// getData from Excel
	// fetches data from excel and retuns a 2D array
	public static Object[][] getData() {
		XSSFWorkbook wb;
		XSSFSheet sheet = null;

		try {
			FileInputStream fis = new FileInputStream(prop.getProperty("ExpediaTestData"));
			wb = new XSSFWorkbook(fis);
			sheet = wb.getSheet(prop.getProperty("SigninTestaData"));

		} catch (FileNotFoundException e) {
			System.out.println(e.getLocalizedMessage());
		} catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
			e.printStackTrace();
		}

		Object[][] sheetData = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {

				sheetData[i][j] = sheet.getRow(i + 1).getCell(j).toString();
			}

		}
		return sheetData;
	}
}
