create table produtos(id serial Primary key, nome varchar(90), quantidade int, valor float, valorTotal float);

insert into produtos(nome, quantidade, valor) values ('feijao', 40, 6.99);
insert into produtos(nome, quantidade, valor) values ('arroz', 57, 3.78);
insert into produtos(nome, quantidade, valor) values ('farinha', 36, 3.29);
insert into produtos(nome, quantidade, valor) values ('oleo', 80, 7.56);
insert into produtos(nome, quantidade, valor) values ('cafe', 48, 5.30);
insert into produtos(nome, quantidade, valor) values ('cha', 32, 2.78);
insert into produtos(nome, quantidade, valor) values ('ervilha', 28, 2.50);
insert into produtos(nome, quantidade, valor) values ('milho', 28, 2.79);

update produtos set valorTotal = valor*quantidade;