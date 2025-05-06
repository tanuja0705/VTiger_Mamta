package com.crm.generic.listeners;

import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.crm.generic.ThreadLocal.ThreadLocalObject;

public class ListenerImpClass implements ITestListener, ISuiteListener {
	public ExtentSparkReporter spark;
	public ExtentReports report;
	public ExtentTest test;

	public void onStart(ISuite suite) {
		System.out.println("Suite starts");
		String time = new Date().toString().replace(" ", "_").replace(":","_");
		spark = new ExtentSparkReporter("./AdvanceReport/report"+time+".html");
		spark.config().setDocumentTitle("Vtiger");
		spark.config().setReportName("VtigerReport");
		spark.config().setTheme(Theme.DARK);

		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("browser", "chrome");
		report.setSystemInfo("os", "windows-10");
	}

	public void onFinish(ISuite suite) {
		System.out.println("Suite ends");
		report.flush();
	}

	public void onTestStart(ITestResult result) {
		System.out.println("Test starts");
		test = report.createTest(result.getMethod().getMethodName());
		ThreadLocalObject.setTest(test);
		ThreadLocalObject.getTest().log(Status.INFO, result.getMethod().getMethodName()+"=====started======");

	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("Test Succeed");
	}

	public void onTestFailure(ITestResult result) {
		String TestName = result.getMethod().getMethodName();
		TakesScreenshot tks = (TakesScreenshot) ThreadLocalObject.getDriver();
		String src = tks.getScreenshotAs(OutputType.BASE64);
		String time = new Date().toString().replace(" ", "_").replace(":","_");
		ThreadLocalObject.getTest().addScreenCaptureFromBase64String(src, TestName+"_"+time);
		ThreadLocalObject.getTest().log(Status.FAIL, result.getThrowable());

	}

	public void onTestSkipped(ITestResult result) {
		System.out.println("Test Skipped");
	}

	public void onStart(ITestContext context) {
		System.out.println("Testtag starts");
	}

	public void onFinish(ITestContext context) {
		System.out.println("Testtag Ends");
	}
}
