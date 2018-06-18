create database adventuresLife;
use adventuresLife;

	create table tbDescricaoMata(
	idMata smallint not null unique,
    descricao varchar(140) not null,
	primary key (idMata))engine= innodb;
    
    create table tbTipodelocomocao(
	idLocomocao smallint not null unique,
    descricao varchar(140) not null,
    primary key (idLocomocao))engine= innodb;
    
    create table tbUsuario(
	nicknameUsuario varchar(16) not null unique,
    senha varchar(32) not null,
    nome varchar(140) not null,
    foto varchar(30),
    email varchar(140) not null,
    primary key (nicknameUsuario)) engine= innodb;
    
create table tbTrilha(
	idTrilha int not null unique auto_increment,
    apelido varchar(40) not null,
    obstaculos varchar(140) null,
    distancia double not null,
    idmata smallint null,
    dataGravacao date not null,
    nicknameUsuario varchar(16) not null,
    dificuldade smallint check(dificuldade >= 0 and dificuldade <=5), 
    primary key (idTrilha),
    foreign key (idMata) references tbDescricaomata(idmata),
    FOREIGN KEY (nicknameUsuario) REFERENCES tbUsuario(nicknameUsuario)
    ) engine= innodb;
        
create table tbRota(
	idTrilha int not null,
    caminho linestring,
    FOREIGN KEY (idTrilha) REFERENCES tbTrilha(idTrilha),
    primary key (idTrilha)) engine= innodb;


create table tbCriterioDeAvaliacao(
	idCriterio smallint auto_increment not null,
    descricao varchar(140) not null,
    primary key (idCriterio)) engine= innodb;
    
create table tbTrilhaLocomocao(
	idTrilha int not null,
    idLocomocao smallint not null,
    primary key(idTrilha),
    FOREIGN KEY (idTrilha) REFERENCES tbTrilha(idTrilha),
    FOREIGN KEY (idLocomocao) REFERENCES tbTipoDeLocomocao(idLocomocao)
    ) engine= innodb;

create table tbUsuarioRealizaTrilha(
	nicknameUsuario varchar(16) not null,
    idTrilha int auto_increment not null,
	dataRealizacao date not null,
    primary key (idTrilha, dataRealizacao), 
    FOREIGN KEY(nicknameUsuario) REFERENCES tbUsuario(nicknameUsuario),
    FOREIGN KEY(idTrilha) REFERENCES tbTrilha(idTrilha)
    ) engine= innodb;

create table tbAvaliacoesRealizadas(
	idTrilha int not null,
    nicknameUsuario varchar(16) not null,
    dataRealizacao date not null,
    idAvaliacao int auto_increment,
    FOREIGN KEY(idTrilha) REFERENCES tbTrilha(idTrilha),
    FOREIGN KEY(nicknameUsuario) REFERENCES tbUsuario(nicknameUsuario),
	primary key (idAvaliacao)
)engine = innodb;

create table tbAvaliacaoValores(
	idAvaliacao int,
	idCriterio smallint not null,
    nota smallint not null,
    comentario varchar(140),
    primary key(idAvaliacao,idCriterio),
    FOREIGN KEY (idCriterio) REFERENCES tbCriterioDeAvaliacao(idCriterio),
    foreign key (idAvaliacao) references tbavaliacoesRealizadas(idAvaliacao)
	) engine= innodb;
    
    
    insert into tbDescricaoMata(idMata, descricao)
    values(1, "Amazonia"), (2,"Caatinga"), (3,"Cerrado"), (4,"Mata Atlantica"), (5,"Pampa"), (6,"Pantanal");
    
    insert into tbTipoDeLocomocao(idLocomocao, descricao)
    values (1,"A pé"),(2,"Bicicleta"),(3,"Carro especial"),(4,"Moto de Trilha"),(5,"Jipe"),(6,"Carro de passeio"),(7,"Moto");
    
    insert into tbCriterioDeAvaliacao (descricao)
    values("Dificuldade da trilha"), ("estado de preservacao da trilha"), ("sinalizacao da trilha"), ("seguranca da trilha");
    
    show tables;