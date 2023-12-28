# Sistema de Gerenciamento de Almoxarifado em Java

## Descrição do Projeto
Este projeto é um sistema de gerenciamento de almoxarifado desenvolvido em Java para empresas que precisam controlar a entrada e saída de materiais de forma eficiente e segura.

## Problema
Empresas de todos os tamanhos enfrentam desafios ao gerenciar a entrada e saída de materiais em seus almoxarifados. Este processo, se não for bem gerenciado, pode levar a ineficiências operacionais e perda de recursos.

## Solução
Desenvolvemos um software que simplifica o processo de entrada e saída de materiais em um almoxarifado. Com nossa solução, as empresas podem facilmente rastrear e gerenciar seus inventários, resultando em operações mais eficientes e economia de recursos.

## Tecnologias Utilizadas
O software foi desenvolvido usando Java, uma linguagem de programação amplamente utilizada que oferece robustez e portabilidade. Para a interface do usuário, escolhemos Swing, uma biblioteca gráfica para Java que permite a criação de interfaces gráficas ricas e responsivas. Para o gerenciamento de dados, utilizamos MySQL, um sistema de gerenciamento de banco de dados relacional de código aberto que oferece desempenho, confiabilidade e facilidade de uso.

## Como utilizar o software? 

### Tela inicial
1. **Aplicação**: O usuário irá escolher para qual setor o item sairá/entrará, por exemplo: "administrativo"
   <img src="https://github.com/CouTech-Ficr/Projeto-java-ficr/assets/122830909/d8f20436-cd94-4780-966f-634a76e79eb3" alt="image" width="130"/>
   
2. **Nome do Requisitante**: O usuário irá escanear o crachá/digitar matrícula ou nome do funcionário.
   <img src="https://github.com/CouTech-Ficr/Projeto-java-ficr/assets/122830909/da6b0c37-ab5b-4bc6-8073-070f9736c00c" alt="funcionario Cadastro" width="130" /> 
   
3. **Tipo**: O usuário irá escolher se está retirando ou armazenando o produto. 
   <img src="https://github.com/CouTech-Ficr/Projeto-java-ficr/assets/122830909/88296e0e-ecd1-4975-a595-32ecf1980560" alt="tipo" width="130"/> 
   
4. **Código de Barras do Produto / Quantidade**: O usuário deverá escanear o sku do produto/digitar e selecionar a quantidade.
   <img src="https://github.com/CouTech-Ficr/Projeto-java-ficr/assets/122830909/4442301d-29a6-4c22-bac6-5df5fc941a3a" alt="codigoProduto" width="130" />
   
5. **Finalizar**: Se tudo ocorrer bem, o programa exibirá uma mensagem de sucesso. 

### Desbloqueio do histórico
O usuário necessitará da senha gerada pelo administrador, acesso restrito a funcionários autorizados.
!Histórico

### Erros
1. Se o usuário tentar prosseguir sem preencher os dados gerais.
   <img src="https://github.com/CouTech-Ficr/Projeto-java-ficr/assets/122830909/bc37184e-281e-4925-98a6-81cadf74d964" alt="erroPreenchimento" width="130" /> 
   
2. Se o usuário inserir a senha errada no histórico. 
   <img src="https://github.com/CouTech-Ficr/Projeto-java-ficr/assets/122830909/a91412f1-bcaf-4cdd-a8ed-7bda2286b45f" alt="SenhaErrada" width="130" /> 
   
3. Se o usuário tentar deletar algo do histórico sem selecionar a linha do item em questão.
   <img src="https://github.com/CouTech-Ficr/Projeto-java-ficr/assets/122830909/61088a60-a5d4-41ae-a4a4-f92006896f19" alt="DeletarLInha" width="130" /> 

### Primeiros passos: 
```bash
git clone https://github.com/CouTech-Ficr/Projeto-java-ficr.git
