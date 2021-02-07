# PROVA NÍVEL III
## Tecnologias mínimas que devem ser utilizadas:

* Banco de dados PostgreSQL

• Java 8+

• Maven

• Spring

• JPA

• Bean Validation

• QueryDSL

• REST com JSON

### Requisitos da Prova:

• Deverá ser desenvolvido um cadastro (Create/Read/Update/Delete/List com paginação)
para as seguintes entidades: produto/serviço, pedido e itens de pedido.

• Deverá ser possível aplicar filtros na listagem

• As entidades deverão utilizar Bean Validation

• Deverá ser implementado um ControllerAdvice para customizar os HTTP Response das
requisições (mínimo BAD REQUEST)

• Todos as entidades deverão ter um ID único do tipo UUID gerado automaticamente

• No cadastro de produto/serviço deverá ter uma indicação para diferenciar um produto de
um serviço

• Deverá ser possível aplicar um percentual de desconto no pedido, porém apenas para os
itens que sejam produto (não serviço); o desconto será sobre o valor total dos produtos

• Somente será possível aplicar desconto no pedido se ele estiver na situação Aberto
(Fechado bloqueia)

• Não deve ser possível excluir um produto/serviço se ele estiver associado a algum pedido

• Não deve ser possível adicionar um produto desativado em um pedido

### Critérios de aceitação

• A prova deverá ser entregue completa (todos os itens resolvidos)

• Deverão ser criados testes automatizados

• O código não poderá ter erros de compilação

• Deverá haver uma documentação mínima de como executar o projeto e suas
funcionalidades

## Gerando o pacote
Sendo um projeto Maven, execute os goals clean e install na raiz do projeto para baixar as dependências e gerar o jar do projeto.


```bash
$ mvn clean install

```

## Exemplos

http://localhost:9000/api-pedido/v1/produtos/salvar

```
{
	"nome": "Cama King size",
	"descricao": "cama/mesa/banho",
	"valor": "1.0",
	"tipo": "PRODUTO",
	"ativo":true
}

```

retorno 200: 
```
{
    "id": "d239789d-aa31-4d4d-9142-fa812c88f3f9",
    "nome": "Cama King size",
    "descricao": "cama/mesa/banho",
    "valor": 1,
    "tipo": "PRODUTO",
    "ativo": true
}
```
http://localhost:9000/api-pedido/v1/pedidos/salvar

```
{
	"situacaoPedido":"ABERTA",
	"itensPedidos": [
		{
			"quantidade":"2",
			"produtoServico": 
			{
				"id": "d239789d-aa31-4d4d-9142-fa812c88f3f9",
				"nome": "Sofa",
				"descricao": "Item de decoração",
				"valor": 280,
				"tipo": "PRODUTO",
				"ativo": true
			}
		}
	],
	"desconto":"2"
}
```

retorno OK:

```
{
    "id": "b5aa0e32-a86f-495b-8159-8fe8c36fc4fa",
    "dataPedido": "2021-02-07T22:02:11.783+00:00",
    "dataAtualizacao": null,
    "situacaoPedido": "ABERTA",
    "itensPedidos": [
        {
            "id": "e13d29ea-e514-4b58-9216-b65e59a1a2c5",
            "quantidade": 2,
            "produtoServico": {
                "id": "d239789d-aa31-4d4d-9142-fa812c88f3f9",
                "nome": "Sofa",
                "descricao": "Item de decoração",
                "valor": 280,
                "tipo": "PRODUTO",
                "ativo": true
            }
        }
    ],
    "desconto": 2,
    "totalSemDesconto": 560,
    "totalComDesconto": 548.8
}

```



