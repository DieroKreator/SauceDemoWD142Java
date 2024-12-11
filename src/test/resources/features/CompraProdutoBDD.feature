# language: pt
Funcionalidade: Compra Produto
    Selecionar e comprar um produto

  Esquema do Cenario: Comprar um produto com sucesso DDT
    Dado que acesso o site "https://www.saucedemo.com/"
    E faço login
    E seleciono o produto <produto> com valor <preço>
    E adiciono ao carrinho
    E confirmo o pedido do item <produto> com valor <preço>
    E preencho as informações pessoais
    E valido as informações do pedido
    Quando clico no botão de confirmação
    Entao o pedido é gerado com sucesso

    Exemplos:
      | produto               | preço    |
      | "Sauce Labs Backpack" | "$29.99" |
      | "Sauce Labs Onesie"   | "$7.99"  |
