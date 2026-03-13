package utilities;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testCases.baseClass;

public class ExtentReportManager implements ITestListener
{
	
	public ExtentSparkReporter sparkReporter; // UI of the report
	public ExtentReports extent; //populate common info on the report
	public ExtentTest test; // creating test case entries in the report and update status of the test methods

	public void onStart(ITestContext context) 
	{
		
	SimpleDateFormat df=new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
	Date dt=new Date();
	String CurrentTimeStamp= df.format(dt);	
	String repName= "Test-Report-"+CurrentTimeStamp+",html";

	sparkReporter=new ExtentSparkReporter(System.getProperty("user.dir")+ "/reports/"+repName);

	sparkReporter.config().setDocumentTitle("Automation Report"); // TiTle of report
	sparkReporter.config().setReportName("Functional Testing"); // name of the report
	sparkReporter.config().setTheme(Theme.DARK);

	extent=new ExtentReports();
	extent.attachReporter(sparkReporter);

	extent.setSystemInfo("Computer Name","localhost");
	extent.setSystemInfo("Environment","QA");
	extent.setSystemInfo("Tester Name",System.getProperty("user.name"));
	extent.setSystemInfo("os","Windows10");
	extent.setSystemInfo("Browser name","Chrome");
	
	String os= context.getCurrentXmlTest().getParameter("os");
	extent.setSystemInfo("Operating System",os);
	
	String browser= context.getCurrentXmlTest().getParameter("os");
	extent.setSystemInfo("Browser",browser);
	
	
	List<String> includedGroups=context.getCurrentXmlTest().getIncludedGroups();
	if(!includedGroups.isEmpty())
	{
		extent.setSystemInfo("Groups",includedGroups.toString());
	}
	
	}
	
	public void onTestSuccess(ITestResult result) 
	{

		test = extent.createTest(result.getName()); // create a new enty in the report
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, "Test case PASSED is:" + result.getName()); // update status p/f/s

	}

	public void onTestFailure(ITestResult result) 
	{

		test = extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, "Test case FAILED is:" + result.getName());
		test.log(Status.FAIL, "Test Case FAILED cause is: " + result.getThrowable().getMessage());
		
		try 
		{
			String imgPath=new baseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

	public void onTestSkipped(ITestResult result) 
	{

		test = extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, "Test case SKIPPED is:" + result.getName());
		test.log(Status.SKIP, "Test case SKIPPED cause is: " + result.getThrowable().getMessage());
	}
	
	public void onFinish(ITestContext context) 
	{

		extent.flush();

	}
	

}
