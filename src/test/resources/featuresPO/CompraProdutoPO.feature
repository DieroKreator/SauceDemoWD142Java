# language: pt
Funcionalidade: Compra Produto PO
    Selecionar e comprar um produto

  Esquema do Cenario: Comprar um produto com sucesso PO
    Dado que acesso o site "https://www.saucedemo.com/"
    E faço login PO
    E seleciono o produto <produto> com valor <preço> PO
    E adiciono ao carrinho PO
    E confirmo o pedido do item <produto> com valor <preço> PO
    E preencho as informações pessoais PO
    E valido as informações do pedido com <produto> e <preço> PO
    Quando clico no botão de confirmação PO
    Entao o pedido é gerado com sucesso PO

    Exemplos:
      | produto                    | preço    |
      | "Sauce Labs Backpack"      | "$29.99" |
      | "Sauce Labs Onesie"        | "$7.99"  |
      | "Sauce Labs Bike Light"    | "$9.99"  |
      | "Sauce Labs Fleece Jacket" | "$49.99" |
