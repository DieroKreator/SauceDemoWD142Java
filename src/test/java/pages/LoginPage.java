package test.java.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends CommonPage{
    
    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    
    public By byLocal(String local){

        return By.cssSelector("option[value=\"" + local + "\"]");
    }

    @FindBy(cssSelector = "[data-test=\"username\"]")
    WebElement usernameField;

    @FindBy(cssSelector = "[data-test=\"password\"]")
    WebElement passwordField;

    @FindBy(cssSelector = "[data-test=\"login-button\"]")
    WebElement loginBtn;

    public void acessarLoginPage(String url){
        driver.get(url);
    }

    protected void preencherUsername(){
        usernameField.sendKeys("standard_user");
    }

    protected void preencherPassword(){
        usernameField.sendKeys("secret_sauce");
    }

    public void clicarBotãoLogin(){
        loginBtn.click();
    }
}
