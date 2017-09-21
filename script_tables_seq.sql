create table APP_USER (
   id_usuario NUMBER(19) NOT NULL,
   sso_id VARCHAR(30) NOT NULL,
   nombre VARCHAR(30) NOT NULL,
   apellido  VARCHAR(30) NOT NULL,
   email VARCHAR(50) NOT NULL,
   PRIMARY KEY (id_usuario),
   UNIQUE (sso_id)
);
 
 
create table USER_DOCUMENT(
   id_documento NUMBER(19) NOT NULL,
   usuario_id NUMBER(19) NOT NULL,
   nombre  VARCHAR(100) NOT NULL,
   descripcion VARCHAR(255) ,
   tipo VARCHAR(100) NOT NULL,
   contenido blob NOT NULL,
   PRIMARY KEY (id_documento),
   CONSTRAINT documento_usuario FOREIGN KEY (usuario_id) REFERENCES APP_USER (id_usuario));
   
   
  create sequence seq_id_usuario
  start with 1
  increment by 1
  maxvalue 9999999999999999999999999999
  minvalue 1;
  
  
   create sequence seq_id_usuario_doc
  start with 1
  increment by 1
  maxvalue 9999999999999999999999999999
  minvalue 1;
   
