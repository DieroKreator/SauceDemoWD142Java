import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SelectProductTest {

  private WebDriver driver;

  @BeforeEach
  public void setUp() {
    driver = new ChromeDriver();
    driver.get("https://www.saucedemo.com/");
    driver.manage().window().maximize();
  }

  @AfterEach
  public void tearDown() {
    driver.quit();
  }

  @Test
  public void selecionarProduto() {
    driver.findElement(By.cssSelector("*[data-test=\"username\"]")).sendKeys("standard_user");
    driver.findElement(By.cssSelector("*[data-test=\"password\"]")).sendKeys("secret_sauce");
    driver.findElement(By.cssSelector("*[data-test=\"login-button\"]")).click();
    assertEquals(driver.findElement(By.cssSelector("*[data-test=\"title\"]")).getText(), "Products");
    driver.findElement(By.cssSelector("*[data-test=\"inventory-item-name\"]")).click();
    assertEquals(driver.findElement(By.cssSelector("*[data-test=\"back-to-products\"]")).getText(), "Back to products");
    assertEquals(driver.findElement(By.cssSelector("*[data-test=\"inventory-item-name\"]")).getText(), "Sauce Labs Backpack");
    assertEquals(driver.findElement(By.cssSelector("*[data-test=\"inventory-item-price\"]")).getText(), "$29.99");
    driver.findElement(By.cssSelector("*[data-test=\"add-to-cart\"]")).click();
    assertEquals(driver.findElement(By.cssSelector("*[data-test=\"shopping-cart-badge\"]")).getText(), "1");
    driver.findElement(By.cssSelector("*[data-test=\"shopping-cart-link\"]")).click();
    assertEquals(driver.findElement(By.cssSelector("*[data-test=\"title\"]")).getText(), "Your Cart");
    assertEquals(driver.findElement(By.cssSelector("*[data-test=\"item-quantity\"]")).getText(), "1");
    assertEquals(driver.findElement(By.cssSelector("*[data-test=\"inventory-item-name\"]")).getText(), "Sauce Labs Backpack");
    assertEquals(driver.findElement(By.cssSelector("*[data-test=\"inventory-item-price\"]")).getText(), "$29.99");
    driver.findElement(By.cssSelector("*[data-test=\"remove-sauce-labs-backpack\"]")).click();
    driver.findElement(By.id("react-burger-menu-btn")).click();
    
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(4000));
    // driver.findElement(By.cssSelector("*[data-test=\"logout-sidebar-link\"]")).click();
    WebElement logoutLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("*[data-test=\"logout-sidebar-link\"]")));
    logoutLink.click();

    driver.close();
  }
}