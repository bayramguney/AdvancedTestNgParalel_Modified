package reusableComponents;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import testBase.DriverFactory;
import testBase.ExtentFactory;
import testBase.ExtentReportNG;


public class ListenersImplementation implements ITestListener {

    static ExtentReports report;
    ExtentTest test;

    public void onTestStart(ITestResult result) {
        //before each test case
        test = report.createTest(result.getMethod().getRealClass().getName());
        ExtentFactory.getInstance().setExtent(test);
    }

    public void onTestSuccess(ITestResult result) {
        String text = "<b>Test Case: " + result.getMethod().getRealClass().getName() + "==>" + result.getMethod().getMethodName() + " is Passed.</b>";
        Markup m = MarkupHelper.createLabel(text, ExtentColor.GREEN);
        ExtentFactory.getInstance().getExtent().log(Status.PASS,m);
        ExtentFactory.getInstance().removeExtentObject();
    }

    public void onTestFailure(ITestResult result) {
        String text="<b>Test Case: " + result.getMethod().getMethodName() + " is Failed.</b>";
        Markup m = MarkupHelper.createLabel(text, ExtentColor.RED);
        ExtentFactory.getInstance().getExtent().log(Status.FAIL,m);
        ExtentFactory.getInstance().getExtent().log(Status.FAIL, result.getThrowable());

        //add screenshot for failed test.
        File src = ((TakesScreenshot) DriverFactory.getInstance().getDriver()).getScreenshotAs(OutputType.FILE);
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy HH-mm-ss");
        Date date = new Date();
        String actualDate = format.format(date);

        String screenshotPath = System.getProperty("user.dir") +
                "/Reports/Screenshots/" + actualDate + ".jpeg";
        File dest = new File(screenshotPath);

        try {
            FileUtils.copyFile(src, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ExtentFactory.getInstance().getExtent().addScreenCaptureFromPath(screenshotPath, "Test case failure screenshot");
            ExtentFactory.getInstance().removeExtentObject();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void onTestSkipped(ITestResult result) {
        String text="<b>Test Case: " + result.getMethod().getMethodName() + " is skipped.</b>";
        Markup m = MarkupHelper.createLabel(text, ExtentColor.ORANGE);
        ExtentFactory.getInstance().getExtent().log(Status.SKIP, m);
        ExtentFactory.getInstance().removeExtentObject();
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    public void onTestFailedWithTimeout(ITestResult result) {
    }

    public void onStart(ITestContext context) {
        try {
            report = ExtentReportNG.setupExtentReport();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onFinish(ITestContext context) {
        //close extent
        report.flush();
    }

}
