package reusableComponents;

import java.util.Hashtable;
import org.testng.annotations.DataProvider;

public class DataUtil {



    @DataProvider
    public static Object[][] getData(ExcelReader excel,String sheetName) {

        int rows = excel.getRowCount(PropertiesOperations.getPropertyValueByKey("sheetName"));

        // Checking total rows in test case

        int dataStartRowNum =2;

        int testRows = 0;
        while (!excel.getCellData(sheetName, 0, dataStartRowNum + testRows).equals("")) {

            testRows++;
        }
        // Checking total cols in test case

        int colStartColNum = 1;
        int testCols = 0;

        while (!excel.getCellData(sheetName ,testCols, colStartColNum).equals("")) {

            testCols++;

        }

        // Printing data

        Object[][] data = new Object[testRows][1];

        int i = 0;
        for (int rNum = dataStartRowNum; rNum < (dataStartRowNum + testRows); rNum++) {

            Hashtable<String, String> table = new Hashtable<String, String>();

            for (int cNum = 0; cNum < testCols; cNum++) {

                String testData = excel.getCellData(sheetName, cNum, rNum);
                String colName = excel.getCellData(sheetName, cNum, colStartColNum);

                table.put(colName, testData);

            }

            data[i][0] = table;
            i++;

        }

        return data;
    }

}
