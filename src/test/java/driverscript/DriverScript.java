package driverscript;

import java.io.File;


import java.util.NoSuchElementException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunctionLibrary.FunctionLibrary;
import pages.HomePage;

import pages.Loginpage;
import utilities.Excelfileutil;


public class DriverScript {
 
	WebDriver driver;
	 ExtentReports report;
	 ExtentTest test;
	
	public void driverScript() throws Throwable {
		 Excelfileutil excel=new Excelfileutil();
		 FunctionLibrary fl=new FunctionLibrary();
		
		
		 Loginpage lp = null;
		 HomePage hp = null;
		  String status=null;
		  for(int i=1;i<=excel.rowCount("MasterTestCases");i++){
			  
			  if(excel.getData("MasterTestCases", i, 3).equalsIgnoreCase("Y")){
				  String testcaseName=excel.getData("MasterTestCases", i, 2);
				  String testcaseId_Mastertestcases=excel.getData("MasterTestCases", i, 0);
				  report=new ExtentReports(System.getProperty("user.dir")+"\\Reports\\"+testcaseName+"_"+FunctionLibrary.generateDate()+".html");
				  
				  for(int j=1;j<=excel.rowCount(testcaseName);j++)
				  {	
					  test=report.startTest(testcaseName);	
					  String step_Description=excel.getData(testcaseName, j, 1);
					  String functionName=excel.getData(testcaseName, j, 2);
					  String page=excel.getData(testcaseName, j, 3);				  
					  try{   
						  	if(functionName.equalsIgnoreCase("startBrowser")){
								driver=fl.startBrowser();
								test.log(LogStatus.INFO, step_Description);		
							}
							if(functionName.equalsIgnoreCase("openApplication")){
								fl.openApplication();
								test.log(LogStatus.INFO, step_Description);
							}
							if(functionName.equalsIgnoreCase("login")){
								lp=new Loginpage(driver);
								lp.login(excel, testcaseId_Mastertestcases);
								test.log(LogStatus.INFO, step_Description);
							}
							if(functionName.equalsIgnoreCase("logout")){
								hp=new HomePage(driver);
								hp.logout();
								test.log(LogStatus.INFO, step_Description);	
							}
							if(functionName.equalsIgnoreCase("closeBrowser")){
								fl.closeBrowser();
								test.log(LogStatus.INFO, step_Description);	
							}	
							if(functionName.equalsIgnoreCase("empReg")){
								hp=new HomePage(driver);
								hp.EmployeeRegistration(excel, testcaseId_Mastertestcases);
								test.log(LogStatus.INFO, step_Description);	
							}
							
							status="PASS";
							excel.setData(testcaseName, j, 3, status);
							test.log(LogStatus.PASS, testcaseName);	
							
						}catch(Exception e) {
						
						status="FAIL";
							excel.setData(testcaseName, j, 3, status);
							test.log(LogStatus.FAIL, testcaseName);	
							File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
							FileUtils.copyFile(srcFile,new File(System.getProperty("user.dir")+"\\Screenshots\\"+step_Description+FunctionLibrary.generateDate()+".png"));
							break;
						}
				  }  
				  
				  report.endTest(test);
				  report.flush();
				  
				  if(status.equalsIgnoreCase("PASS")) {
					  excel.setData("MasterTestCases",i, 4, "PASS");
				  }else {
					  excel.setData("MasterTestCases",i, 4, "FAIL");
				  }	
			 }
			 else{
				 excel.setData("MasterTestCases",i, 4, "Not Executed");
			 }  

		 }	  
    }	
}
	


