package Tests;

import java.util.HashMap;

import org.testng.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.HomePageObjects;
import reusableComponents.ExcelOperations;
import testBase.*;

/**
 * @author: Prakash Narkhede
 * @Youtube: https://www.youtube.com/automationtalks
 * @LinkedIn: https://www.linkedin.com/in/panarkhede89/
 */

public class TestCase extends TestBase{

	HomePageObjects homePage = new HomePageObjects();



	
	@Test()
	public void TaskCreationTest() throws Throwable {


		ExtentFactory.getInstance().getExtent().info("Test Data for this execution run is: "+new Throwable().getStackTrace()[0].getMethodName());

      homePage.search();
      homePage.validatetitle();


	}



}
