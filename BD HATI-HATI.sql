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
 especificação VARCHAR (1000),  
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
 descrição_serviço_vaga VARCHAR(1000),  
 data_hora_vaga DATETIME,  
 prestador_requerido VARCHAR (50),
 id_contratante BIGINT,
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
