/* Será necessário criar um novo schema(esquema) no banco de dados com o nome ENTERPRISE
   Depois rodar esses comando a baixo na ordem
*/

ALTER USER ENTERPRISE quota unlimited on USERS;

GRANT CREATE SESSION TO ENTERPRISE;