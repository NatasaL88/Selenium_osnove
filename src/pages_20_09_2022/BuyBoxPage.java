package pages_20_09_2022;


	import java.util.List;
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.interactions.Actions;
	import org.openqa.selenium.support.ui.Select;
	import org.openqa.selenium.support.ui.WebDriverWait;

	public class BuyBoxPage {
		private WebDriver driver;
		private WebDriverWait wait;
		String link;
		
		public BuyBoxPage(WebDriver driver, WebDriverWait wait) {
			this.driver = driver;
			this.wait = wait;
		}
//		input za kolicinu
		public WebElement getQtyInput() {
			return driver.findElement(By.id("quantity_wanted"));
		}
//		select za velicinu
		public Select getSizeSelect() {
			return new Select(this.driver.findElement(By.id("group_1")));
			  
		}
//		add to cart dugme
		public WebElement getAddToCartBtn() {
			return driver.findElement(By.xpath("//*[@name = 'Submit']"));
		}
//		add to wishlist dugme
		public WebElement getAddToWishlistBtn() {
			return driver.findElement(By.id("wishlist_button"));
		}
//		metodu koja vraca element boje. 
		public WebElement getColorPickElement() {
			return this.driver.findElement(By.xpath("//ul[@id='color_to_pick_list']/li[@class = 'selected']/a"));
		}
//		Metoda kao parametar prima naziv boje 
//		(npr: “Orange”, “Blue”) a vraca link koji ima atribut title	prema trazenoj vrednosti.
		public WebElement getColorLink(String color) {
			return this.driver.findElement(By.xpath("//ul[@id='color_to_pick_list']//a[@title='" + color +"']"));
		}

//		metodu koja skrola do ovog dela stranice
		public void scrollToElement() {
			new Actions(driver)
			.scrollToElement(driver.findElement(By.className("box-info-product")))
			.perform();;
		}
		public WebElement getProductPrice() {
			return driver.findElement(By.id("our_price_display"));
		}
		

	}



