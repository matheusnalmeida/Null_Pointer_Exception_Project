create database hospital
default character set utf8
default collate utf8_general_ci;

create table medicos(
    nome varchar(50) not null,
    crm varchar(20) not null,
    matricula varchar(12) not null,
    senha varchar(50) not null,
    primary key (crm)
);

create table alunos(
    nome varchar(50) not null,
    matricula varchar(12) not null,
    senha varchar(50) not null,
    anoResidencia int,
    dataNascimento date not null,
    crmProfessor varchar(20),
    foreign key(crmProfessor)
    references professores(crm)
);

create table professores(
    nome varchar(50) not null,
    matricula varchar(12) not null,
    crm varchar(20) not null,
    senha varchar(50) not null,
    titulacao varchar(50) not null,
    primary key (crm)
);

create table pacientes(
    nome varchar(50) not null,
    cor varchar(10) not null,
    dataNascimento date not null,
    cpf varchar(14) not null primary key,
    sexo varchar(10)
);

create table pacientes_alunos_relatorios(
    cpfPaciente varchar(14) not null,
    codigoAluno int not null,
    dataAtendimento datetime not null,
    codigoAtendimento int auto_increment primary key,
    codigoRelatorio int not null,
    foreign key(cpfPaciente)
    references paciente(cpf),
    foreign key(codigoAluno)
    references aluno(codigo),
    foreign key(codigoRelatorio)
    references relatorio(codigo)
);

create table relatorios(
    codigo int auto_increment primary key,
    dataRelatorio datetime default now(),
    descricao text not null,
    crmMedicoAutorizacao varchar(20) not null,
    dataAutorizacao datetime not null,
    arquivo blob,
    foreign key(crmMedicoAutorizacao)
    references medicos(crm)
);

create table pedidosExame(
    codigo int auto_increment primary key,
    recomendacoes text not null,
    dataExame datetime not null,
    hipoteseDiagnostica text,
    tipoExame text not null,
    cpfPaciente varchar(14) not null,
    foreign key(cpfPaciente)
    references pacientes(cpf)
);

create table emissoesPedidosExame(
    dataEmissao datetime default now(),
    crmMedico varchar(20) not null,
    cpfPaciente varchar(14) not null,
    codigoPedidoExame int not null primary key,
    foreign key(crmMedico)
    references medicos(crm),
    foreign key(cpfPaciente)
    references pacientes(cpf),
    foreign key(codigoPedidoExame)
    references pedidosExame(codigo)
);

