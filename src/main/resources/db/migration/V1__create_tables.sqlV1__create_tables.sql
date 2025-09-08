-- src/main/resources/db/migration/V1__create_tables.sql

-- Tabela de habilidades
CREATE TABLE habilidades (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    etapa_educacional VARCHAR(255) NOT NULL,
    codigo_habilidade VARCHAR(255) NOT NULL,
    descricao TEXT NOT NULL
);

-- Tabela de atividades
CREATE TABLE atividades (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255),
    descricao VARCHAR(2000),
    categoria VARCHAR(255),
    etapa_educacional VARCHAR(255),
    link VARCHAR(255),
    url_imagem VARCHAR(255)
);

-- Tabela de junção
CREATE TABLE atividade_habilidades (
    atividade_id BIGINT,
    habilidade_id BIGINT,
    PRIMARY KEY (atividade_id, habilidade_id),
    FOREIGN KEY (atividade_id) REFERENCES atividades(id),
    FOREIGN KEY (habilidade_id) REFERENCES habilidades(id)
);