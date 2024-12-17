package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Checkout1Page extends CommonPage{

    public Checkout1Page(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public By byLocal(String local) {

        return By.cssSelector("option[value=\"" + local + "\"]");
    }

    @FindBy(id = "first-name")
    WebElement firstNameField;

    @FindBy(id = "last-name")
    WebElement lastNameField;

    @FindBy(id = "postal-code")
    WebElement zipcodeField;

    @FindBy(id = "continue")
    WebElement continueBtn;

    public void preencherNome(String nome){
        firstNameField.sendKeys(nome);
    }

    public void preencherSobrenome(String sobreNome){
        passwordField.sendKeys(sobreNome);
    }

    public void preencherCodigoPostal(String codigoPostal){
        zipcodeField.sendKeys(codigoPostal);
    }

    public void clicarBotãoContinue(){
        continueBtn.click();
    }

}
