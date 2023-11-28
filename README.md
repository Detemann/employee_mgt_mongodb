<h1 align="center"> SISTEMA DE CONTROLE DE FUNCIONÁRIOS </h1>
<P align="center"> DISCIPLINA DE BANCO DE DADOS 2023/2 </P>
<P align="center"> PROFESSOR HOWARD ROATTI </P>


## Índice 
* [Descrição do Projeto](#pushpin-descrição-do-projeto)
* [Pessoas Desenvolvedoras do Projeto](#pushpin-desenvolvedores)
* [Tecnologias utilizadas](#%EF%B8%8F-tecnologias-utilizadas)
* [Organização do Projeto](#pushpin-organização-do-projeto)
* [Demonstração da Aplicação](#pushpin-demonstração-da-aplicação)
* [Executando a Aplicação](#pushpin-executando-a-aplicação)
* [Contato](#pushpin-contatos)

## :pushpin: Descrição do Projeto:
Esse sistema foi desenvolvido como parte da disciplina de Banco de Dados 2023/2 ministrada pelo Professor Howard Rotti. Ele consiste em um conjunto de coleções projetadas para a gestão de informações relacionadas aos funcionários, sendo as principais deste projeto: "funcionarios" e "departamentos".

Todas as coleções estão previamente cadastradas no banco.
<br>

## :pushpin: Desenvolvedores:
| [<img src="https://avatars.githubusercontent.com/u/110741308?v=4" width=115><br><sub>Lucas Detemann</sub>](https://github.com/Detemann) |  [<img src="https://avatars.githubusercontent.com/u/147534346?v=4" width=115><br><sub>Natalia Tayar</sub>](https://github.com/tayarnat) | [<img src="https://avatars.githubusercontent.com/u/105672201?v=4" width=115><br><sub>Wanderson Gonçalves</sub>](https://github.com/Wandersontr01) |
| :---: | :---: | :---: |

<br>



## ✔️ Tecnologias Utilizadas
| ![Java](assets/java_64.png)<br><sub>Java</sub> | ![IntelliJ IDEA](assets/intellij_64.png)<br><sub>IntelliJ IDEA</sub> | ![MongoDB](assets/mongodb_64.png)<br><sub>MongoDB</sub> | ![Orientacao a Objeto](assets/cod_64.png)<br><sub>Orientação a Objetos</sub> |
| :-----: | :-----: | :-----: | :-----: |


<br>



## :pushpin: Organização do Projeto:
- [Diagrams](DiagramaRelacional): Nesse diretório está o [diagrama relacional](DiagramaRelacional/DiagramaRelacional.pdf) (lógico) do sistema.
  * O sistema possui duas entidades: FUNCIONARIO E DEPARTAMENTO

- [main](com/src/main/java/sarrussys/main): Nesse diretório estão os scripts do sistema:
  * [Conexão](com/src/main/java/sarrussys/main/database/): Nesse repositório encontra-se o [módulo](com/src/main/java/sarrussys/main/database/ConexaoMongoDB.java) de conexão com o banco de dados MongoDB.
    - Exemplo de utilização para consultas simples:<br>
      ```Java
      public Funcionario buscarFuncionarioPorId(int id) {
        try (MongoCursor<Document> funcionarios = database.getMongoDatabase().getDatabase("employees")
                .getCollection("employee")
                .find(eq("_id", id)).cursor()) {
      
            return (Funcionario) utils.populate(funcionarios, utils.fabricate(1, Funcionario.class)).get(0);
        } catch (Exception e) {
            System.out.println("[DepartamentoRepository] "+e.getMessage());
            return null;
        }
      }
   
  
  * [Modelos](com/src/main/java/sarrussys/main/model): Nesse diretório encontram-ser as classes das entidades descritas no diagrama relacional.<br>
  * [Services](com/src/main/java/sarrussys/main/services): Nesse diretório encontra-se a [classe](com/src/main/java/sarrussys/main/services/RelatorioServices.java) responsável por gerar todos os relatórios do sistema.<br>
  * [controller](com/src/main/java/sarrussys/main/controllers): Nesse diretório encontram-sem as classes controladoras, responsáveis por realizar inserção, alteração e exclusão dos registros das tabelas.
<br>

## :pushpin: Demonstração da Aplicação

Assista a demonstrações em vídeo da aplicação para entender seu funcionamento. Clique nos links abaixo para acessar os vídeos:

- [![Video_Demonstracao: Video](https://img.shields.io/badge/-Video_Completo-red?style=flat-square&logo=Youtube&logoColor=white)](https://www.youtube.com/watch?v=JYDa-CZDsbU&ab_channel=Wandersontr)

- [![Video_Demonstracao: Video](https://img.shields.io/badge/-Relatorios-red?style=flat-square&logo=Youtube&logoColor=white)](https://youtu.be/JYDa-CZDsbU?t=81)
  
- [![Video_Demonstracao: Video](https://img.shields.io/badge/-Inserir_Registros-red?style=flat-square&logo=Youtube&logoColor=white)](https://youtu.be/JYDa-CZDsbU?t=117)

- [![Video_Demonstracao: Video](https://img.shields.io/badge/-Remover_Registros-red?style=flat-square&logo=Youtube&logoColor=white)](https://youtu.be/JYDa-CZDsbU?t=223)

- [![Video_Demonstracao: Video](https://img.shields.io/badge/-Atualizar_Registros-red?style=flat-square&logo=Youtube&logoColor=white)](https://youtu.be/JYDa-CZDsbU?t=243)


## :pushpin: Executando a Aplicação

**Sobre a Aplicação:**
O Sistema de Controle de Funcionários é uma aplicação para gerenciar informações de funcionários em uma organização. Siga as etapas abaixo para executar a aplicação.

### Passo 1: Download do Arquivo .JAR

- Faça o download do arquivo .JAR clicando [aqui](https://github.com/Detemann/employee_mgt_mongodb/releases/download/release/SistemaGerenciamento.jar).
- Certifique-se de que o arquivo seja baixado na pasta de downloads ou em um local de fácil acesso.

### Passo 2: Instale o Java

- Para executar o Sistema de Controle de Funcionários, você precisa ter o **Java 11 ou uma versão superior** instalada no seu sistema.
- Caso você não tenha o Java instalado, você pode fazer o download do Java [aqui](https://www.java.com/pt-BR/download/manual.jsp).

### Passo 3: Executando a Aplicação

- Abra o terminal ou prompt de comando no seu sistema.
- Navegue até a pasta onde o arquivo .JAR foi baixado. Você pode fazer isso usando o comando "cd" para mudar o diretório.
- Execute o seguinte comando para iniciar a aplicação:
   ```shell
   ~$ java -jar SistemaGerenciamento.jar
   ```
 
 Isso iniciará a aplicação de controle de funcionários. Siga as instruções na interface da aplicação para começar a gerenciar as informações dos funcionários.

### Problemas Comuns e Soluções:

  - PROBLEMA: Erro de compatibilidade Java
     * Certifique-se de que você está usando o Java 11 ou uma versão superior. Você pode verificar a versão do Java no terminal com o comando java -version.



## :pushpin: Contatos:

| <img src="https://avatars.githubusercontent.com/u/110741308?v=4" width=115><br><sub>Lucas Detemann</sub><br> [![Linkedin: Lucas](https://img.shields.io/badge/-Linkedin-blue?style=flat-square&logo=Linkedin&logoColor=white)](https://www.linkedin.com/in/lucasdetemann/) [![Hotmail: Lucas](https://img.shields.io/badge/-Email-blue?%23E4405F?style=flat-square&logo=microsoftoutlook&logoColor=white)](mailto:lucas.deteman@aluno.faesa.br) | <img src="https://avatars.githubusercontent.com/u/147534346?v=4" width=115><br><sub>Natalia Tayar</sub><br> [![Linkedin: Natalia](https://img.shields.io/badge/-Linkedin-blue?style=flat-square&logo=Linkedin&logoColor=white)](https://www.linkedin.com/in/natalia-tayar-302577251/) [![Hotmail: Lucas](https://img.shields.io/badge/-Email-blue?%23E4405F?style=flat-square&logo=microsoftoutlook&logoColor=white)](mailto:natalia.tayar@aluno.faesa.br) | <img src="https://avatars.githubusercontent.com/u/105672201?v=4" width=115><br><sub>Wanderson Gonçalves</sub><br> [![Linkedin: Wanderson](https://img.shields.io/badge/-Linkedin-blue?style=flat-square&logo=Linkedin&logoColor=white)](https://www.linkedin.com/in/wandersonfg/) [![Hotmail: Wanderson](https://img.shields.io/badge/-Email-blue?%23E4405F?style=flat-square&logo=microsoftoutlook&logoColor=white)](mailto:wanderson.f.g@hotmail.com) |
| :---: | :---: | :---: | 



