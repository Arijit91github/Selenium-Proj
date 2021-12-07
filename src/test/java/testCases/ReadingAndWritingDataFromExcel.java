package testCases;

import co.in.bymat.seleniumTraining.ExcelReader;

public class ReadingAndWritingDataFromExcel {
	
 public static void main(String[] args) {
	

	ExcelReader excel=new ExcelReader(System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\Master_Sheet.xlsx");
//    excel.addColumn("Test_data", "NewColumn");
//    excel.addSheet("NewSheet");
	excel.getCellData("Test_data", 2, 4);
	excel.getCellData("Test_data", 4, 5);
	excel.getCellData("Test_data", "Name", 6);
	excel.getCellData("Test_data", "City", 2);
}
}