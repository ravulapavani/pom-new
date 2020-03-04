package pages;

import org.apache.poi.ss.formula.functions.Choose;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.Excelfileutil;
public class HomePage {

	  
    @FindBy(id="welcome")
    public WebElement lnkwelcome;
    
    @FindBy(linkText="Logout")
    public WebElement lnklogout;
    
    @FindBy(xpath="//b[contains(text(),'PIM')]")
    public WebElement lnkPIM;
    
    @FindBy(linkText="Admin")
    public WebElement lnkadmin;
    
    @FindBy(id="systemUser_employeeName_empName")
    public WebElement empName;
    
    @FindBy(id="systemUser_userName")
    public WebElement userName;
    
    @FindBy(id="systemUser_password")
    public WebElement password;
    
    @FindBy(id="systemUser_confirmPassword")
    public WebElement confirmPassword;
    
    @FindBy(linkText="Employee List")
    public WebElement lnkEmployeeList;
    
    @FindBy(xpath="//input[@id='btnAdd']")
    public WebElement btnAdd;
    
    @FindBy(xpath="//table[@id='resultTable']/tbody")
    public WebElement usersTable; 
    
    @FindBy(xpath="//input[@id='firstName']")
    public WebElement firstName;
    
    @FindBy(xpath="//input[@id='lastName']")
    public WebElement lastName;
    
    @FindBy(xpath="//input[@id='btnSave']")
    public WebElement btnSave2; 
    
    @FindBy(xpath="//input[@id='btnSave']")
    public WebElement btnSave; 
    
    @FindBy(xpath="//*[@id='profile-pic']/h1")
    public WebElement profilePic;
   
  
    
    WebDriver driver;
    public HomePage(WebDriver driver) {
    	this.driver=driver;
		PageFactory.initElements(driver, this);
	}
    
    public void logout() throws Exception {
			Thread.sleep(3000);
		 	String expval="LOGIN";
		 	lnkwelcome.click();
		 	Thread.sleep(5000);
		 	lnklogout.click();
	}
  
    /*to emp registration*/
	public void EmployeeRegistration(Excelfileutil exlu,String tcid) throws Exception {
		int testcaseRow= exlu.getTestcaseRow(tcid);
		String frstName=exlu.getData("TestData", testcaseRow, 5);
		String lstname=exlu.getData("TestData", testcaseRow, 6);
		lnkPIM.click();
		Thread.sleep(5000);
		lnkEmployeeList.click();
	    btnAdd.click();
	    Thread.sleep(5000);
        firstName.sendKeys(frstName);
        lastName.sendKeys(lstname);
        
        
        
        btnSave.click();
	}
    

}
