package stepsPO;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.List;

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
import pages.Base;
import pages.LoginPage;
import pages.InventoryPage;
import pages.InventoryItemPage;
import pages.CartPage;
import pages.Checkout1Page;
import pages.Checkout2Page;
import pages.ConfirmationPage;

public class CompraProdutoPO {

        final WebDriver driver;
        private LoginPage loginPage;
        private InventoryPage inventoryPage;
        private InventoryItemPage inventoryItemPage;
        private CartPage cartPage;
        private Checkout1Page checkout1Page;
        private Checkout2Page checkout2Page;
        private ConfirmationPage confirmationPage;

        String nomeProduto;
        String preço;

        // Constructor
        public CompraProdutoPO(Base base) {
                this.driver = base.driver;
        }

        @Dado("que acesso o site {string} PO")
        public void que_acesso_o_site_po(String url) {
                loginPage = new LoginPage(driver);
                loginPage.acessarLoginPage(url);

                assertEquals("Swag Labs", loginPage.lerNomeDaGuia());
        }

        @E("faço login PO")
        public void faço_login_po() {

                loginPage = new LoginPage(driver);

                loginPage.preencherUsername();
                loginPage.preencherPassword();

                loginPage.clicarBotãoLogin();
                assertEquals(driver.findElement(By.cssSelector("*[data-test=\"title\"]")).getText(), "Products");
        }

        @Dado("seleciono o produto {string} com valor {string} PO")
        public void seleciono_o_produto_com_valor_po(String nomeProduto, String preço) {

                inventoryPage = new InventoryPage(driver);

                WebElement itemLabel;
                WebElement itemPrice;
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

                {
                        this.nomeProduto = nomeProduto;
                        itemLabel = driver
                                        .findElement(By.xpath("//div[@data-test='inventory-item-name' and text()='"
                                                        + nomeProduto + "']"));
                }

                {
                        this.preço = preço;

                        itemPrice = itemLabel.findElement(By.xpath(
                                        "./ancestor::div[@class='inventory_item']//div[@class='inventory_item_price']"));

                        // Retrieve and combine the price parts dynamically
                        String[] priceParts = itemPrice.getText().split("\n");
                        String fullPrice = String.join("", priceParts); // Combines all parts of the price

                        // Validate the full price
                        System.out.println("Price for " + nomeProduto + ": " + fullPrice);
                        if (fullPrice.equals(preço)) {
                                System.out.println("Price is correct.");
                        } else {
                                System.out.println("Price is incorrect. Found: " + fullPrice);
                        }

                }

                assertEquals(itemLabel.getText(), nomeProduto);
                assertEquals(itemPrice.getText(), preço);

                itemLabel.click();

                // Wait for the element to be visible and fetch its text
                WebElement backToProductsButton = wait.until(
                                ExpectedConditions.visibilityOfElementLocated(
                                                By.cssSelector("*[data-test='back-to-products']")));

                assertEquals(driver.findElement(By.cssSelector("*[data-test='back-to-products']")).getText(),
                                "Back to products");

                /* Check productname and price validation on inventory-item page */
                // assertEquals(itemLabel.getText(), nomeProduto);
                // assertEquals(itemPrice.getText(), preço);
        }

        @E("adiciono ao carrinho PO")
        public void adiciono_ao_carrinho_po() {

                inventoryItemPage = new InventoryItemPage(driver);

                inventoryItemPage.clicarBotãoAddToCart();

                assertEquals(driver.findElement(By.cssSelector("*[data-test='shopping-cart-badge']")).getText(),
                                "1");

                inventoryItemPage.clicarLinkCart();

                // Ativar a sincronização para o robô executar devagar
                // E podermos visualizar o funcionamento
                // Importante: É só como curiosidade ou em caso de problemas
                // O indicado é deixar o robô executar o mais rápido possível

                synchronized (driver) {
                        try {
                                driver.wait(1000);
                        } catch (Exception e) {
                                e.printStackTrace();
                        }
                }
        }

        public void delay(int milliseconds) {
                try {
                        Thread.sleep(milliseconds);
                } catch (InterruptedException e) {
                        e.printStackTrace();
                }
        }

        @E("confirmo o pedido do item {string} com valor {string} PO")
        public void confirmo_o_pedido_do_item_com_valor_po(String nomeProduto, String preço) {

                cartPage = new CartPage(driver);

                // assertEquals(cartPage.lerNomeDaGuia(), "Your Cart");
                assertEquals(driver.findElement(By.cssSelector("*[data-test='title']")).getText(), "Your Cart");
                assertEquals(driver.findElement(By.cssSelector("*[data-test='item-quantity']")).getText(), "1");

                WebElement itemLabel;
                WebElement itemPrice;
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

                {
                        this.nomeProduto = nomeProduto;
                        itemLabel = driver
                                        .findElement(By.xpath("//div[@data-test='inventory-item-name' and text()='"
                                                        + nomeProduto + "']"));
                }

                {
                        this.preço = preço;

                        itemPrice = driver.findElement(By.cssSelector("div[data-test='inventory-item-price']"));

                        // Retrieve and combine the price parts dynamically
                        String[] priceParts = itemPrice.getText().split("\n");
                        String fullPrice = String.join("", priceParts); // Combines all parts of the price

                        // Validate the full price
                        System.out.println("Price for " + nomeProduto + ": " + fullPrice);
                        if (fullPrice.equals(preço)) {
                                System.out.println("Price is correct.");
                        } else {
                                System.out.println("Price is incorrect. Found: " + fullPrice);
                        }

                }

                assertEquals(itemLabel.getText(), nomeProduto);
                assertEquals(itemPrice.getText(), preço);

                // WebElement checkoutBtn = driver.findElement(By.cssSelector("button[data-test='checkout']"));
                // checkoutBtn.click();
                cartPage.clicarBotãoCheckout();
        }

        @E("preencho as informações pessoais PO")
        public void preencho_as_informações_pessoais_po() {

                checkout1Page = new Checkout1Page(driver);
                assertEquals(driver.findElement(
                                By.cssSelector("*[data-test='title']")).getText(), "Checkout: Your Information");
                // assertEquals(checkout1Page.lerNomeDaGuia(), "Checkout: Your Information");

                checkout1Page.preencherNome("Charlie");

                checkout1Page.preencherSobrenome("Green");

                checkout1Page.preencherCodigoPostal("91210258");

                checkout1Page.clicarBotãoContinue();

        }

        @E("valido as informações do pedido com {string} e {string} PO")
        public void valido_as_informações_do_pedido_com_e_po(String nomeProduto, String preço) {

                assertEquals(driver.findElement(
                        By.cssSelector("*[data-test='title']")).getText(), "Checkout: Overview");

                WebElement itemLabel = driver
                                .findElement(By.xpath("//div[@class='inventory_item_name' and text()='" + nomeProduto
                                                + "']"));
                WebElement itemPrice;

                {
                        this.preço = preço;

                        itemPrice = driver.findElement(By.cssSelector("div[data-test='inventory-item-price']"));

                        // Retrieve and combine the price parts dynamically
                        String[] priceParts = itemPrice.getText().split("\n");
                        String fullPrice = String.join("", priceParts); // Combines all parts of the price

                        // Validate the full price
                        System.out.println("Price for " + nomeProduto + ": " + fullPrice);
                        if (fullPrice.equals(preço)) {
                                System.out.println("Price is correct.");
                        } else {
                                System.out.println("Price is incorrect. Found: " + fullPrice);
                        }

                }

                assertEquals(itemLabel.getText(), nomeProduto);
                assertEquals(itemPrice.getText(), preço);
        }

        @Quando("clico no botão de confirmação PO")
        public void clico_no_botão_de_confirmação_po() {

                WebElement finishBtn = driver
                                .findElement(By.id("finish"));
                finishBtn.click();

                assertEquals(driver.findElement(
                                By.cssSelector("*[data-test='title']")).getText(), "Checkout: Complete!");
        }

        @Entao("o pedido é gerado com sucesso PO")
        public void o_pedido_é_gerado_com_sucesso_po() {

                assertEquals(driver.findElement(
                                By.cssSelector("*[data-test='complete-header']")).getText(),
                                "Thank you for your order!");
        }

}
