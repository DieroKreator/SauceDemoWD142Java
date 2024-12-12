package steps;

import static org.junit.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

public class CompraProdutoBDD {

        private WebDriver driver;

        @Before
        public void iniciar() {
                driver = new ChromeDriver();
                driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
                driver.manage().window().maximize();
        }

        @After
        public void finalizar() {
                driver.quit();
        }

        @Dado("que acesso o site {string}")
        public void que_acesso_o_site(String url) {
                driver.get(url);
        }

        @E("faço login")
        public void faço_login() {

                WebElement usernameField = driver.findElement(By.cssSelector("*[data-test=\"username\"]"));
                usernameField.sendKeys("standard_user");

                WebElement password = driver.findElement(By.cssSelector("*[data-test=\"password\"]"));
                password.sendKeys("secret_sauce");

                WebElement loginBtn = driver.findElement(By.cssSelector("*[data-test=\"login-button\"]"));
                loginBtn.click();

                assertEquals(driver.findElement(By.cssSelector("*[data-test=\"title\"]")).getText(), "Products");
        }

        @E("seleciono o produto {string} com valor {string}")
        public void seleciono_o_produto_com_valor(String nomeProduto, String preço) {

                WebElement itemLabel = driver
                                .findElement(By.xpath("//div[@class='inventory_item_name' and text()='" + nomeProduto
                                                + "']"));
                WebElement itemPrice = driver
                                .findElement(By.xpath(
                                                "//div[@class='inventory_item_price' and text()='" + preço + "']"));
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
                addBtn.click();

                WebElement removeBtn = driver.findElement(By.cssSelector("*[data-test=\"remove\"]"));
                removeBtn.isDisplayed();

                assertEquals(driver.findElement(By.cssSelector("*[data-test=\"shopping-cart-badge\"]")).getText(), "1");

                WebElement cartLink = driver.findElement(By.cssSelector("*[data-test=\"shopping-cart-link\"]"));
                cartLink.click();

                assertEquals(driver.findElement(By.cssSelector("*[data-test=\"title\"]")).getText(), "Your Cart");
                assertEquals(driver.findElement(By.cssSelector("*[data-test=\"item-quantity\"]")).getText(), "1");
        }

        @E("confirmo o pedido do item {string} com valor {string}")
        public void confirmo_o_pedido_do_item_com_valor(String nomeProduto, String preço) {

                WebElement itemLabel = driver
                                .findElement(By.xpath("//div[@class='inventory_item_name' and text()='" + nomeProduto
                                                + "']"));
                WebElement itemPrice = driver
                                .findElement(By.xpath(
                                                "//div[@class='inventory_item_price' and text()='" + preço + "']"));

                assertEquals(itemLabel.getText(), nomeProduto);
                assertEquals(itemPrice.getText(), preço);

                WebElement checkoutBtn = driver.findElement(By.cssSelector("button[data-test='checkout']"));
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

                WebElement continueBtn = driver.findElement(By.id("continue"));
                continueBtn.click();

                assertEquals(driver.findElement(
                                By.cssSelector("*[data-test=\"title\"]")).getText(), "Checkout: Overview");
        }

        @E("valido as informações do pedido com {string} e {string}")
        public void valido_as_informações_do_pedido_com_e(String string, String string2) {
                
                WebElement itemLabel = driver
                                .findElement(By.xpath("//div[@class='inventory_item_name' and text()='" + nomeProduto
                                                + "']"));
                WebElement itemPrice = driver
                                .findElement(By.xpath(
                                                "//div[@class='inventory_item_price' and text()='" + preço + "']"));

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
                                By.cssSelector("*[data-test=\"complete-header\"]")).getText(),
                                "Thank you for your order!");
        }

}
