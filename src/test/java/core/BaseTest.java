package core;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.codehaus.groovy.ast.tools.GeneralUtils;
import org.hamcrest.Matchers;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import utils.GenericUtils;

public class BaseTest {

	public static ExtentReports extent;	
	public static ExtentTest etest;
	public String env;
	public String url;
	
	
	@BeforeSuite(alwaysRun = true)
	public void suitePreCondition()
	{
		GenericUtils gUtils = new GenericUtils();
		env= gUtils.getPropertyValue("global","ENV") ;
		url= gUtils.getPropertyValue(env,"URL") ;
		
		extent = new ExtentReports();
		
		ExtentSparkReporter spark = new ExtentSparkReporter("./reports/report.html");
		extent.attachReporter(spark);
		
		System.out.println("------------ Executing Before Suite -------------- ");
		RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
		
		reqBuilder.addHeader("accept", "application/vnd.github+json");
		reqBuilder.setBaseUri(url);
		//RestAssured.baseURI = "https://api.github.com";
		
		ResponseSpecBuilder builder = new ResponseSpecBuilder();
		builder.expectHeader("Server", Matchers.equalTo("GitHub.com"));
		builder.expectHeader("Content-Type", Matchers.equalTo("application/json; charset=utf-8"));
		
		RestAssured.requestSpecification = reqBuilder.build();
		RestAssured.responseSpecification = builder.build();
				
	}
	
	@AfterSuite(alwaysRun = true)
	public void suitePostCondition() {
		System.out.println("------------ Executing After Suite -------------- ");
		extent.flush();
	}

	@BeforeMethod(alwaysRun =true)	
	
		public void preMethodCondition(Method method)
		{
			etest = extent.createTest(method.getName());
		}
	
	@AfterMethod(alwaysRun = true)
	public void MethodpostCondition(ITestResult result)
	{
		System.out.println(result.getStatus());
		if(result.getStatus()==2)
		{
			etest.fail(result.getThrowable().getMessage());
		}
	}
		
	
}
