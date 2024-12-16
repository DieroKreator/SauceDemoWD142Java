package test.java.pages;

public class CommonPage {

    public WebDriver driver;

    public CommonPage(WebDriver driver){
        this.driver = driver;
    }

    public String lerNomeDaGuia(){
        return driver.getTitle();
    }
}
