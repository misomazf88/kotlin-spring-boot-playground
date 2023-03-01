CREATE TABLE IF NOT EXISTS dermoapp_patient.user_profile (

    id UUID NOT NULL,
    correo_electronico varchar NOT NULL,
	nombre varchar NOT NULL,
	edad varchar NOT NULL,
	ciudad varchar NOT NULL,
	tipo_de_piel varchar NOT NULL,
	foto_de_piel varchar NOT NULL,
    created_at timestamp NOT NULL,
    updated_at timestamp NOT NULL,

	CONSTRAINT user_pk PRIMARY KEY (id)
);

CREATE INDEX IF NOT EXISTS user_id_idx ON dermoapp_patient.user_profile (id);
CREATE INDEX IF NOT EXISTS account_correo_electronico_idx ON dermoapp_patient.user_profile (correo_electronico);