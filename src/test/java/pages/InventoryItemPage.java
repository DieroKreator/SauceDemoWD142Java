package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InventoryItemPage extends CommonPage{

    public InventoryItemPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public By byLocal(String local) {

        return By.cssSelector("option[value=\"" + local + "\"]");
    }

    @FindBy(id = "add-to-cart")
    WebElement addBtn;

    @FindBy(css = "*[data-test='remove']")
    WebElement removeBtn;

    @FindBy(css = "*[data-test='shopping-cart-badge']")
    WebElement cartBadge;

    @FindBy(css = "*[data-test='shopping-cart-link']")
    WebElement cartLink;

    public void clicarBotãoAddToCart(){
        addBtn.click();
        removeBtn.isDisplayed();
    }

    public void clicarLinkCart(){
        cartLink.click();
    }

}
