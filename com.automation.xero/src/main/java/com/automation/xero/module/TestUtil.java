package com.automation.xero.module;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class TestUtil {
	public static int page_load_timeout=60;
	public static int implicitly_wait=60;
	public static String sheetPath="C:/Users/Num/git/com.selenium.pom/com.selenium.pom/src/main/java/com/selenium/testdata/TestSuits.xls";

	
	public static String[][] readDataFromXl(String sheetName) throws IOException {
		// String cur_dir = System.getProperty("user.dir");
		FileInputStream fio = new FileInputStream(new File(sheetPath));
		HSSFWorkbook wb = new HSSFWorkbook(fio);
		HSSFSheet sheet = wb.getSheet(sheetName);
		int trow = sheet.getLastRowNum() + 1;
		int tcol = sheet.getRow(0).getLastCellNum();
		String[][] s = new String[trow][tcol];
		System.out.println("total rows=" + trow + " and total column=" + tcol);
		for (int i = 0; i < trow; i++) {
			for (int j = 0; j < tcol; j++) {
				int cellType = sheet.getRow(i).getCell(j).getCellType();
				if (cellType == HSSFCell.CELL_TYPE_NUMERIC) {
					s[i][j] = String.valueOf((int) sheet.getRow(i).getCell(j)
							.getNumericCellValue());
					System.out.println("integer");
				} else

					s[i][j] = sheet.getRow(i).getCell(j).getStringCellValue();
				// System.out.println(s[i][j]);

			}
		}

		return s;
	}

}
