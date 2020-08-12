package utils;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	static XSSFWorkbook workbook;
	static XSSFSheet sheet;

	public ExcelUtils(String excelPath, String sheetName) {
		try {
			workbook = new XSSFWorkbook(excelPath);
			sheet= workbook.getSheet(sheetName);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		getRowCount();
		getCellData(0, 0);
	}

	public static void getRowCount() {
		try {
			int rowCount = sheet.getPhysicalNumberOfRows();
			System.out.println("Number of rows :" +rowCount);

		} catch (Exception exp) {
			System.out.println(exp.getMessage());
			System.out.println(exp.getMessage());
			exp.getCause();
			exp.printStackTrace();
		}

	}

	public static void getCellData(int rowNum, int columnNum) {
		try {
			String cellData = sheet.getRow(rowNum).getCell(columnNum).getStringCellValue();
			System.out.println(cellData);

		} catch (Exception exp) {
			System.out.println(exp.getMessage());
			exp.getMessage();
			exp.getCause();
			exp.printStackTrace();
		}

	}
}
