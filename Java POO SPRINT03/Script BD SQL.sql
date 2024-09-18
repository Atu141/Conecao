CREATE TABLE consultora (
    Cod_consultora INT PRIMARY KEY,
    Nome_consultora VARCHAR(255),
    Email_consultora VARCHAR(255),
    Telefone_consultora VARCHAR(15),
    Data_inicio DATE,
    Data_fim DATE
);

CREATE TABLE RastreamentoDesempenho (
    Cod_Desempenho INT PRIMARY KEY,
    Metas VARCHAR2(200),
    Pontuação NUMBER(5, 2),
    Comissão NUMBER(5, 2),
    Vendas_Total NUMBER(5, 2),
    Consultora_Cod_Consultora INT,
    Cod_Consultora INT,
    FOREIGN KEY (Consultora_Cod_Consultora) REFERENCES Consultora(Cod_Consultora)
);

CREATE TABLE Mentoria (
    Cod_Mentoria INT PRIMARY KEY,
    Id_Mentora INT,
    Id_Mentorada INT,
    Data_Inicio DATE,
    Data_Fim DATE
);

CREATE TABLE MentoriaConsultora (
    Consultora_Cod_Consultora INT,
    Mentoria_Cod_Mentoria INT,
    PRIMARY KEY (Consultora_Cod_Consultora, Mentoria_Cod_Mentoria),
    FOREIGN KEY (Consultora_Cod_Consultora) REFERENCES Consultora(Cod_Consultora),
    FOREIGN KEY (Mentoria_Cod_Mentoria) REFERENCES Mentoria(Cod_Mentoria)
);

CREATE TABLE ChatBot (
    Cod_Chatbot INT PRIMARY KEY,
    Pergunta VARCHAR2(100),
    Resposta VARCHAR2(600),
    Data_Interação DATE,
    Consultora_Cod_Consultora INTEGER,
    FOREIGN KEY (Consultora_Cod_Consultora) REFERENCES Consultora(Cod_Consultora)
);

CREATE TABLE Cliente (
    Cod_Cliente INT PRIMARY KEY,
    Nome_Cliente VARCHAR2(50),
    Email_Cliente VARCHAR2(100),
    Telefone_Cliente VARCHAR2(10),
    Endereço VARCHAR2(50),
    Consultora_Cod_Consultora INT,
    FOREIGN KEY (Consultora_Cod_Consultora) REFERENCES Consultora(Cod_Consultora)
);

CREATE TABLE Comunidade (
    Cod_Comunidade INT PRIMARY KEY,
    Nome_Comunidade VARCHAR2(50),
    Descrição VARCHAR2(500)
);

CREATE TABLE ComunidadeConsultora (
    Consultora_Cod_Consultora INTEGER,
    Comunidade_Cod_Comunidade INTEGER,
    PRIMARY KEY (Consultora_Cod_Consultora, Comunidade_Cod_Comunidade),
    FOREIGN KEY (Consultora_Cod_Consultora) REFERENCES Consultora(Cod_Consultora),
    FOREIGN KEY (Comunidade_Cod_Comunidade) REFERENCES Comunidade(Cod_Comunidade)
);

CREATE TABLE Produto (
    Cod_Produto INTEGER PRIMARY KEY,
    Nome_Produto VARCHAR2(50),
    Descrição_Produto VARCHAR2(500),
    Preço NUMBER(5, 2)
);

CREATE TABLE Pedido (
    Cod_Pedido INTEGER PRIMARY KEY,
    Data DATE,
    Cliente_Cod_Cliente INTEGER,
    Consultora_Cod_Consultora INTEGER,
    FOREIGN KEY (Cliente_Cod_Cliente) REFERENCES Cliente(Cod_Cliente),
    FOREIGN KEY (Consultora_Cod_Consultora) REFERENCES Consultora(Cod_Consultora)
);

CREATE TABLE PedidoProduto (
    Pedido_Cod_Pedido INTEGER,
    Produto_Cod_Produto INTEGER,
    Quantidade NUMBER(5, 2),
    PRIMARY KEY (Pedido_Cod_Pedido, Produto_Cod_Produto),
    FOREIGN KEY (Pedido_Cod_Pedido) REFERENCES Pedido(Cod_Pedido),
    FOREIGN KEY (Produto_Cod_Produto) REFERENCES Produto(Cod_Produto)
);
