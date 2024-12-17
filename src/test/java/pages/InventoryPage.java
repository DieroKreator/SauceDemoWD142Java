package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InventoryPage extends CommonPage{

    String nomeProduto;

    public InventoryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public By byLocal(String local) {

        return By.cssSelector("option[value=\"" + local + "\"]");
    }

    // WebElement itemLabel = driver.findElement(By.xpath("//div[@data-test='inventory-item-name' and text()='"
    //                                                     + nomeProduto + "']"));

    // public void selecionarProduto(String nomeProduto) {
    //     itemLabel.click();
    // }
}
