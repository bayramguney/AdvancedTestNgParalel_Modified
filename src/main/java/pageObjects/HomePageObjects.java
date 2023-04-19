package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import testBase.DriverFactory;
import testBase.TestBase;

import java.util.*;

public class HomePageObjects extends TestBase {


    By seach_field = By.cssSelector("[name='q']");
    By google_search=By.cssSelector("[value='Google Search']");

    By sites=By.xpath("//div[@jsname='yEVEwb']");

    public void search() throws InterruptedException {
        sendKeys_custom(DriverFactory.getInstance().getDriver().findElement(seach_field), "search_field", "Selenium");
        click_custom(DriverFactory.getInstance().getDriver().findElement(google_search),"google_search" );
        Thread.sleep(3000);
    }

    public void validatetitle() throws Throwable {
        List<WebElement> titles=DriverFactory.getInstance().getDriver().findElements(sites);
        for (WebElement each:titles) {
            assertIncludeString_custom("Selenium",each.getText(),"Sponsored Article");
        }

    }

}
