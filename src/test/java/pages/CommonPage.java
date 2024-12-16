package test.java.pages;

import org.openqa.selenium.WebDriver;

public class CommonPage {

    public WebDriver driver;

    public CommonPage(WebDriver driver){
        this.driver = driver;
    }

    public String lerNomeDaGuia(){
        return driver.getTitle();
    }
}
