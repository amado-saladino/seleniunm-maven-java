package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	private static XSSFWorkbook workBook;
	private static XSSFSheet sheet;

	private static void setDataSource(String path) {

		File excelFile = new File(path);

		try {

			FileInputStream inputStream = new FileInputStream(excelFile);
			workBook = new XSSFWorkbook(inputStream);
		}

		catch (FileNotFoundException e) {
			e.getMessage();
		}

		catch (IOException e) {
			e.getMessage();
		}

	}

	
	private static void selectSheet(String sheetName) {

		sheet = workBook.getSheet(sheetName);
	}

	
	private static String readCell(int rowIndex, int colIndex) {

		return sheet.getRow(rowIndex).getCell(colIndex).getStringCellValue();		
	}

	
	public static Object[][] loadTestData(String file,String sheetName) {

		setDataSource(file);
		selectSheet(sheetName);
		
		Object[][] testData = null;

		int rows = sheet.getLastRowNum() + 1;
		int columns = sheet.getRow(0).getLastCellNum();

		testData = new Object[rows - 1][columns];

		for (int i = 1; i < rows; i++) {

			for (int j = 0; j < columns; j++) {
				testData[i - 1][j] = readCell(i, j);
			}

		}

		return testData;
	}

}
