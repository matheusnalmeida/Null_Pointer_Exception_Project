create database hospital
default character set utf8
default collate utf8_general_ci;

create table usuarios(
    nome varchar(50) not null,
    matricula varchar(12) not null,
    senha varchar(70) not null,
    primary key(matricula)
);

create table medicos(
    matricula varchar(12) not null,
    crm varchar(20) not null unique,
    primary key(matricula),
    foreign key(matricula)
    references usuarios(matricula)
);

create table professores(
    matricula varchar(12) not null,
    titulacao varchar(20),
    primary key(matricula),
    foreign key(matricula)
    references medicos(matricula)
);

create table alunos(
    matricula varchar(12) not null,
    anoResidencia integer,
    dataNascimento varchar(10),
    matriculaProfessor varchar(12) not null,
    primary key(matricula),
    foreign key(matriculaProfessor)
    references professores(matricula)
);

create table pacientes(
    nome varchar(50) not null,
    cpf varchar(14) not null,
    cor varchar(10),
    dataNascimento varchar(10),
    sexo char(1)
);

create table pacientes_alunos_relatorios(
    cpfPaciente varchar(14),
    matriculaAluno varchar(12),
    dataAtendimento varchar(20),
    codigoAtendimento int auto_increment primary key,
    codigoRelatorio int not null,
    foreign key(cpfPaciente)
    references pacientes(cpf),
    foreign key(matriculaAluno)
    references alunos(matricula),
    foreign key(codigoRelatorio)
    references relatorios(codigo)
);

create table relatorios(
    codigo int auto_increment primary key,
    dataRelatorio varchar(20),
    descricao text not null,
    matriculaMedicoAutorizacao varchar(12),
    dataAutorizacao varchar(20) not null,
    arquivo blob,
    foreign key(matriculaMedicoAutorizacao)
    references medicos(matricula)
);

create table pedidos_exame(
    codigo int auto_increment primary key,
    recomendacoes text not null,
    dataExame varchar(20) not null,
    hipoteseDiagnostica text,
    tipoExame text not null
);

create table emissoes_pedidos_exame(
    dataEmissao varchar(20),
    matriculaMedico varchar(12) not null,
    cpfPaciente varchar(14) not null,
    codigoPedidoExame int not null primary key,
    foreign key(matriculaMedico)
    references medicos(matricula),
    foreign key(cpfPaciente)
    references pacientes(cpf),
    foreign key(codigoPedidoExame)
    references pedidos_exame(codigo)
);