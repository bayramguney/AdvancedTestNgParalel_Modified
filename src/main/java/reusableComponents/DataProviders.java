package reusableComponents;



import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name="dp")
    public static Object[][] getData() {

        ExcelReader excel = new ExcelReader(System.getProperty("user.dir") + PropertiesOperations.getPropertyValueByKey("testDataLocation"));
        return DataUtil.getData(excel);
    }
}
