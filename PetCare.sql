
CREATE DATABASE IF NOT EXISTS petcare;
USE petcare;

-- Tabela Usuário (generalização)
CREATE TABLE Usuario (
    idUsuario INT AUTO_INCREMENT PRIMARY KEY,
    senha VARCHAR(255) NOT NULL,
    data_cadastro DATETIME DEFAULT CURRENT_TIMESTAMP
);
-- Tabela Tutor (herda de Usuario)
CREATE TABLE Tutor (
    idTutor INT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(20) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    telefone VARCHAR(20),
    idUsuario int,
    FOREIGN KEY (idUsuario) REFERENCES Usuario(idUsuario)
);

-- Tabela Pet
CREATE TABLE Pet (
    idPet INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    especie VARCHAR(100) NOT NULL,
    raca VARCHAR(100),
    dataNascimento DATE,
    sexo VARCHAR(20),
    idTutor INT,
    FOREIGN KEY (idTutor) REFERENCES Tutor(idTutor)
);

-- Tabela Vacina
CREATE TABLE Vacina (
    idVacina INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    fabricante VARCHAR(100),
    validadeMeses INT
);

-- Tabela AplicacaoVacina
CREATE TABLE AplicacaoVacina (
    idAplicacao INT AUTO_INCREMENT PRIMARY KEY,
    dataAplicacao DATE NOT NULL,
    dataProximaDose DATE,
    observacoes TEXT,
    idVacina INT,
    idPet INT,
    FOREIGN KEY (idVacina) REFERENCES Vacina(idVacina),
    FOREIGN KEY (idPet) REFERENCES Pet(idPet)
);

-- Tabela Medicamento
CREATE TABLE Medicamento (
    idMedicamento INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    fabricante VARCHAR(100),
    preco DECIMAL(10,2),
    quantidadeEstoque INT DEFAULT 0
);

-- Tabela Pedido
CREATE TABLE Pedido (
    idPedido INT AUTO_INCREMENT PRIMARY KEY,
    dataPedido DATETIME DEFAULT CURRENT_TIMESTAMP,
    valorTotal DECIMAL(10,2) DEFAULT 0.00,
    status VARCHAR(30) DEFAULT 'Pendente',
    idTutor INT,
    FOREIGN KEY (idTutor) REFERENCES Tutor(idTutor)
);

-- Tabela ItemPedido
CREATE TABLE ItemPedido (
    idItem INT AUTO_INCREMENT PRIMARY KEY,
    quantidade INT NOT NULL,
    precoUnitario DECIMAL(10,2),
    subtotal DECIMAL(10,2),
    idPedido INT,
    idMedicamento INT,
    FOREIGN KEY (idPedido) REFERENCES Pedido(idPedido),
    FOREIGN KEY (idMedicamento) REFERENCES Medicamento(idMedicamento)
);
