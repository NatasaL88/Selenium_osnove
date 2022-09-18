package d_15_09_2022;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class zadatak1 {

	public static void main(String[] args) {
//		Ucitava stranicu https://s.bootsnipp.com/iframe/Dq2X
//			Klikce na svaki iks da ugasi obavestenje i proverava da li se nakon klika element obrisao sa stranice
//			i ispisuje odgovarajuce poruke (OVO JE POTREBNO RESITI PETLJOM)
//			POMOC: Brisite elemente odozdo.
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.get("https://s.bootsnipp.com/iframe/Dq2X");
		List<WebElement>listaX= driver.findElements(By.xpath("//button[contains(@class, 'close')]"));
		boolean elementPostoji = true;
		for (int i = listaX.size()-1; i > 0; i--) {
			listaX.get(i).click();	
			 try {
	                WebElement e = driver.findElement(By.xpath("//button[contains(@class, 'close')]"));
	                        elementPostoji= true;
	            }catch (Exception e){
	                elementPostoji = false;
	            }
	            if (elementPostoji) {
	                System.out.println("Element postoji.");
	            } else {
	                System.out.println("Element ne postoji.");
	            }
		}
		
		driver.quit();
			
	
		

	}

}
