package pages_20_09_2022;



	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;
	
	public class LayerCartPage {
		private WebDriver driver;
		private WebDriverWait wait;
		public LayerCartPage(WebDriver driver, WebDriverWait wait) {
			this.driver = driver;
			this.wait = wait;
		}
//		LayerCartPage koja pribavlja:
//			continue shopping dugme
		public WebElement getContinueShoppingBtn() {
			return driver.findElement(By.xpath("//*[@title = 'Continue shopping']"));
		}
//			proced to checkout dugme
		public WebElement getProceedToCheckoutBtn() {
			return driver.findElement(By.xpath("//*[@title = 'Proceed to checkout']"));
		}
//			element koji vraca atrubute
//			 proizvoda (sa slike je to desno od devojke)
		public WebElement getAttributes() {
			return driver.findElement(By.id("layer_cart_product_attributes"));
		}
		
//			element koji cuva quantity
//			(takodje desno od devojke)
		public WebElement getQuantity() {
			return driver.findElement(By.id("layer_cart_product_quantity"));
		}
//			element koji cuva total price
		public WebElement getTotalPrice() {
			return driver.findElement(By.id("layer_cart_product_price"));
		}
//			metodu koja ceka da dijalog bude vidljiv
		public void waitModuleVisability() {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("layer_cart")));
		}
//			metodu koja ceka da dijalog bude nevidljiv
		public void waitModuleInisability() {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("layer_cart")));
		}
		
}
