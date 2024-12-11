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
    }

    @E("confirmo o pedido")
    public void confirmo_o_pedido() {
    }

    @E("preencho as informações pessoais")
    public void preencho_as_informações_pessoais() {
    }

    @E("valido as informações do pedido")
    public void valido_as_informações_do_pedido() {
    }

    @Quando("clico no botão de confirmação")
    public void clico_no_botão_de_confirmação() {
    }

    @Entao("o pedido é gerado com sucesso")
    public void o_pedido_é_gerado_com_sucesso() {
    }

}
