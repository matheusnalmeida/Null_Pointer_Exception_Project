
#create database hospital
#default character set utf8
#default collate utf8_general_ci;

create table medico(
    nome varchar(50) not null,
    crm varchar(20) not null primary key,
    senha varchar(50) not null
);

create table professor(
    nome varchar(50) not null,
    crm varchar(20) not null primary key,
    titulacao varchar(20) not null,
    senha varchar(50) not null
);

create table aluno(
    nome varchar(50) not null,
    codigo int not null primary key,
    senha varchar(50) not null,
    anoResidencia date not null,
    dataNascimento date not null,
    crm_professor varchar(20),
    foreign key(crm_professor)
    references professor(crm)
);

create table paciente(
    nome varchar(50) not null,
    cor varchar(10) not null,
    dataNascimento date not null,
    cpf varchar(14) not null primary key,
    sexo varchar(10)
);

create table paciente_aluno(
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

create table relatorio(
    codigo int auto_increment primary key,
    dataRelatorio datetime default now(),
    descricao text not null,
    crm_medico_autoriza varchar(20) not null,
    dataAutorizacao datetime not null,
    arquivo blob,
    foreign key(crm_medico_autoriza)
    references medico(crm)
);

create table pedidoExame(
    codigo int auto_increment primary key,
    recomendacoes text not null,
    dataExame datetime not null,
    hipoteseDiagnostica text,
    tipoExame text not null,
    cpfPaciente varchar(14) not null,
    foreign key(cpfPaciente)
    references paciente(cpf)
);

create table pedidoExame_medico(
    codigoPedidoExame int not null,
    crm_medico varchar(20) not null,
    dataPedidoExame datetime not null default now(),
    codigo int auto_increment primary key,
    foreign key(codigoPedidoExame)
    references pedidoExame(codigo),
    foreign key(crm_medico)
    references medico(crm)
);