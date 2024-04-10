CREATE DATABASE ControlProyectosBD;

USE ControlProyectosDB;

CREATE TABLE SNIP
(
    id_snip INT PRIMARY KEY IDENTITY(1,1),
    numero_snip INT NOT NULL,
    nombre_proyecto varchar(300)
);

CREATE TABLE SMIP
(
    id_smip INT PRIMARY KEY IDENTITY(1,1),
    numero_smip INT NOT NULL,
    id_snip INT,
    monto DECIMAL NOT NULL,
    FOREIGN KEY (id_snip) REFERENCES SNIP(id_snip)
);

CREATE TABLE RENGLON_INICIAL
(
	id_renglon_inicial INT PRIMARY KEY IDENTITY(1,1),
    id_smip INT,
    num_renglon_trabajo INT,
    renglon_trabajo VARCHAR(600) NOT NULL,
    unidad_medida VARCHAR(20) NOT NULL,
    cantidad DECIMAL NOT NULL,
    costo_unitario DECIMAL NOT NULL,
    costo_total DECIMAL NOT NULL,
    FOREIGN KEY (id_smip) REFERENCES SMIP(id_smip)
);

CREATE TABLE RENGLON_ACTUAL
(
	id_renglon_actual INT PRIMARY KEY IDENTITY(1,1),
    id_smip INT,
    num_renglon_trabajo INT,
    renglon_trabajo VARCHAR(600) NOT NULL,
    unidad_medida VARCHAR(20) NOT NULL,
    cantidad DECIMAL NOT NULL,
    costo_unitario DECIMAL NOT NULL,
    costo_total DECIMAL NOT NULL,
    FOREIGN KEY (id_smip) REFERENCES SMIP(id_smip)
);

CREATE TABLE RENGLON_EJECUTADO
(
    id_renglon_ejecutado INT PRIMARY KEY IDENTITY(1,1),
    id_smip INT,
    num_renglon_trabajo INT,
    renglon_trabajo VARCHAR(600) NOT NULL,
    unidad_medida VARCHAR(20) NOT NULL,
    cantidad DECIMAL NOT NULL,
    costo_unitario DECIMAL NOT NULL,
    costo_total DECIMAL NOT NULL,
    FOREIGN KEY (id_smip) REFERENCES SMIP(id_smip)
);

CREATE TABLE RENGLON_PENDIENTE
(
    id_renglon_pendiente INT PRIMARY KEY IDENTITY(1,1),
    id_smip INT,
    num_renglon_trabajo INT,
    renglon_trabajo VARCHAR(600) NOT NULL,
    unidad_medida VARCHAR(20) NOT NULL,
    cantidad DECIMAL NOT NULL,
    costo_unitario DECIMAL NOT NULL,
    costo_total DECIMAL NOT NULL,
    FOREIGN KEY (id_smip) REFERENCES SMIP(id_smip)
);

CREATE TABLE TIPO_MODIFICACION
(
    id_tipo_modificacion INT PRIMARY KEY IDENTITY(1,1),
    modificacion VARCHAR (50)
);

CREATE TABLE MODIFICACION
(
    id_modificacion INT PRIMARY KEY IDENTITY(1,1),
    id_smip INT,
    id_tipo_modificacion INT,
    FOREIGN KEY (id_smip) REFERENCES SMIP(id_smip),
    FOREIGN KEY (id_tipo_modificacion) REFERENCES TIPO_MODIFICACION(id_tipo_modificacion)
);

CREATE TABLE PRORROGA
(
    id_prorroga INT PRIMARY KEY IDENTITY(1,1),
    id_modificacion INT,
    justificacion VARCHAR(400) NOT NULL,
    fecha_final_modificada DATE NOT NULL,
    numero_acta VARCHAR (10) NOT NULL,
    fecha_acta DATE NOT NULL,
    url_acta VARCHAR(500) NOT NULL
        FOREIGN KEY (id_modificacion) REFERENCES MODIFICACION(id_modificacion)
);

CREATE TABLE DOC_CAMBIO
(
    id_doc_cambio INT PRIMARY KEY IDENTITY(1,1),
    tipo_doc_cambio VARCHAR(150)
);

CREATE TABLE MOD_MONTO
(
    id_mod_monto INT PRIMARY KEY IDENTITY(1,1),
    id_modificacion INT,
    id_doc_cambio INT,
    justificacion VARCHAR(400) NOT NULL,
    monto_modificado DECIMAL NOT NULL,
    numero_acta INT NOT NULL,
    fecha_acta DATE NOT NULL,
    url_acta VARCHAR(500)
        FOREIGN KEY (id_modificacion) REFERENCES MODIFICACION(id_modificacion),
    FOREIGN KEY (id_doc_cambio) REFERENCES DOC_CAMBIO(id_doc_cambio)
);

CREATE TABLE ODC
(
    id_mod_monto INT,
    num_renglon_trabajo INT,
    renglon_trabajo VARCHAR(600) NOT NULL,
    unidad_medida VARCHAR(20) NOT NULL,
    cantidad DECIMAL NOT NULL,
    costo_unitario DECIMAL NOT NULL,
    costo_total DECIMAL NOT NULL,
    FOREIGN KEY (id_mod_monto) REFERENCES MOD_MONTO(id_mod_monto)
);

CREATE TABLE OTS
(
    id_mod_monto INT,
    num_renglon_trabajo INT,
    renglon_trabajo VARCHAR(600) NOT NULL,
    unidad_medida VARCHAR(20) NOT NULL,
    cantidad DECIMAL NOT NULL,
    costo_unitario DECIMAL NOT NULL,
    costo_total DECIMAL NOT NULL,
    FOREIGN KEY (id_mod_monto) REFERENCES MOD_MONTO(id_mod_monto)
);

CREATE TABLE ATE
(
    id_mod_monto INT,
    num_renglon_trabajo INT,
    renglon_trabajo VARCHAR(600) NOT NULL,
    unidad_medida VARCHAR(20) NOT NULL,
    cantidad DECIMAL NOT NULL,
    costo_unitario DECIMAL NOT NULL,
    costo_total DECIMAL NOT NULL,
    FOREIGN KEY (id_mod_monto) REFERENCES MOD_MONTO(id_mod_monto)
);

CREATE TABLE EXPEDIENTE
(
    id_expediente INT PRIMARY KEY IDENTITY(1,1),
    numero_expediente INT NOT NULL
);

CREATE TABLE CONTRATISTA
(
    nit VARCHAR(50) PRIMARY KEY NOT NULL,
    nombre VARCHAR(400) NOT NULL,
    apellido VARCHAR(400) NOT NULL,
    cargo VARCHAR(400) NOT NULL,
    empresa VARCHAR(400) NOT NULL
);

CREATE TABLE ESTADO
(
    id_estado INT PRIMARY KEY IDENTITY(1,1),
    estado VARCHAR(100)
);

CREATE TABLE CONVENIO
(
    id_convenio INT PRIMARY KEY IDENTITY(1,1),
    numero_convenio VARCHAR(15) NOT NULL,
    fecha_convenio DATE NOT NULL,
    monto_convenio DECIMAL,
    url VARCHAR(500) NOT NULL
);

CREATE TABLE NOG
(
    id_nog INT PRIMARY KEY IDENTITY(1,1),
    numero_nog INT NOT NULL
);

CREATE TABLE CONTRATO
(
    id_contrato INT PRIMARY KEY IDENTITY(1,1),
    numero_contrato VARCHAR(10) NOT NULL,
    id_expediente INT,
    id_smip INT,
    nit VARCHAR(50),
    id_estado INT,
    id_convenio INT,
    id_nog INT,
    fecha_contrato DATE NOT NULL,
    monto_inicial DECIMAL NOT NULL,
    monto_actual DECIMAL NOT NULL,
    plazo_incial VARCHAR(100) NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_final DATE NOT NULL,
    FOREIGN KEY (id_expediente) REFERENCES EXPEDIENTE(id_expediente),
    FOREIGN KEY (id_smip) REFERENCES SMIP(id_smip),
    FOREIGN KEY (nit) REFERENCES CONTRATISTA(nit),
    FOREIGN KEY (id_estado) REFERENCES ESTADO(id_estado),
    FOREIGN KEY (id_convenio) REFERENCES CONVENIO(id_convenio),
    FOREIGN KEY (id_nog) REFERENCES NOG(id_nog)
);

CREATE TABLE RECIBO_7B
(
    id_recibo_7b INT PRIMARY KEY NOT NULL,
    id_convenio INT,
    monto DECIMAL NOT NULL,
    fecha DATE NOT NULL,
    concepto VARCHAR(300) NOT NULL,
    url_recibo VARCHAR(500) NOT NULL,
    FOREIGN KEY (id_convenio) REFERENCES CONVENIO(id_convenio)
);

CREATE TABLE APORTE
(
    id_aporte INT PRIMARY KEY IDENTITY,
    tipo VARCHAR(100)
);

CREATE TABLE FINANCIAMIENTO_INICIAL
(
    id_financiamiento_inicial INT PRIMARY KEY IDENTITY,
    id_contrato INT,
    id_aporte INT,
    monto DECIMAL NOT NULL,
    FOREIGN KEY (id_contrato) REFERENCES CONTRATO(id_contrato),
    FOREIGN KEY (id_aporte) REFERENCES APORTE(id_aporte)
);

CREATE TABLE ANTICIPO
(
    id_anticipo INT PRIMARY KEY IDENTITY,
    id_expediente INT,
    id_financiamiento_inicial INT,
    fecha DATE NOT NULL,
    monto DECIMAL NOT NULL,
    FOREIGN KEY (id_expediente) REFERENCES EXPEDIENTE(id_expediente),
    FOREIGN KEY (id_financiamiento_inicial) REFERENCES FINANCIAMIENTO_INICIAL(id_financiamiento_inicial)
);

CREATE TABLE AMORTIZACION
(
    id_amortizacion INT PRIMARY KEY IDENTITY,
    id_anticipo INT,
    anticipo DECIMAL NOT NULL,
    amortizado DECIMAL NOT NULL,
    por_amortizar DECIMAL NOT NULL
        FOREIGN KEY (id_anticipo) REFERENCES ANTICIPO(id_anticipo)
);

CREATE TABLE FINANCIAMIENTO_ACTUAL
(
    id_financiamiento_actual INT PRIMARY KEY IDENTITY,
    id_expediente INT,
    id_financiamiento_inicial INT,
    monto DECIMAL NOT NULL,
    FOREIGN KEY (id_expediente) REFERENCES EXPEDIENTE(id_expediente),
    FOREIGN KEY (id_financiamiento_inicial) REFERENCES FINANCIAMIENTO_INICIAL(id_financiamiento_inicial)
);

CREATE TABLE PAGADO
(
    id_pagado INT PRIMARY KEY IDENTITY,
    id_expediente INT,
    id_financiamiento_actual INT,
    monto DECIMAL NOT NULL,
    FOREIGN KEY (id_expediente) REFERENCES EXPEDIENTE(id_expediente),
    FOREIGN KEY (id_financiamiento_actual) REFERENCES FINANCIAMIENTO_ACTUAL(id_financiamiento_actual)
);

CREATE TABLE POR_PAGAR
(
    id_por_pagar INT PRIMARY KEY IDENTITY,
    id_expediente INT,
    id_financiamiento_actual INT,
    monto DECIMAL NOT NULL,
    FOREIGN KEY (id_expediente) REFERENCES EXPEDIENTE(id_expediente),
    FOREIGN KEY (id_financiamiento_actual) REFERENCES FINANCIAMIENTO_ACTUAL(id_financiamiento_actual)
);

CREATE TABLE ESTIMACION
(
    id_estimacion INT PRIMARY KEY IDENTITY,
    id_expediente INT,
    id_por_pagar INT,
    fecha DATE NOT NULL,
    liquido DECIMAL NOT NULL,
    amortizacion DECIMAL NOT NULL,
    total DECIMAL NOT NULL,
    concepto VARCHAR(500) NOT NULL,
    FOREIGN KEY (id_expediente) REFERENCES EXPEDIENTE(id_expediente),
    FOREIGN KEY (id_por_pagar) REFERENCES POR_PAGAR(id_por_pagar)
);

CREATE TABLE DEVENGADO
(
    id_devengado INT PRIMARY KEY IDENTITY,
    id_estimacion INT,
    id_expediente INT,
    id_financiamiento_actual INT,
    monto DECIMAL NOT NULL,
    FOREIGN KEY (id_expediente) REFERENCES EXPEDIENTE(id_expediente),
    FOREIGN KEY (id_financiamiento_actual) REFERENCES FINANCIAMIENTO_ACTUAL(id_financiamiento_actual),
    FOREIGN KEY (id_estimacion) REFERENCES ESTIMACION(id_estimacion)
);

CREATE TABLE LIQUIDACION
(
    id_liquidacion INT PRIMARY KEY IDENTITY,
    id_expediente INT,
    id_por_pagar INT,
    liquido DECIMAL NOT NULL,
    amortizacion DECIMAL NOT NULL,
    total DECIMAL NOT NULL,
    concepto VARCHAR(500) NOT NULL,
    FOREIGN KEY (id_expediente) REFERENCES EXPEDIENTE(id_expediente),
    FOREIGN KEY (id_por_pagar) REFERENCES POR_PAGAR(id_por_pagar)
);

--Tablas para los documentos--

CREATE TABLE CHEQUE_LIQUIDACION
(
    id_cheque_liquidacion INT PRIMARY KEY IDENTITY,
    id_liquidacion INT,
    id_nog INT,
    numero_cheque INT NOT NULL,
    fecha_doc DATE NOT NULL,
    fecha_registro DATE NOT NULL,
    monto DECIMAL NOT NULL,
    concepto VARCHAR(500),
    url_doc VARCHAR(500),
    FOREIGN KEY (id_liquidacion) REFERENCES LIQUIDACION(id_liquidacion),
    FOREIGN KEY (id_nog) REFERENCES NOG(id_nog)
);

CREATE TABLE CHEQUE_ESTIMACION
(
    id_cheque_estimacion INT PRIMARY KEY IDENTITY,
    id_estimacion INT,
    id_nog INT,
    numero_cheque INT NOT NULL,
    fecha_doc DATE NOT NULL,
    fecha_registro DATE NOT NULL,
    monto DECIMAL NOT NULL,
    concepto VARCHAR(500),
    url_doc VARCHAR(500),
    FOREIGN KEY (id_estimacion) REFERENCES ESTIMACION(id_estimacion),
    FOREIGN KEY (id_nog) REFERENCES NOG(id_nog)
);

CREATE TABLE CHEQUE_ANTICIPO
(
    id_cheque_anticipo INT PRIMARY KEY IDENTITY,
    id_anticipo INT,
    id_nog INT,
    numero_cheque INT NOT NULL,
    fecha_doc DATE NOT NULL,
    fecha_registro DATE NOT NULL,
    monto DECIMAL NOT NULL,
    concepto VARCHAR(500),
    url_doc VARCHAR(500),
    FOREIGN KEY (id_anticipo) REFERENCES ANTICIPO(id_anticipo),
    FOREIGN KEY (id_nog) REFERENCES NOG(id_nog)
);

CREATE TABLE TIPO_GARANTIA
(
    id_tipo_garantia INT PRIMARY KEY IDENTITY,
    tipo VARCHAR(200)
);

CREATE TABLE GARANTIAS
(
    id_garantia INT PRIMARY KEY IDENTITY,
    id_tipo_garantia INT,
    id_nog INT,
    no_poliza VARCHAR (50) NOT NULL,
    monto DECIMAL NOT NULL,
    fecha_doc DATE NOT NULL,
    fecha_registro DATE NOT NULL,
    url_doc VARCHAR(500),
    FOREIGN KEY (id_tipo_garantia) REFERENCES TIPO_GARANTIA(id_tipo_garantia),
    FOREIGN KEY (id_nog) REFERENCES NOG(id_nog)
);

CREATE TABLE ACTA
(
    id_acta INT PRIMARY KEY IDENTITY,
    id_nog INT,
    numero_acta INT NOT NULL,
    fecha_acta DATE NOT NULL,
    fecha_registro DATE NOT NULL,
    url_doc VARCHAR(500),
    FOREIGN KEY (id_nog) REFERENCES NOG(id_nog)
);

CREATE TABLE DOCUMENTO_CAMBIO
(
    id_doc_cambio INT PRIMARY KEY IDENTITY,
    id_nog INT,
    descripcion VARCHAR(500) NOT NULL,
    fecha_doc DATE NOT NULL,
    fecha_registro DATE NOT NULL,
    url_doc VARCHAR(500),
    FOREIGN KEY (id_nog) REFERENCES NOG(id_nog)
);

CREATE TABLE TIPO_INFORME
(
    id_tipo_informe INT PRIMARY KEY IDENTITY,
    tipo VARCHAR(200)
);

CREATE TABLE INFORME
(
    id_informe INT PRIMARY KEY IDENTITY,
    id_tipo_informe INT,
    id_nog INT,
    descripcion VARCHAR(500) NOT NULL,
    fecha_doc DATE NOT NULL,
    fecha_registro DATE NOT NULL,
    porcetaje DECIMAL NOT NULL,
    url_doc VARCHAR(500),
    FOREIGN KEY (id_tipo_informe) REFERENCES TIPO_INFORME(id_tipo_informe),
    FOREIGN KEY (id_nog) REFERENCES NOG(id_nog)
);

CREATE TABLE DOCUMENTO_COCODE
(
    id_documento_cocode INT PRIMARY KEY IDENTITY,
    id_nog INT,
    descripcion VARCHAR(500) NOT NULL,
    fecha_doc DATE NOT NULL,
    fecha_registro DATE NOT NULL,
    url_doc VARCHAR(500),
    FOREIGN KEY (id_nog) REFERENCES NOG(id_nog)
);

CREATE TABLE TIPO_DOC_TECNICO
(
    id_tipo_doc_tecnico INT PRIMARY KEY IDENTITY,
    tipo VARCHAR(200)
);

CREATE TABLE DOCUMENTO_TECNICO
(
    id_doc_tecnico INT PRIMARY KEY IDENTITY,
    id_tipo_doc_tecnico INT,
    id_nog INT,
    fecha_doc DATE NOT NULL,
    fecha_registro DATE NOT NULL,
    url_doc VARCHAR(500),
    FOREIGN KEY (id_tipo_doc_tecnico) REFERENCES TIPO_DOC_TECNICO(id_tipo_doc_tecnico),
    FOREIGN KEY (id_nog) REFERENCES NOG(id_nog)
);

CREATE TABLE SIN_CATEGORIA
(
    id_sin_categoria INT PRIMARY KEY IDENTITY,
    id_nog INT,
    descripcion VARCHAR(500) NOT NULL,
    fecha_doc DATE NOT NULL,
    fecha_registro DATE NOT NULL,
    url_doc VARCHAR(500),
    FOREIGN KEY (id_nog) REFERENCES NOG(id_nog)
);