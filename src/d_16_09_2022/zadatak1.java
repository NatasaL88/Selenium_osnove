package d_16_09_2022;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class zadatak1 {

	public static void main(String[] args) throws InterruptedException {
		
//		implicitno cekanje za trazenje elemenata od 10s
//		implicitno cekanje za ucitavanje stranice od 10s
//		eksplicitno cekanje podeseno na 10s
//		Podaci:
//		Potrebno je u projektu ukljuciti 4 slike.
//		Imenovanje slika neka bude po pravilu pozicija_ime_prezime_polaznika.ekstenzija
//		Npr: front_milan_jovanovic.jpg, left_milan_jovanovic.jpg 

	
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		File back = new File("selenium_osnove/img/back_natasa_lazarevic.jpg");
        File front = new File("selenium_osnove/img/front_natasa_lazarevic.jpg");
        File left = new File("selenium_osnove/img/left_natasa_lazarevic.jpg");
        File right = new File("selenium_osnove/img/right_natasa_lazarevic.jpg");
        
//        Ucitava stranicu https://boomf.com/apps/proxy/boomf-bomb/i-bloody-love-you
//        	Maksimizuje prozor
//        	Selektuje Image - Front klikom na tu karticu u dnu ekrana
//        	Klik na add photo iz levog navigacionog menia
//        	Upload slike. Upload preko File objekta koristeci getAbsolutePath
//        	Sacekati da broj prikazanih slika u donjem uglu navigacije bude za 1 veca
//        	Kliknuti na poslednje dodatu sliku kako bi bila izabrana za postavljanje
//        	Ceka da dijalog bude vidljiv
//        	Klik na Use One Side Only dugme
//        	Ceka da se pojavi dijalog sa slikom
//        	Klik na Done
//        	Ponoviti proces za Left, Right i Back
//        	Zatim iz desnog gornjeg ugla izabrati random jedan od ponudjenih konfeta
//        	Kliknuti na Add To Cart dugme
//        	Verifikovati postojanje greske Oops! It looks like you`ve not added an text to this field,
//        	please add one before continuing.
//        	Xpath: //*[@action='error']
//        	Zatvorite pretrazivac
        
        driver.get("https://boomf.com/apps/proxy/boomf-bomb/i-bloody-love-you");
        driver.manage().window().maximize();
        driver.findElement(By.className("fendym")).click();
        driver.findElement(By.xpath("//div[text() = '+ Add photo']")).click();
        driver.findElement(By.xpath("//input[@type ='file']")).sendKeys(front.getAbsolutePath());
        
        wait.until(ExpectedConditions.numberOfElementsToBe(By.className("sc-ftvSup"), 1));

        driver.findElement(By.xpath("//div[contains(@class, 'gmXCBU')]/img[last()]")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'sc-iXxrte')]")));

        driver.findElement(By.xpath("//button[text() ='Use One Side Only']")).click();
        driver.findElement(By.xpath("//img[@alt = 'image 2']")).click();
        driver.findElement(By.xpath("//div[text() ='+ Add photo']")).click();
        driver.findElement(By.xpath("//input[@type ='file']")).sendKeys(left.getAbsolutePath());
        
        wait.until(ExpectedConditions.numberOfElementsToBe(By.className("haLJiC"), 2));
        
        driver.findElement(By.xpath("//*[contains(@class, 'sc-ftvSup')]/div[2]/div/img")).click();
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By .xpath("//div[contains(@class, 'dxCajp')]")));

        driver.findElement(By.xpath("//button[text() ='Use One Side Only']")).click();
        driver.findElement(By.xpath("//img[@alt = 'image 3']")).click();
        driver.findElement(By.xpath("//div[text() ='+ Add photo']")).click();
        driver.findElement(By.xpath("//input[@type ='file']")).sendKeys(back.getAbsolutePath());

        wait.until(ExpectedConditions.numberOfElementsToBe(By.className("haLJiC"), 3));

        driver.findElement(By.xpath("//*[contains(@class, 'sc-ftvSup')]/div[3]/div/img"))	.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'dxCajp')]")));

        driver.findElement(By.xpath("//button[text() ='Use One Side Only']")).click();
        driver.findElement(By.xpath("//img[@alt = 'image 4']")).click();
        driver.findElement(By.xpath("//div[text() ='+ Add photo']")).click();
        driver.findElement(By.xpath("//input[@type ='file']")).sendKeys(right.getAbsolutePath());

        wait.until(ExpectedConditions.numberOfElementsToBe(By.className("haLJiC"), 4));

        driver.findElement(By.xpath("//*[contains(@class, 'sc-ftvSup')]/div[4]/div/img")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'dxCajp')]")));

        driver.findElement(By.xpath("//button[text() ='Use One Side Only']")).click();

        Thread.sleep(2000);

        List<WebElement> zadatak = driver.findElements(By.xpath("//*[contains(@class, 'fajlzv')]/div"));

		Random r = new Random();
		int x = r.nextInt();
		zadatak.get(x).click();
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		Thread.sleep(2000);
		
		if (elementExist(driver, By.xpath("//*[@action='error']"))){
		    System.out.println("Greska se pojavila. ");
		} else {
		    System.out.println("Greska se nije pojavila. ");
		}
		
		driver.quit();
		}
		public static boolean elementExist(WebDriver driver, By by) {
		boolean elementExist = true;
		try {
		    driver.findElement(by);
		} catch (Exception e) {
		    elementExist = false;
		}
		return  elementExist;



     

}
}