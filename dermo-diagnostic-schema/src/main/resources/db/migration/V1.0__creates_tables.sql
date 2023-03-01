CREATE TABLE IF NOT EXISTS dermoapp_patient.account (

    id UUID NOT NULL,
	correo_electronico varchar NOT NULL,
	created_at timestamp NOT NULL,
	contrasena varchar NOT NULL,

	CONSTRAINT account_pk PRIMARY KEY (correo_electronico)
);

CREATE INDEX IF NOT EXISTS account_created_at_idx ON dermoapp_patient.account (created_at);
CREATE INDEX IF NOT EXISTS account_correo_electronico_idx ON dermoapp_patient.account (correo_electronico);