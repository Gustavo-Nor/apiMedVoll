update medicos set telefone = '0';

alter table medicos alter column telefone set not null;