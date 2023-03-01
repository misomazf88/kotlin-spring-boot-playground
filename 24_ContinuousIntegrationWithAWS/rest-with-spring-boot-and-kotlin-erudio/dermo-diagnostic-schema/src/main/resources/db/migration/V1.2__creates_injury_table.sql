CREATE TABLE IF NOT EXISTS dermoapp_patient.injury (

    id UUID NOT NULL,
    correo_electronico varchar NOT NULL,
    tipo_de_lesion varchar NOT NULL,
    forma_de_lesion varchar NOT NULL,
    numero_de_lesiones varchar NOT NULL,
    distribucion varchar NOT NULL,
    foto_de_lesion varchar NOT NULL,
    created_at timestamp NOT NULL,
    updated_at timestamp NOT NULL,

	CONSTRAINT injury_pk PRIMARY KEY (id)
);

CREATE INDEX IF NOT EXISTS account_correo_electronico_idx ON dermoapp_patient.injury (correo_electronico);