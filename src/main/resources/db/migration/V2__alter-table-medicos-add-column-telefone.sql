alter table medicos add column telefone varchar(20);

update medicos set telefone = replace(telefone, null, '0');