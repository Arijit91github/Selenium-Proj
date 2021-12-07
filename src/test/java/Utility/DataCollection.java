package Utility;

import java.util.Hashtable;

public class DataCollection {
	
		
		String testDataSheetName;
		String testCaseName;
		ExcelReader excel;
		
		public DataCollection(ExcelReader excel, String testDataSheetName, String testCaseName) {
			this.excel = excel;
			this.testCaseName = testCaseName;
			this.testDataSheetName = testDataSheetName;	
		}

		public Object[][] dataArray() {

			// Test case start from row number

			int TestcaseRowNum = 1;

			while (!excel.getCellData(testDataSheetName, 0, TestcaseRowNum).equalsIgnoreCase(testCaseName)) {

				TestcaseRowNum++;
			}

			System.out.println("" + testCaseName + " test case starts from row number :- " + TestcaseRowNum);

			// Total number of rows and columns in test data sheet... Columns start
			// from and Test data starts from

			int colsStartRowNum = TestcaseRowNum + 1;
			int dataStartRowNum = colsStartRowNum + 1;

			// Total number of columns are

			int cols = 0;

			while (!excel.getCellData(testDataSheetName, cols, colsStartRowNum).trim().equals("")) {

				cols++;
			}

			System.out.println("Total number of columns are:- " + cols);

			// Total Number of iterations/rows are

			int rows = 0;

			while (!excel.getCellData(testDataSheetName, 0, dataStartRowNum + rows).trim().equals("")) {

				rows++;

			}

			System.out.println("Total number of iterations/rows are :- " + rows);
			System.out.println("***************************************************");

			// Reading and printing a data from test data sheet with Hashtable

			Object[][] htdata = new Object[rows][1];

			int i = 0;

			for (int row = dataStartRowNum; row < dataStartRowNum + rows; row++) {

				Hashtable<String, String> htable = new Hashtable<String, String>();

				for (int col = 0; col < cols; col++) {

					String colName = excel.getCellData(testDataSheetName, col, colsStartRowNum);
					String tdata = excel.getCellData(testDataSheetName, col, row);

					htable.put(colName, tdata);

				}

				System.out.println();
				htdata[i][0] = htable;
				Object data = htdata[i][0];
				System.out.println("----" + data);
				i++;
			}

			return htdata;
		}


}
