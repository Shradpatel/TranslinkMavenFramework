package tests;

import org.testng.annotations.Test;


import pages.TranslinkPage;

import org.testng.annotations.BeforeMethod;



import java.io.IOException;


import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class TranslinkTest extends TranslinkPage {
 
	TranslinkPage lp = new TranslinkPage();
//	DataFiles df = new DataFiles();
	
  @BeforeMethod
  public void beforeMethod() throws IOException {
	  //step-1
	  lp.openBrowser(null);	  	  
  }
  
 @Test (priority=1)
  public void firstScenarioTest() throws IOException, InterruptedException {
	  //step-2
	 lp.selectBus();
	 //step-3
	 Assert.assertEquals(lp.verifyBusPage(), "Bus Schedules");
	 //step-4
	 lp.scrollToInputArea();
	 //step-5
	 lp.inputAndSearchBus();
	 //step-6
	 lp.selectOneResultFromList();
	 //step-7
	 lp.enterDateTime();
	 //step-8
	 lp.selectStops();
	 //step-9
	 lp.addToFavorite();
	 //step-10
	 lp.renameAddToFavourite();
	 //step-11
	 lp.clickManageFavouriteBtn();
	 Assert.assertEquals(lp.manageFavouriteValidation(), "99 UBC B-Line – Morning Schedule");
	
		  	  
  }
  
 @Test (priority=2)
 	public void secondscenarioTest() throws InterruptedException {
	 //step-2
	 lp.selectBus();
	 //step-3
	 Assert.assertEquals(lp.verifyBusPage(), "Bus Schedules");
	 //step-4
	 lp.scrollToInputArea();
	 //step-5
	 lp.inputAndSearchBus();
	 //step-6
	 lp.selectOneResultFromList();
	 //step-7
	 lp.enterDateTime();
	 //step-8 -  //step-1-scenario-2
	 lp.selectStops();
	 
	 //step-2-scenario-2
	 lp.validateFourStamps();
}


 

  @AfterMethod
  public void afterMethod() {
	  
	  lp.closeBrowser();
  }

}
