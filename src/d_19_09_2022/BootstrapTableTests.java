package d_19_09_2022;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BootstrapTableTests {
//	Base url: https://s.bootsnipp.com
//	First Name: ime polaznika
//	Last Name: prezime polaznika
//	Middle Name: srednje ime polanzika
	
	private WebDriver driver;
	private WebDriverWait wait;
	private String baseUrl = "https://s.bootsnipp.com";
	

	@BeforeClass
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));

}
	
	@BeforeMethod
	public void beforeMethod() {
		driver.get(baseUrl);
	}
//	Ucitati stranu /iframe/K5yrx
//	Verifikovati naslov stranice Table with Edit and Update Data - Bootsnipp.com
//	Klik na Edit dugme prvog reda
//	Sacekati da dijalog za Editovanje bude vidljiv
//	Popuniti formu podacima. 
//	Bice potrebno da pre unosa tekst pobrisete tekst koji vec postoji, za to se koristi metoda clear. Koristan link
//	Klik na Update dugme
//	Sacekati da dijalog za Editovanje postane nevidljiv
//	Verifikovati da se u First Name celiji prvog reda tabele javlja uneto ime
//	Verifikovati da se u Last Name celiji prvog reda tabele javlja uneto prezime
//	Verifikovati da se u Middle Name celiji prvog reda tabele javlja uneto srednje ime
//	Za sve validacije ispisati odgovarajuce poruke u slucaju greske

	@Test(priority = 10)
	public void editRow() {
		driver.get(baseUrl + "/iframe/K5yrx");
		Assert.assertEquals(driver.getTitle(),"Table with Edit and Update Data - Bootsnipp.com");
		
	driver.findElement(By.xpath("//button[contains(@class, 'update')]")).click();
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='edit']//*[contains(@class, 'modal-content')]")));
	
	driver.findElement(By.id("fn")).clear();
	driver.findElement(By.id("fn")).sendKeys("Natasa");
	
	driver.findElement(By.id("ln")).clear();
	driver.findElement(By.id("ln")).sendKeys("Lazarevic");
	
	driver.findElement(By.id("mn")).clear();
	driver.findElement(By.id("mn")).sendKeys("Mirko");
	
	driver.findElement(By.id("up")).click();
	
	assertEquals(driver.findElement(By.id("f1")).getText(),"Natasa","Incorrect firstname.");
	assertEquals(driver.findElement(By.id("l1")).getText(), "Lazarevic","Incorrect lastname.");
	assertEquals(driver.findElement(By.id("m1")).getText(),"Mirko", "Incorrect middlename.");	
	}
//	Ucitati stranu /iframe/K5yrx
//	Verifikovati naslov stranice Table with Edit and Update Data - Bootsnipp.com
//	Klik na Delete dugme prvog reda
//	Sacekati da dijalog za brisanje bude vidljiv
//	Klik na Delete dugme iz dijaloga 
//	Sacekati da dijalog za Editovanje postane nevidljiv
//	Verifikovati da je broj redova u tabeli za jedan manji
//	Za sve validacije ispisati odgovarajuce poruke u slucaju greske
	
	@Test(priority = 20)
	public void deleteRow() {
		driver.get(baseUrl + "/iframe/K5yrx");
		
		Assert.assertEquals(driver.getTitle(),"Table with Edit and Update Data - Bootsnipp.com");
		driver.findElement(By.xpath("//button[contains(@class, 'delete')]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='delete']")));
		driver.findElement(By.id("del")).click();
		Assert.assertFalse(driver.findElement(By.xpath("//tbody/tr[1]")).isDisplayed(),"ERROR: Number of rows is not change.");
		
	}
	
//	Ucitati stranu  /iframe/K5yrx
//	Verifikovati naslov stranice Table with Edit and Update Data - Bootsnipp.com
//	Kreirati screenshot stranice. Koristan link https://www.guru99.com/take-screenshot-selenium-webdriver.html
//	Fajl cuvajte na putanji gde su vam bile slike od proslog domaceg. Na putanji: src/paket_za_domaci/nazivslike.png

	@Test(priority = 30)
	public void TakeAScreenshot() throws IOException {
		driver.get(baseUrl + "/iframe/K5yrx");
		Assert.assertEquals(driver.getTitle(),"Table with Edit and Update Data - Bootsnipp.com");
		TakesScreenshot scrShot =((TakesScreenshot)driver);
		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
		 File DestFile=new File("img/screenshot.jpg");
		 FileUtils.copyFile(SrcFile, DestFile);
	}
		
		
	
	

	@AfterMethod
	public void afterMethod() {
		System.out.println("After Method");
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	
	
	
	
	
	
	}
	
