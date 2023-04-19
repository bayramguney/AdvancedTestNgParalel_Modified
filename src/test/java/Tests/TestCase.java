package Tests;


import java.util.Hashtable;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePageObjects;
import reusableComponents.DataProviders;
import testBase.DriverFactory;
import testBase.ExtentFactory;
import testBase.TestBase;


public class TestCase extends TestBase {

    HomePageObjects homePage = new HomePageObjects();


    @Test(dataProviderClass = DataProviders.class, dataProvider = "dp", priority = 1)
    public void TaskCreationTest(Hashtable<String,String> data) throws Throwable {


        ExtentFactory.getInstance().getExtent().info("Test Data for this execution run is: " + new Throwable().getStackTrace()[0].getMethodName());
        System.out.println(data.get("name")+"---"+data.get("lastname"));
        homePage.search();
        homePage.validatetitle();

        String google=DriverFactory.getInstance().getDriver().getWindowHandle();

        Assert.fail();
        openNewTab("https://www.wikipedia.org/");

        String wiki=DriverFactory.getInstance().getDriver().getWindowHandle();


        DriverFactory.getInstance().getDriver().switchTo().window(google);



    }
}
