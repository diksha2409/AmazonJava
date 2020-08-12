package StepDefinitions;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.ExcelUtils;

public class AmazonLoginStep {
	WebDriver driver=null;
	String projectpath = System.getProperty("user.dir");


	@Given("browser is open")
	public void browser_is_open() {
		System.out.println("Inside step- browser is open");
		System.setProperty("webdriver.chrome.driver", projectpath+"\\src\\test\\resources\\drivers\\chromedriver\\chromedriver.exe");
		driver=new ChromeDriver();

		//System.setProperty("webdriver.gecko.driver", projectpath+"\\src\\test\\resources\\drivers\\firefoxdriverr\\geckodriver.exe");
		//driver=new FirefoxDriver();			

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		//driver.manage().window().maximize();
		
	}

	@And("user is on login page")
	public void user_is_on_login_page() {
		driver.get("https://www.amazon.in/");
		driver.findElement(By.id("nav-hamburger-menu")).click();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);

		JavascriptExecutor je = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.linkText("Sign In"));

		je.executeScript("arguments[0].scrollIntoView(true);",element);
		element.click();
	}

	@When("user enters username and password")
	public void user_enters_username_and_password() {
		String expectedMessage = "Email or mobile phone number";
		String message = driver.findElement(By.className("a-form-label")).getText();
		Assert.assertTrue("Your error message", message.contains(expectedMessage));
		Assert.assertEquals(message, expectedMessage);
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.findElement(By.id("ap_email")).sendKeys("----");
		driver.findElement(By.id("continue")).click();

		String expectedMessage1 = "Password";
		String message1 = driver.findElement(By.className("a-form-label")).getText();
		Assert.assertTrue("Your error message", message1.contains(expectedMessage1));
		Assert.assertEquals(message1, expectedMessage1);
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.findElement(By.id("ap_password")).sendKeys("----");
	}

	@And("user clicks on login button")
	public void user_clicks_on_login_button() {
		driver.findElement(By.id("signInSubmit")).click();
	}

	@Then("user navigated to the home page")
	public void user_navigated_to_the_home_page() throws InterruptedException {
		driver.findElement(By.className("nav-line-2")).isDisplayed();
	}

	@When("user search the product")
	public void user_search_the_product() {
		
		WebElement product = driver.findElement(By.id("twotabsearchtextbox"));
		product.click();
		
		product.sendKeys("ZAFEX Matte Finish Full Size Carrom Board with Coins Striker and Boric Powder, Beige (Large 32 inch)");
		product.sendKeys(Keys.ENTER);

		String expectedMessage2 = "ZAFEX Matte Finish Full Size Carrom Board with Coins Striker and Boric Powder, Beige (Large 32 inch)";
		String message2 = driver.findElement(By.xpath("//span[text()='ZAFEX Matte Finish Full Size Carrom Board with Coins Striker and Boric Powder, Beige (Large 32 inch)']")).getText();
		Assert.assertTrue("Your error message", message2.contains(expectedMessage2));
		Assert.assertEquals(message2, expectedMessage2);
	}

	@When("select the valid product from search results")
	public void select_the_valid_product_from_search_results() {
		driver.findElement(By.xpath("//img[@alt='ZAFEX Matte Finish Full Size Carrom Board with Coins Striker and Boric Powder, Beige (Large 32 inch)']")).click();

		String price = driver.findElement(By.xpath("//span[text()='2,399']")).getText();
		int limit = 1;
		price = price.replace("$","");
		int priceValue = Integer.parseInt(price.substring(0,price.indexOf(",")));
		System.out.println("PriceValue : "+priceValue);
		if( limit > priceValue)
			System.out.println("It is less than 1000");
		else
			System.out.println("It is greater than 1000");

	}

	@When("user clicks on add to cart button")
	public void user_clicks_on_add_to_cart_button() {
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		Set<String> ids = driver.getWindowHandles();
		Iterator<String> it = ids.iterator();
		String parentId = it.next();
		String childId = it.next();
		driver.switchTo().window(childId);
		driver.findElement(By.id("add-to-cart-button")).click();
	}

	@Then("verify add to card with selected product")
	public void verify_add_to_card_with_selected_product_and_quantity() {
		driver.findElement(By.id("hlb-view-cart-announce")).click();
		String expectedMessage3 = "ZAFEX Matte Finish Full Size Carrom Board with Coins Striker and Boric Powder, Beige (Large 32 inch)";
		String message3 = driver.findElement(By.xpath("//span[contains(text(),'ZAFEX Matte Finish Full Size Carrom Board with Coins Striker and Boric Powder, Beige (Large 32 inch)')]")).getText();
		Assert.assertTrue("Your error message", message3.contains(expectedMessage3));
		Assert.assertEquals(message3, expectedMessage3);
	}

	@When("user clicks on signout button")
	public void user_clicks_on_signout_button() throws Exception {
		Actions a= new Actions(driver);
		WebElement ele=driver.findElement(By.xpath(".//*[@id='nav-link-accountList']"));
		a.moveToElement(ele).build().perform();
		driver.findElement(By.xpath(".//*[@id='nav-al-your-account']"));
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[text()='Sign Out']")).click();
	}

	@Then("user navigates to login page")
	public void user_navigates_to_login_page() throws Exception {
		String expectedMessage4 = "Login";
		String message4 = driver.findElement(By.xpath("//h1[contains(text(),'Login')]")).getText();
		Assert.assertTrue("Your error message", message4.contains(expectedMessage4));
		Assert.assertEquals(message4, expectedMessage4);

	}

}
