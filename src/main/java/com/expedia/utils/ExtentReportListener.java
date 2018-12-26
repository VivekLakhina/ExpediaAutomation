package com.expedia.utils;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReportListener implements IReporter {

	ExtentReports report;

	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {

		report = new ExtentReports(outputDirectory + File.separator + "extentReport.html", true);

		for (ISuite suite : suites) {
			Map<String, ISuiteResult> suiteResult = suite.getResults();

			for (ISuiteResult result : suiteResult.values()) {

				ITestContext context = result.getTestContext();

				testResults(context.getPassedTests(), LogStatus.PASS);
				testResults(context.getFailedTests(), LogStatus.FAIL);
				testResults(context.getSkippedTests(), LogStatus.SKIP);
			}
		}

		report.flush();
		report.close();
	}

	public void testResults(IResultMap rmap, LogStatus status) {
		ExtentTest test;

		if (rmap.size() > 0) {

			for (ITestResult testResult : rmap.getAllResults()) {
				test = report.startTest(testResult.getMethod().getMethodName());
				
				test.setStartedTime(getTime(testResult.getStartMillis()));
				test.setEndedTime(getTime(testResult.getEndMillis()));
				
				for(String group : testResult.getMethod().getGroups()) {
					test.assignCategory(group);
				}
				if(testResult.getThrowable()!= null) {
					
					test.log(status, testResult.getThrowable());
				}else {
					test.log(status, "Test "+testResult.toString().toLowerCase()+"ed");
				}

			}
		}
	}
	
	public Date getTime(long millis) {
		
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(millis);
		return cal.getTime();
	}
}
