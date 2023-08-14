package pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TranslinkPage {

	WebDriver driver;
	
	
	@FindBy(xpath="//a[contains(text(),'Schedules and Maps')]")
	public WebElement SchedulesMaps;
	
	@FindBy(linkText="Bus")
	public WebElement optionBus;
	
	@FindBy(xpath="//h1[@id='bus-schedules']")
	public WebElement verifyBusText;
	
	@FindBy(xpath="//input[@id='find-schedule-searchbox']")
	public WebElement inputBusDetails;
	
	@FindBy(xpath="//button[@form='searchAPI']") 
	public WebElement findScheduleBtn;
	
	@FindBy(linkText="#99 - UBC B-Line") 
	public WebElement resultFromBusSearch;
	
	@FindBy(xpath="//input[@id='schedulestimefilter-startdate']")
	public WebElement date;
	
	@FindBy(xpath="//input[@id='schedulestimefilter-starttime']")
	public WebElement startTime;
	
	@FindBy(xpath="//input[@id='schedulestimefilter-endtime']")
	public WebElement endTime;
	
	@FindBy(xpath="//button[@form='SchedulesTimeFilter']")
	public WebElement searchBtn;
	
	@FindBy(xpath="//button[@aria-controls='StopsPicker-listbox']")
	public WebElement filterStops;
	
	@FindBy(xpath="//li[@id='StopsPicker-50913']")
	public WebElement selecta50913;
	
	@FindBy(xpath="//li[@id='StopsPicker-50916']")
	public WebElement selectb50916;
	
	@FindBy(xpath="//li[@id='StopsPicker-58613']")
	public WebElement selectc58613;
	
	@FindBy(xpath="//div[@class='contentItem hideOnAPIFail flexContainer flexWrapper useButtons useFontColor']//button[@type='submit']")
	public WebElement selectedStopsOnlyBtn;
	
	
	@FindBy(xpath="//body/main[@id='content']/aside[1]/div[1]/article[1]/section[2]/div[1]/button[1]")  
	public WebElement addToFavourite;
	
	
	@FindBy(xpath="//input[@id='newfavourite']")
	public WebElement rename;
	
	@FindBy(xpath="//body/dialog[@id='add-to-favourites_dialog']/form[1]/section[1]/div[1]") 
	public WebElement AddToFavouriteAfterBtn;
	
	
	@FindBy(linkText="Manage my favourites")  
	public WebElement manageFavouriteBtn;
	
	@FindBy(xpath="//a[contains(text(),'99 UBC B-Line – Morning Schedule')]")
	public WebElement verifyFavourite;
	

	
	public void openBrowser(String browserName) throws IOException {
		
//		FileInputStream Bw = new FileInputStream("C:\\QA\\testing\\prop.properties");   //if you want to read value from property file
//		Properties prop = new Properties();
//		prop.load(Bw);
//		
//		String Browser = prop.getProperty("browser");
		
		String defaultBrowser = "Firefox";
        String Browser = browserName != null ? browserName : defaultBrowser; //(condition) ? (value_if_true) : (value_if_false)-checks if browserName is not null. 

		
		if(Browser.equals("Firefox")) {
			driver = new FirefoxDriver();
			driver.get("https://www.translink.ca/");
			
		}else if(Browser.equals("Chrome")) {
			driver = new ChromeDriver();
			driver.get("https://www.translink.ca/");
		}else {
			driver = new SafariDriver();
			driver.get("https://www.translink.ca/");
		}
		
		
		PageFactory.initElements(driver, this);	
		
	}
	
	public void maximizeWindow() {
		driver.manage().window().minimize();
		//driver.manage().window().getSize();
		driver.manage().window().maximize();
	}
	
	public void closeBrowser() {
		driver.quit();
	}
	
	public void selectBus() throws InterruptedException {
		//step-2
		Actions builder = new Actions(driver);
		WebElement SchedulesMaps1 = SchedulesMaps;
		builder.moveToElement(SchedulesMaps1).build().perform();
		
		Thread.sleep(2000);
		optionBus.click();
	}
	
	public String verifyBusPage() throws InterruptedException {
		//step-3
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		String actualText = verifyBusText.getText();
		System.out.println(actualText);
		return actualText;
	}
	
	public void scrollToInputArea() throws InterruptedException {
		//step-4
		JavascriptExecutor sd = (JavascriptExecutor)driver;
		sd.executeScript("window.scrollBy(0,300)");
		
	}
	
	public void inputAndSearchBus() throws InterruptedException {
		//step-5
		
		Thread.sleep(2000);
		inputBusDetails.sendKeys("99");
		findScheduleBtn.click();
		JavascriptExecutor sd = (JavascriptExecutor)driver;

		sd.executeScript("window.scrollBy(0,600)");
		
	}
	
	public void selectOneResultFromList() throws InterruptedException {
		//step-6
		Thread.sleep(2000);
		resultFromBusSearch.click();
	
	}
	
	public void enterDateTime() throws InterruptedException {
		//step-7
	
		String script = "arguments[0].value = '2023-08-15';";
        WebElement dateInput = driver.findElement(By.xpath("//input[@id='schedulestimefilter-startdate']"));
        ((JavascriptExecutor) driver).executeScript(script, dateInput);	
       

        String time = "07:00";
        String script1 = "arguments[0].value = arguments[1];";
        
        WebElement startTimeInput = driver.findElement(By.xpath("//input[@id='schedulestimefilter-starttime']"));
        ((JavascriptExecutor) driver).executeScript(script1, startTimeInput, time);
        
        String newTime = "08:30";
        String script2 = "arguments[0].value = arguments[1];";
        
        WebElement endTimeInput = driver.findElement(By.xpath("//input[@id='schedulestimefilter-endtime']"));
        ((JavascriptExecutor) driver).executeScript(script2, endTimeInput, newTime);
		
        Thread.sleep(2000);
		searchBtn.click();
		
		Thread.sleep(2000);
		JavascriptExecutor sd = (JavascriptExecutor)driver;
		sd.executeScript("window.scrollBy(0,700)");
		Thread.sleep(2000);
	}
	
	public void selectStops() throws InterruptedException {
		//step-8
		filterStops.click();
		Thread.sleep(2000);
		selecta50913.click();
		selectb50916.click();
		selectc58613.click();
		selectedStopsOnlyBtn.click();
		
	}
	
	public void addToFavorite() throws InterruptedException {
		//step-9
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, 0);");
		Thread.sleep(2000);
		addToFavourite.click();		
	}
	
	public void renameAddToFavourite() throws InterruptedException {
		//step-10
		rename.clear();
		rename.sendKeys("99 UBC B-Line – Morning Schedule");
		Thread.sleep(2000);
		AddToFavouriteAfterBtn.click();
	}
	
	public void clickManageFavouriteBtn() {
		manageFavouriteBtn.click();
	}
	public String manageFavouriteValidation() throws InterruptedException {
		//step-11
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		String actualfavourite = verifyFavourite.getText();
		
		System.out.println(actualfavourite);
		return actualfavourite;
	}

	public void validateFourStamps() throws InterruptedException {
		//step-2 - scenario-2
		By[] timestampLocators = {
	            By.xpath("//td[@data-stop-time='3032']"),  
	            By.xpath("//td[@data-stop-time='3038']"), 
	            By.xpath("//td[@data-stop-time='3042']"), 
	            By.xpath("//td[@data-stop-time='3045']")  
	        };

		
		
		List<LocalTime> timestamps = new ArrayList<>();
		
		Thread.sleep(2000);
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm a", Locale.ENGLISH);


        for (By locator : timestampLocators) {
        	
            WebElement timestampElement = driver.findElement(locator);
   
            Thread.sleep(2000);
            
            String timestampText = timestampElement.getText().trim().replace('\u00A0', ' ');
            if (!timestampText.isEmpty()) { // Check if the timestamp text is not empty
                try {
                    LocalTime timestamp = LocalTime.parse(timestampText, timeFormatter);
                    timestamps.add(timestamp);
                } catch (DateTimeParseException e) {
                    System.out.println("Error parsing timestamp: " + timestampText);
                    // Handle the exception if needed
                }
            
            
        }

        boolean timestampsInOrder = true;
        boolean timeIntervalsLessThan30Minutes = true;

        for (int i = 0; i < timestamps.size() - 1; i++) {
            if (timestamps.get(i).isAfter(timestamps.get(i + 1))) {
                timestampsInOrder = false;
                break;
            }

            if (timestamps.get(i).plusMinutes(30).isBefore(timestamps.get(i + 1))) {
                timeIntervalsLessThan30Minutes = false;
                break;
            }
        }
  
            
        if (timestampsInOrder && timeIntervalsLessThan30Minutes) {
            System.out.println("First 4 timestamps of stop #58613 are in order and time intervals are less than 30 minutes.");
        } else {
            System.out.println("First 4 timestamps of stop #58613 are not in order or time intervals are greater than 30 minutes.");
        }

	
	}
	}
}
