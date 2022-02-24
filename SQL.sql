CREATE DATABASE estoque
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Portuguese_Brazil.1252'
    LC_CTYPE = 'Portuguese_Brazil.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;




CREATE TABLE IF NOT EXISTS public.produto
(
    id integer NOT NULL DEFAULT nextval('sequencia'::regclass),
    nome character varying(90) COLLATE pg_catalog."default" NOT NULL,
    preco numeric NOT NULL,
    quantidade integer,
    total numeric,
    CONSTRAINT id_pk PRIMARY KEY (id)
)

TABLESPACE pg_default;
