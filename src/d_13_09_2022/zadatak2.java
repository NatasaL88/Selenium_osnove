package d_13_09_2022;


import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class zadatak2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
////		Maksimizirati prozor
//		Ucitati stranicu https://s.bootsnipp.com/iframe/WaXlr
//			Dohvatite dugmice za rejting kao listu. XPath za trazenje treba da bude preko id atributa i za ovo
//			trebace vam contains u xpath-u

		
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.get("https://s.bootsnipp.com/iframe/WaXlr");
		List<WebElement> zvezda = driver.findElements(By.xpath("//*[contains(@id,'rating-star')]"));
		
//		Od korisnika ucitati broj (preko scannera) na koju zvezdicu je potrebno kliknuti (u rasponu od 1 do 5)
		
		Scanner s = new Scanner(System.in);
		System.out.println("Unesite broj od 1 do 5: ");
		int broj = s.nextInt();
		
//		I izvrsite akciju klik na odgovarajuci element preko indeksa
//		Na kraju programa ugasite pretrazivac.
		
		
		zvezda.get(broj - 1).click();
		driver.quit();

		
		
		

		
		
	}

}
