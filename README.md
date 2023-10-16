<h1 align="center"> SISTEMA DE CONTROLE DE FUNCIONÁRIOS </h1>
<P align="center"> DISCIPLINA DE BANCO DE DADOS 2023/2 </P>
<P align="center"> PROFESSOR HOWARD ROATTI </P>

<h4 align="center">    
 :construction:  Projeto em construção  :construction:
</h4>

## Índice 
* [Descrição do Projeto](#pushpin-descrição-do-projeto)
* [Pessoas Desenvolvedoras do Projeto](#pushpin-desenvolvedores)
* [Tecnologias utilizadas](#%EF%B8%8F-tecnologias-utilizadas)
* [Organização do Projeto](#pushpin-organização-do-projeto)
* [Demonstração da Aplicação](#pushpin-demonstração-da-aplicação)
* [Contato](#pushpin-contatos)

## :pushpin: Descrição do Projeto:
Esse sistema foi desenvolvido como parte da disciplina de Banco de Dados 2023/2 ministrada pelo Professor Howard Rotti. Ele consiste em um conjunto de tabelas projetadas para a gestão de informações relacionadas aos funcionários, sendo as principais tabelas deste projeto: "funcionarios" e "departamentos".

Quando a aplicação é executada, o sistema automaticamente gera todas as tabelas e estabelece os relacionamentos necessários.
<br>

## :pushpin: Desenvolvedores:
| [<img src="https://avatars.githubusercontent.com/u/110741308?v=4" width=115><br><sub>Lucas Detemann</sub>](https://github.com/Detemann) |  [<img src="https://avatars.githubusercontent.com/u/147534346?v=4" width=115><br><sub>Natalia Tayar</sub>](https://github.com/tayarnat) | [<img src="https://avatars.githubusercontent.com/u/105672201?v=4" width=115><br><sub>Wanderson Gonçalves</sub>](https://github.com/Wandersontr01) |
| :---: | :---: | :---: |

<br>


## ✔️ Tecnologias Utilizadas
| ![Java](https://github.com/Detemann/employee_manegement/assets/105672201/b6497e63-3185-4d1a-9add-265914adefe4)<br><sub>Java</sub> |  ![SQL](https://github.com/Detemann/employee_manegement/assets/105672201/4674d324-f393-4b73-b196-884608a84049)<br><sub>SQL</sub> | <img src="https://icons.iconarchive.com/icons/papirus-team/papirus-apps/48/intellij-icon.png" width="48" height="48"><br><sub>IntelliJ IDEA</sub> | ![Oracle Database](https://github.com/Detemann/employee_manegement/assets/105672201/bbd69044-52d4-4d8c-b756-841317d5f20c)<br><sub>Oracle Database</sub> | ![Oracle Database](https://github.com/Detemann/employee_manegement/assets/105672201/1a0f0f85-017e-4ca8-8088-9d0a7f53fe40)<br><sub>Orientação a Objetos</sub> |
| :-----: | :-----: | :-----: | :-----: | :-----: |


<br>



## :pushpin: Organização do Projeto:
- [Diagrams](xxxxxxx): Nesse diretório está o [diagrama relacional](xxxxxxxxxxxxx) (lógico) do sistema.
- [sql](com/src/main/resources/sql): Nesse diretório estão os scripts para criação das tabelas e inserção de dados fictícios para testes do sistema.
  * Certifique-se de que o usuário do banco possui todos os privilégios antes de executar os scripts de criação, caso ocorra erro, execute o comando a seguir com o superusuário via SQL Developer: `GRANT ALL PRIVILEGES TO LABDATABASE;`
  * [CreateTables.sql](com/src/main/resources/sql/CreateTables.sql): script responsável pela criação das tabelas.
  * [AlterTabela.sql](com/src/main/resources/sql/AlterTable.sql): script responsável pela criação dos relacionamentos.
  * [InsertData.sql](com/src/main/resources/sql/InsertData.sql): script responsável pela inserção dos registros fictícios para testes do sistema.
- [main](com/src/main/java/sarrussys/main): Nesse diretório estão os scripts do sistema:
  * [Conexão](com/src/main/java/sarrussys/main/database/ConexaoDB.java): Nesse repositório encontra-se o [módulo](com/src/main/java/sarrussys/main/database/ConexaoDB.java) de conexão com o banco de dados Oracle.
    - Exemplo de utilização para consultas simples:<br>
      ```Java
       public List<String> relatorioDepartamentoChefe(){
        List<String> resultado = new ArrayList<>();
        try {
            ResultSet consulta = this.databaseServices.fazerConsulta("SELECT DEPARTAMENTO.NOME AS Nome_Departamento, FUNCIONARIO.NOME AS Nome_Chefe\n" +
                    "FROM DEPARTAMENTO\n" +
                    "LEFT JOIN FUNCIONARIO ON DEPARTAMENTO.ID_CHEFE = FUNCIONARIO.ID_FUNCIONARIO");
            while(consulta.next()) {
                resultado.add(consulta.getString("NOME_DEPARTAMENTO"));
                resultado.add(consulta.getString("NOME_CHEFE"));
            }
            if(!resultado.isEmpty()){//se estiver cheia retorna a lista se não, retorna null
                return resultado;
            }else{
                return null;
            }
        }catch (SQLException e) {
            System.out.println("[RelatorioService] Ocorreu um erro inesperado: /n"+e.getMessage());
            return null;
        }
      }
   
  
  * [Modelos](com/src/main/java/sarrussys/main/model): Nesse diretório encontram-ser as classes das entidades descritas no diagrama relacional.<br>
  * [Services](com/src/main/java/sarrussys/main/services): Nesse diretório encontra-se a [classe](com/src/main/java/sarrussys/main/services/RelatorioServices.java) responsável por gerar todos os relatórios do sistema.<br>
  * [controller](com/src/main/java/sarrussys/main/controllers): Nesse diretório encontram-sem as classes controladoras, responsáveis por realizar inserção, alteração e exclusão dos registros das tabelas.
<br>

## :pushpin: Demonstração da Aplicação:

## :pushpin: Contatos:

| <img src="https://avatars.githubusercontent.com/u/110741308?v=4" width=115><br><sub>Lucas Detemann</sub><br> [![Linkedin: Lucas](https://img.shields.io/badge/-Linkedin-blue?style=flat-square&logo=Linkedin&logoColor=white)](https://www.linkedin.com/in/lucasdetemann/) [![Hotmail: Lucas](https://img.shields.io/badge/-Email-blue?%23E4405F?style=flat-square&logo=microsoftoutlook&logoColor=white)](mailto:lucas.deteman@aluno.faesa.br) | <img src="https://avatars.githubusercontent.com/u/147534346?v=4" width=115><br><sub>Natalia Tayar</sub><br> [![Linkedin: Natalia](https://img.shields.io/badge/-Linkedin-blue?style=flat-square&logo=Linkedin&logoColor=white)](https://www.linkedin.com/in/natalia-tayar-302577251/) [![Hotmail: Lucas](https://img.shields.io/badge/-Email-blue?%23E4405F?style=flat-square&logo=microsoftoutlook&logoColor=white)](mailto:natalia.tayar@aluno.faesa.br) | <img src="https://avatars.githubusercontent.com/u/105672201?v=4" width=115><br><sub>Wanderson Gonçalves</sub><br> [![Linkedin: Wanderson](https://img.shields.io/badge/-Linkedin-blue?style=flat-square&logo=Linkedin&logoColor=white)](https://www.linkedin.com/in/wandersonfg/) [![Hotmail: Wanderson](https://img.shields.io/badge/-Email-blue?%23E4405F?style=flat-square&logo=microsoftoutlook&logoColor=white)](mailto:wanderson.f.g@hotmail.com) |
| :---: | :---: | :---: | 
