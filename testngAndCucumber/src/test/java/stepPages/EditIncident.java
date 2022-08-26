package stepPages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.sukgu.Shadow;

public class EditIncident extends ProjectSpecificMethods{
	
	@Given ("Search existing incident number to edit")
	public void searchIncidentNum() {
		shadow.setImplicitWait(20);
		WebElement eleFrame= shadow.findElementByXPath(("//iframe[@title='Main Content']"));
		driver.switchTo().frame(eleFrame);
		driver.findElement(By.xpath("(//input[@class='form-control'])[1]")).sendKeys(text2,Keys.ENTER);
	}
	
	@When ("click incident number to edit")
	public void clickIncidentNum() {
		driver.findElement(By.xpath("//th[@name='number']/following::a[@class='linked formlink']")).click();
	}
	@And ("Enter caller by clearing the existing value (.*)$")
	public void enterCallerValue(String callerId) {
		WebElement eleCallerID = driver.findElement(By.id("sys_display.incident.caller_id"));
		eleCallerID.clear();
		eleCallerID.sendKeys(callerId,Keys.TAB);
	}

	@And ("select Urgency and select (.*)$")
	public void selectUrgency(String Urgency) {
		WebElement dd = driver.findElement(By.xpath("//select[@id='incident.urgency']"));
		dd.click();
		WebElement option = driver.findElement(By.xpath("//option[text()='"+Urgency+"']"));
		action = new Actions(driver);
		action.moveToElement(option).click().build().perform();	
	}
	@And ("select State and select (.*)$")
	public void selectState(String State) {
		WebElement dd2 = driver.findElement(By.xpath("//select[@id='incident.state']"));
		dd2.click();
		WebElement option = driver.findElement(By.xpath("//option[text()='"+State+"']"));
		action = new Actions(driver);
		action.moveToElement(option).click().build().perform();
	}
	@And ("enter Short description as (.*)$")
	public void enterShortDesc(String ShortDescription) {
		WebElement eleShortDesc = driver.findElement(By.id("incident.short_description"));
		eleShortDesc.clear();
		eleShortDesc.sendKeys(ShortDescription);
	}
	@Then ("click Update")
	public void clickUpdate() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[text()='Update']")).click();	
	}
	
}
