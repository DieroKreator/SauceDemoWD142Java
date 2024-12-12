package steps;

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

public class CompraProdutoBDD {

    private WebDriver driver;

    @Dado("que acesso o site {string}")
    public void que_acesso_o_site(String url) {
        driver.get(url);
    }

    @E("faço login")
    public void faço_login() {
        WebElement usernameField = driver.findElement(By.cssSelector("*[data-test=\"username\"]"))
                .sendKeys("standard_user");
        WebElement password = driver.findElement(By.cssSelector("*[data-test=\"password\"]")).sendKeys("secret_sauce");

        WebElement loginBtn = driver.findElement(By.cssSelector("*[data-test=\"login-button\"]")).click();

        assertEquals(driver.findElement(By.cssSelector("*[data-test=\"title\"]")).getText(), "Products");
    }

    @E("seleciono o produto <produto> com valor <preço>")
    public void seleciono_o_produto_produto_com_valor_preço(String nomeProduto, String preço) {
        WebElement itemLabel = driver
                .findElement(By.xpath("//div[@class='inventory_item_name' and text()='" + nomeProduto + "']"));
        WebElement itemPrice = driver
                .findElement(By.xpath("//div[@class='inventory_item_price' and text()='" + preço + "']"));
        assertEquals(itemLabel.getText(), nomeProduto);
        assertEquals(itemPrice.getText(), preço);

        itemLabel.click();

        assertEquals(driver.findElement(By.cssSelector("*[data-test=\"back-to-products\"]")).getText(),
                "Back to products");
        assertEquals(itemLabel.getText(),
                nomeProduto);
        assertEquals(itemPrice.getText(), preço);
    }

    @E("adiciono ao carrinho")
    public void adiciono_ao_carrinho() {

        WebElement addBtn = driver.findElement(By.cssSelector("*[data-test=\"add-to-cart\"]"));
        addcartBtn.click();

        WebElement removeBtn = driver.findElement(By.cssSelector("*[data-test=\"remove\"]"));
        removeBtn.isDisplayed();

        assertEquals(driver.findElement(By.cssSelector("*[data-test=\"shopping-cart-badge\"]")).getText(), "1");

        WebElement cartLink = driver.findElement(By.cssSelector("*[data-test=\"shopping-cart-link\"]"));
        cartLink.click();

        assertEquals(driver.findElement(By.cssSelector("*[data-test=\"title\"]")).getText(), "Your Cart");
        assertEquals(driver.findElement(By.cssSelector("*[data-test=\"item-quantity\"]")).getText(), "1");
    }

    @E("confirmo o pedido do item <produto> com valor <preço>")
    public void confirmo_o_pedido_do_item_item_com_valor_preço(String nomeProduto, String preço) {

        WebElement itemLabel = driver
                .findElement(By.xpath("//div[@class='inventory_item_name' and text()='" + nomeProduto + "']"));
        WebElement itemPrice = driver
                .findElement(By.xpath("//div[@class='inventory_item_price' and text()='" + preço + "']"));

        assertEquals(itemLabel.getText(), nomeProduto);
        assertEquals(itemPrice.getText(), preço);

        WebElement checkoutBtn = driver
                .findElement(By.css("button[data-test=\"checkout\"]"));
        checkoutBtn.click();

        assertEquals(driver.findElement(
                By.cssSelector("*[data-test=\"title\"]")).getText(), "Checkout: Your Information");
    }

    @E("preencho as informações pessoais")
    public void preencho_as_informações_pessoais() {

        WebElement firstnameField = driver.findElement(By.id("first-name"));
        firstnameField.sendKeys("Usuario");

        WebElement lastnameField = driver.findElement(By.id("last-name"));
        lastnameField.sendKeys("Test");

        WebElement zipcodeField = driver.findElement(By.id("postal-code"));
        lastnameField.sendKeys("92158485");

        WebElement continueBtn = driver
                .findElement(By.id("continue"));
        checkoutBtn.click();

        assertEquals(driver.findElement(
                By.cssSelector("*[data-test=\"title\"]")).getText(), "Checkout: Overview");
    }

    @E("valido as informações do pedido com <produto> e <preço>")
    public void valido_as_informações_do_pedido_com_produto_e_preço(String nomeProduto, String preço) {

        WebElement itemLabel = driver
                .findElement(By.xpath("//div[@class='inventory_item_name' and text()='" + nomeProduto + "']"));
        WebElement itemPrice = driver
                .findElement(By.xpath("//div[@class='inventory_item_price' and text()='" + preço + "']"));

        assertEquals(itemLabel.getText(), nomeProduto);
        assertEquals(itemPrice.getText(), preço);
    }

    @Quando("clico no botão de confirmação")
    public void clico_no_botão_de_confirmação() {

        WebElement finishBtn = driver
                .findElement(By.id("finish"));
        finishBtn.click();

        assertEquals(driver.findElement(
                By.cssSelector("*[data-test=\"title\"]")).getText(), "Checkout: Complete!");
    }

    @Entao("o pedido é gerado com sucesso")
    public void o_pedido_é_gerado_com_sucesso() {
        assertEquals(driver.findElement(
                By.cssSelector("*[data-test=\"complete-header\"]")).getText(), "Thank you for your order!");
    }

}
