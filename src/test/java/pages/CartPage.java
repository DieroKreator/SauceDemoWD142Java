package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends CommonPage{

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public By byLocal(String local) {

        return By.cssSelector("option[value=\"" + local + "\"]");
    }

    @FindBy(css = "button[data-test='checkout']")
    WebElement checkoutBtn;

    public void clicarBotãoCheckout(){
        checkoutBtn.click();
    }

}
