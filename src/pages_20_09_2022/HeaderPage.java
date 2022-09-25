package pages_20_09_2022;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HeaderPage {
	private WebDriver driver;
	private WebDriverWait wait;
	public HeaderPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}
//	shop phone element - gde je prikazan broj telefona
	public WebElement getShopPhoneElement() {
		return driver.findElement(By.xpath("//span[@class='shop-phone']"));
	}
//	contact us link
	public WebElement getContactUsLink() {
		return driver.findElement(By.xpath("//*[@id='contact-link']/a"));
	}
//	sign in link
	public WebElement getSignInLink() {
		return driver.findElement(By.className("login"));
	}
	public void waitShopPhoneVisibility() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("shop-phone")));
	}

}
