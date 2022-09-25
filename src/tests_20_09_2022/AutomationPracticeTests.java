package tests_20_09_2022;
import java.time.Duration;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages_20_09_2022.BuyBoxPage;
import pages_20_09_2022.HeaderPage;
import pages_20_09_2022.LayerCartPage;
import pages_20_09_2022.TopMenuPage;


public class AutomationPracticeTests {
	private WebDriver driver;
	private WebDriverWait wait;
	private String baseUrl = "http://automationpractice.com/";

	private BuyBoxPage buyBoxPage;
	private LayerCartPage layerCartPage;
	private TopMenuPage topMenuPage;
	private HeaderPage headerPage;
	private SoftAssert softAssert;

	@BeforeClass
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		this.driver = new ChromeDriver();

		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(180));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		buyBoxPage = new BuyBoxPage(driver, wait);
		layerCartPage = new LayerCartPage(driver, wait);
		topMenuPage = new TopMenuPage(driver, wait);
		headerPage = new HeaderPage(driver, wait);
		softAssert = new SoftAssert();
	}

	@BeforeMethod
	public void beforeMethod() {
		driver.navigate().to(baseUrl);
	}

	@Test(priority = 10)
	public void AddingProductToTheCart() {
//		Ucitati stranicu /index.php?id_product=1&controller=product
		driver.navigate().to(baseUrl + "/index.php?id_product=1&controller=product");
//				Odskrolati do buy box-a
		buyBoxPage.scrollToElement();
//				Postavite kolicinu na 3
		buyBoxPage.getQtyInput().clear();
		buyBoxPage.getQtyInput().sendKeys("3");
//				Izaberite velicinu L
		buyBoxPage.getSizeSelect().selectByIndex(2);
//				Izaberite plavu boju
		buyBoxPage.getColorLink("Blue").click();
//				Kliknite na dugme add to cart
		buyBoxPage.getAddToCartBtn().click();
//				Cekajte da dijalog layer cart bude vidljiv
		layerCartPage.waitModuleVisability();
//				Verifikovati da je kolicina iz layer cart dijalog 3
		Assert.assertEquals(layerCartPage.getQuantity().getText(), "3", "Error: the quantity should be 3.");
//				Verifikovati da je velicina L
		Assert.assertTrue(layerCartPage.getAttributes().getText().contains("L"), "Error: the size should be L.");
//				Verifikovati da je izabran proizvod sa plavom bojom
		Assert.assertTrue(layerCartPage.getAttributes().getText().contains("Blue"), "Error: the color should be blue.");
//				Verifikovati da je total price 3 puta veci od cene proizvoda
		Double actualValue = Double.valueOf(layerCartPage.getTotalPrice().getText().substring(1));
		Double expectedValue = Double.valueOf(buyBoxPage.getProductPrice().getText().substring(1))
				* Double.valueOf(layerCartPage.getQuantity().getText());
		Assert.assertEquals(actualValue, expectedValue, "Error: the price should be multiplied by 3.");
//				Kliknite na dugme continue shopping
		layerCartPage.getContinueShoppingBtn().click();
//				cekajte da dijalog layer cart postane nevidljiv
		layerCartPage.waitModuleInisability();
//				Izaberite novi proizvod sa kolicinom 1, velicinom S i bojom Organe
		buyBoxPage.getQtyInput().clear();
		buyBoxPage.getQtyInput().sendKeys("1");
		buyBoxPage.getSizeSelect().selectByIndex(0);
		buyBoxPage.getColorLink("Orange").click();
//				Kliknite na dugme add to cart
		buyBoxPage.getAddToCartBtn().click();
//				Cekajte da dijalog bude vidljiv
		layerCartPage.waitModuleVisability();
//				kliknite na dugme proceed to checkout
		layerCartPage.getProceedToCheckoutBtn().click();
//				Verifikujte da je naslov stranice Order - My Store
		Assert.assertEquals(driver.getTitle(), "Order - My Store", "Error: the page title is not expected.");

	}

	@Test(priority = 20)
	public void TopMenuMouseOver(){

//		Test #2:  Top menu mouse over
//	Predjite misem preko women linka. Koristan link kako da predjete misem preko nekog elementa link
		new Actions(driver)
        .moveToElement(topMenuPage.getWomenLink())
        .perform();
		
		topMenuPage.waitWomanModuleVisability();
//	Verifikujte da je podmeni za women deo vidljiv
		softAssert.assertTrue(topMenuPage.getWomenSubmenu().isDisplayed(), "Error: Women submenu should be visible.");
//	Predjite misem preko dresses linka. 
		new Actions(driver)
        .moveToElement(topMenuPage.getDressesLink())
        .perform();
//	Verifikujte da je podmeni za dresses deo vidljiv
		softAssert.assertTrue(topMenuPage.getDressesSubmenu().isDisplayed(), "Error: Dresses submenu should be visible.");
//	Predjite misem preko t-shirts linka. 
		new Actions(driver)
        .moveToElement(topMenuPage.getTshitsLink())
        .perform();
//	Verifikujte podmeniji za womens i dresses nevidljivi
		topMenuPage.waitDressesModuleInvisability();
		softAssert.assertFalse(topMenuPage.getWomenSubmenu().isDisplayed(), "Error: Women submenu should not be visible.");
		softAssert.assertFalse(topMenuPage.getDressesSubmenu().isDisplayed(), "Error: Dresses submenu should not be visible.");
//	Ukoliko je potrebno ukljucite odgovarajuca cekanja, kojim bi se sacekalo da stranica dodje u odgovarajuce stanje
//	Provera preko za vidljivost preko soft assert-a
		softAssert.assertAll();
	}

	@Test(priority = 30)
	public void PhoneNumberVisibilityCheckOnResize(){
//		Test #3:  Phone number visibility check on resize
//		Maksimizujte prozor
		driver.manage().window().maximize();
//		Proverite da je element za broj telefona vidljiv
		headerPage.waitShopPhoneVisibility();
		softAssert.assertTrue(headerPage.getShopPhoneElement().isDisplayed(), "Error: Phone should be visible when maximized");
//		Smanjite dimenziju pretrazivaca na velicinu 767 x 700
		driver.manage().window().setSize(new Dimension(767, 700));
//		Proverite element za broj telefona nije vidljiv
		softAssert.assertFalse(headerPage.getShopPhoneElement().isDisplayed(), "Error: Phone should not be visible when at 767x700");
//		Promenite dimenziju pretrazivaca na 768 x 700
		driver.manage().window().setSize(new Dimension(768, 700));
//		Proverite da je broj telefona vidljiv
		softAssert.assertTrue(headerPage.getShopPhoneElement().isDisplayed(), "Error: Phone should be visible at 768x700");
//		Maksimizujte prozor
		driver.manage().window().maximize();
//		Provera preko soft asserta
		softAssert.assertAll();

	}

	@Test(priority = 40)
	public void HeaderLinksCheck() {
//		Test #4:  Header links check
//		Kliknite na contact us link
		headerPage.getContactUsLink().click();
//		Verifikujte da je naslov stranice Contact us - My Store
		softAssert.assertEquals(driver.getTitle(), "Contact us - My Store", "Error: the page title is not expected.");
//		Kliknite na sign in link
		headerPage.getSignInLink().click();
//		Verifikujte da je naslov stranice Login - My Store
		softAssert.assertEquals(driver.getTitle(), "Login - My Store", "Error: the page title is not expected.");
//		Provera preko soft asserta
		softAssert.assertAll();

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
