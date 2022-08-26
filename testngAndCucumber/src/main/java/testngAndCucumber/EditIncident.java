package testngAndCucumber;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class EditIncident extends ProjectSpecificMethods {
	
	@DataProvider(name="edit")
	public String[] fetchData() {
		
		String[] data = new String [4];
		//1st data
		data[0]="Edited Via Automation";
		data[1]="Abel Tuter";
		data[2]="In Progress";
		data[3]="1-High";
		
		return data;
	}
	
	@Test
	public void editIncident(String editedDesc, String callerId, String State, String Urgency) throws InterruptedException {
		
		WebElement eleFrame= shadow.findElementByXPath(("//iframe[@title='Main Content']"));
		driver.switchTo().frame(eleFrame);
		
		//4. Search for the existing incident and click on the incident
		driver.findElement(By.xpath("(//input[@class='form-control'])[1]")).sendKeys(text2,Keys.ENTER);
		driver.findElement(By.xpath("//th[@name='number']/following::a[@class='linked formlink']")).click();
		
		//5. Update the incident with Urgency as High and State as In Progress
		WebElement eleCallerID = driver.findElement(By.id("sys_display.incident.caller_id"));
		eleCallerID.clear();
		eleCallerID.sendKeys(callerId,Keys.TAB);
		//select urgency
		WebElement dd = driver.findElement(By.xpath("//select[@id='incident.urgency']"));
		WebElement option = driver.findElement(By.id("//option[text()='"+Urgency+"']"));
		dd.click();
		action = new Actions(driver);
		action.moveToElement(option).click().build().perform();
		
		//select state
		WebElement dd2 = driver.findElement(By.id("//select[@id='incident.state']"));
		WebElement option = driver.findElement(By.id("//option[text()='"+State+"']"));
		dd2.click();
		action = new Actions(driver);
		action.moveToElement(option).click().build().perform();
		
		//enter description
		WebElement eleShortDesc = driver.findElement(By.id("incident.short_description"));
		eleShortDesc.clear();
		eleShortDesc.sendKeys(editedDesc);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[text()='Update']")).click();	//click update
		Thread.sleep(2000);
		//6. Verify the update information is available in the incident
		
	}
}
