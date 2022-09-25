package pages_20_09_2022;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TopMenuPage {
	private WebDriver driver;
	private WebDriverWait wait;
	String link;

	public TopMenuPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}

//	TopMenuPage koja pribavlja:
//		metodu koja vraca WOMEN link iz glavnom menija
	public WebElement getWomenLink() {
		return driver.findElement(By.xpath("//*[@id='block_top_menu']/ul/li[1]/a"));
	}

//		metodu koja vraca DRESSES link iz glavnom menija
	public WebElement getDressesLink() {
		return driver.findElement(By.xpath("//*[@id='block_top_menu']/ul/li[2]/a"));
	}

//		metodu koja vraca T-SHIRTS link iz glavnom menija
	public WebElement getTshitsLink() {
		return driver.findElement(By.xpath("//*[@id='block_top_menu']/ul/li[3]/a"));
	}

//		metodu koja vraca podmeni za WOMEN
	public WebElement getWomenSubmenu() {
		return driver.findElement(By.xpath("//*[@id='block_top_menu']/ul/li[1]/ul"));
	}

//		metodu koja vraca podmeni za DRESSES
	public WebElement getDressesSubmenu() {
		return driver.findElement(By.xpath("//*[@id='block_top_menu']/ul/li[2]/ul"));
	}

//	metodu koja ceka da dijalog bude vidljiv
	public void waitWomanModuleVisability() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='block_top_menu']/ul/li[1]/ul")));
	}

	public void waitDressesModuleVisability() {
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\\\"block_top_menu\\\"]/ul/li[2]/ul")));
	}

//	metodu koja ceka da dijalog bude nevidljiv
	public void waitDressesModuleInvisability() {
		wait.until(
				ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[2]/ul")));
	}

}
