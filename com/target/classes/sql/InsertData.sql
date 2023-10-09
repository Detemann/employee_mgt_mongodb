-- Inserindo dois registros de funcionários na tabela FUNCIONARIO
INSERT INTO FUNCIONARIO (NOME, CPF, EMAIL, SALARIO_BRUTO, SALARIO_LIQUIDO, ID_DEPARTAMENTO)
VALUES ('João Silva', '12345678901', 'joao@email.com', 5000.00, 4000.00, 1);

INSERT INTO FUNCIONARIO (NOME, CPF, EMAIL, SALARIO_BRUTO, SALARIO_LIQUIDO)
VALUES ('Maria Santos', '98765432101', 'maria@email.com', 6000.00, 4800.00);

-- Inserindo dois registros de departamentos na tabela DEPARTAMENTO
INSERT INTO DEPARTAMENTO (NOME, SIGLA, ID_CHEFE)
VALUES ('Departamento de Vendas', 'VD', 1);

INSERT INTO DEPARTAMENTO (NOME, SIGLA)
VALUES ('Departamento de TI', 'TI');