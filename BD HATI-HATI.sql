use master
drop database hati_hati

CREATE DATABASE hati_hati;
USE hati_hati;

CREATE TABLE usuario  
( 
 id_usuario BIGINT PRIMARY KEY IDENTITY,  
 cidade VARCHAR(58),  
 avaliacoes FLOAT,  
 nome_usuario VARCHAR(70),  
 estado VARCHAR(28),
 cep VARCHAR (8), 
 tipo_usuario BIT
); /*OK*/ 


CREATE TABLE contratante 
(
 id_contratante BIGINT PRIMARY KEY,
 constraint fk_contratante_usuario foreign key (id_contratante) references usuario(id_usuario) ON DELETE CASCADE

);  /*OK*/

CREATE TABLE prestador_de_servico 
( 
 id_prestador BIGINT PRIMARY KEY,  
 anexos VARCHAR (1000),  
 descricao VARCHAR (1000),  
 tipo_assinatura BIT,  
 constraint fk_prestador_usuario foreign key (id_prestador) references usuario(id_usuario) ON DELETE CASCADE

); /*OK*/ 


CREATE TABLE solicitacao 
( 
 id_solicitacao BIGINT PRIMARY KEY IDENTITY,  
 id_contratante BIGINT,  
 id_prestador BIGINT,  
 especificacao_solicitacao VARCHAR(1000),
 data_hora DATETIME,

 FOREIGN KEY(id_prestador) 
    REFERENCES prestador_de_servico(id_prestador),
 FOREIGN KEY(id_contratante) 
    REFERENCES contratante (id_contratante) 
); /*OK*/

CREATE TABLE agendamento 
( 
 status_agendamento VARCHAR (10),    
 id_contratante BIGINT,  
 id_prestador BIGINT,  
 id_agendamento BIGINT PRIMARY KEY IDENTITY,  
 data_hora_agendamento DATETIME,  
 local_servico  VARCHAR(90),  
 id_solicitacao BIGINT, 

 FOREIGN KEY(id_solicitacao) REFERENCES solicitacao (id_solicitacao), 
 FOREIGN KEY(id_prestador) REFERENCES prestador_de_servico (id_prestador), 
 FOREIGN KEY(id_contratante) REFERENCES contratante (id_contratante) 
);/*OK*/ 


CREATE TABLE servico 
( 
 id_contratante BIGINT,  
 especifica��o VARCHAR (1000),  
 id_prestador BIGINT,  
 status_servico VARCHAR (10),  
 id_servico BIGINT PRIMARY KEY IDENTITY, 
 

 FOREIGN KEY(id_prestador) 
    REFERENCES prestador_de_servico (id_prestador), 
 FOREIGN KEY(id_contratante) 
    REFERENCES contratante (id_contratante) 

); /*OK*/ 


CREATE TABLE calendario 
( 
 id_calendario BIGINT PRIMARY KEY IDENTITY,  
 id_agendamento BIGINT,
 FOREIGN KEY(id_agendamento) REFERENCES agendamento (id_agendamento)
 
); /*OK*/ 

CREATE TABLE vaga 
( 
 tipo_vaga BIT,  
 id_vaga BIGINT PRIMARY KEY IDENTITY,  
 descri��o_servi�o_vaga VARCHAR(1000),  
 data_hora_vaga DATETIME,  
 prestador_requerido VARCHAR (50),
 id_contratante BIGINT,
 duracao VARCHAR(20),
 valor smallmoney,
 localizacao VARCHAR(100),
 pessoa_cuidada VARCHAR(20),
 idade_pessoa_cuidada INT,
 nome_contratante VARCHAR(20),
 tipo_servico VARCHAR(20),
 FOREIGN KEY(id_contratante) REFERENCES contratante (id_contratante)

); /*OK*/ 

CREATE TABLE beneficios
( 
  id_beneficios BIGINT PRIMARY KEY IDENTITY,
  prioridade_feed BIT,
  zero_propaganda BIT
 
); /*OK*/ 

CREATE TABLE premium 
( 
  id_premium BIGINT PRIMARY KEY IDENTITY,
  id_usuario BIGINT,
  id_beneficios BIGINT,
  tipo_premium BIT,
  status_premium VARCHAR (15),
  renovacao_automatica BIT,
  metodo_pagamento VARCHAR(20) NOT NULL,
     CHECK (metodo_pagamento IN ('CREDITO','DEBITO', 'PIX', 'BOLETO')),
     FOREIGN KEY(id_usuario) REFERENCES usuario (id_usuario),
     FOREIGN KEY(id_beneficios) REFERENCES beneficios (id_beneficios)

); /*OK*/ 

CREATE TABLE avaliacao 
( 
 id_avaliacao BIGINT PRIMARY KEY IDENTITY,
 tipo_avaliacao BIT,
 status_avaliacao BIT,
 data_avaliacao DATE,
 comentario VARCHAR (1000),
 id_contratante BIGINT,
 id_prestador BIGINT,

  FOREIGN KEY(id_contratante) REFERENCES contratante (id_contratante),
  FOREIGN KEY(id_prestador) REFERENCES prestador_de_servico (id_prestador)
); /*OK*/

CREATE TABLE chat 
(
 id_chat BIGINT PRIMARY KEY IDENTITY,
 status_chat BIT,
 id_prestador BIGINT,  
 id_contratante BIGINT ,  
 
  FOREIGN KEY(id_contratante) REFERENCES contratante (id_contratante),
  FOREIGN KEY(id_prestador) REFERENCES prestador_de_servico (id_prestador)
);  /*OK*/

CREATE TABLE mensagem 
( 
 id_mensagem BIGINT PRIMARY KEY IDENTITY,
 data_hora DATETIME,
 conteudo VARCHAR (100),
 id_usuario BIGINT,
 id_chat BIGINT,
 FOREIGN KEY(id_chat) REFERENCES chat (id_chat),
 FOREIGN KEY(id_usuario) REFERENCES usuario (id_usuario)


); /*OK*/


CREATE TABLE realiza 
( 
 id_realiza BIGINT PRIMARY KEY IDENTITY,
 id_solicitacao BIGINT ,  
 id_contratante BIGINT , 
 FOREIGN KEY(id_contratante) REFERENCES contratante (id_contratante),
  FOREIGN KEY(id_solicitacao) REFERENCES solicitacao (id_solicitacao)
); /*OK*/

CREATE TABLE aceita  
( 
 id_aceita BIGINT PRIMARY KEY IDENTITY,
 id_solicitacao BIGINT,  
 id_prestador BIGINT, 
 FOREIGN KEY(id_prestador) REFERENCES prestador_de_servico (id_prestador),
 FOREIGN KEY(id_solicitacao) REFERENCES solicitacao (id_solicitacao)
); /*OK*/

CREATE TABLE requer  
( 
 id_requer BIGINT PRIMARY KEY IDENTITY,
 id_agendamento BIGINT,  
 id_prestador BIGINT, 
 FOREIGN KEY(id_prestador) REFERENCES prestador_de_servico (id_prestador),
 FOREIGN KEY(id_agendamento) REFERENCES agendamento (id_agendamento)
); /*OK*/

-- 1. Aumentar o tamanho do conteúdo das mensagens
ALTER TABLE mensagem
ALTER COLUMN conteudo NVARCHAR(1000) NULL;
GO

-- 2. Garantir data e hora nas novas mensagens
UPDATE mensagem
SET data_hora = GETDATE()
WHERE data_hora IS NULL;
GO

ALTER TABLE mensagem
    ADD CONSTRAINT DF_mensagem_data_hora
        DEFAULT GETDATE() FOR data_hora;
GO

-- 3. Adicionar indicador de mensagem lida
ALTER TABLE mensagem
    ADD lida BIT NOT NULL
    CONSTRAINT DF_mensagem_lida DEFAULT 0 WITH VALUES;
GO

-- 4. Garantir status padrão para novos chats
ALTER TABLE chat
    ADD CONSTRAINT DF_chat_status
        DEFAULT 1 FOR status_chat;
GO

-- 5. Facilitar a busca das mensagens de cada conversa
CREATE INDEX IX_mensagem_chat_data
    ON mensagem (id_chat, data_hora, id_mensagem);
GO

-- 6. Verificar se existem conversas duplicadas
SELECT
    id_contratante,
    id_prestador,
    COUNT(*) AS quantidade
FROM chat
GROUP BY id_contratante, id_prestador
HAVING COUNT(*) > 1;
GO

-- Execute somente se a consulta anterior não retornar duplicatas:
CREATE UNIQUE INDEX UX_chat_participantes
    ON chat (id_contratante, id_prestador);
GO