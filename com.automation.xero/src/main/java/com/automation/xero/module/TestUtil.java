package com.automation.xero.module;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;

public class TestUtil {
	public static int page_load_timeout=60;
	public static int implicitly_wait=60;
	//public static String sheetPath="C:/Users/Num/git/com.selenium.pom/com.selenium.pom/src/main/java/com/selenium/testdata/TestSuits.xls";
	public static HSSFWorkbook wb;
	
	
	public static String[][] readXlSheet(String link,String sheetName) throws IOException{
		FileInputStream fio=new FileInputStream(new File(link));
		wb=new HSSFWorkbook(fio);
		HSSFSheet sheet=wb.getSheet(sheetName);
		int trow=sheet.getLastRowNum()+1;
		int tcol=sheet.getRow(0).getLastCellNum();
		String[][] s = new String[trow][tcol];
		System.out.println("total rows="+trow+" and total column="+tcol);
		for(int i=0;i<trow;i++){
			for(int j=0;j<tcol;j++){
				int cellType=sheet.getRow(i).getCell(j).getCellType();
				if(cellType==HSSFCell.CELL_TYPE_NUMERIC)
					s[i][j]=String.valueOf((int)sheet.getRow(i).getCell(j).getNumericCellValue());
				else
				s[i][j]=sheet.getRow(i).getCell(j).getStringCellValue();	
		
			}
		}
		
		return s;
	}
	
	public static void writeXlSheet(String link, String sheetName,String text,int row,int col) throws IOException {
		File f=new File(link);
		FileInputStream fio=new FileInputStream(f);
		HSSFWorkbook wb=new HSSFWorkbook(fio);
		HSSFSheet sheet=wb.getSheet(sheetName);
		sheet.getRow(row).getCell(col).setCellValue(text);
		FileOutputStream fop=new FileOutputStream(f);
		wb.write(fop);
		fop.flush();
		fop.close();
		}
	
	
	public static void setXlColorStyle(String link,String sheetName,int iRow,int iCol,String status) throws IOException{
		File f=new File(link);
		FileInputStream fio=new FileInputStream(f);
		HSSFWorkbook wb=new HSSFWorkbook(fio);
		HSSFSheet sheet=wb.getSheet(sheetName);
		
		
		HSSFRow row = sheet.getRow(iRow);
		HSSFCell cell = row.getCell(iCol);
		
		if(status.equalsIgnoreCase("pass"))
			fillBackgroundColor(wb, "green", cell);
		else
			fillBackgroundColor(wb, "red", cell);
		
		FileOutputStream fop=new FileOutputStream(f);
		wb.write(fop);
		fop.flush();
		fop.close();
		}
	public static void fillBackgroundColor(HSSFWorkbook wb,String color,HSSFCell cell){
		HSSFCellStyle style=(HSSFCellStyle) wb.createCellStyle();
		if(color.equalsIgnoreCase("green")){
			style.setFillForegroundColor(new HSSFColor.GREEN().getIndex());
			}
		else if(color.equalsIgnoreCase("red")){
			style.setFillForegroundColor(new HSSFColor.RED().getIndex());
			}
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		cell.setCellStyle(style);
	}
	


}
