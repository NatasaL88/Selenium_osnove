package d_15_09_2022;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class zadatak2 {

	public static void main(String[] args) {
//		Napisati program koji ucitava stranicu https://geodata.solutions/
//			Bira Country, State i City po vasoj zelji
//			Pritom potrebno je izvrsiti cekanje da se povaje State-ovi nakon izbora Country-a
//			I takodje je potrebno izvrsiti cekanje da se ucitaju gradovi nakon izbora State-a
//			Izabrerit Country, State i City tako da imate podatke da selektujete!

		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		
		driver.get("https://geodata.solutions/");
		//ne radi sajt
		driver.quit();
	}

}
