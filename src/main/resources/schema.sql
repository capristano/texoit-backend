/*
 Este script é executado ao se iniciar a aplicação
 */
CREATE TABLE IF NOT EXISTS city(
	ibge_id INTEGER NOT NULL,
	uf VARCHAR(2) NOT NULL,
	name VARCHAR(512) NOT NULL,
	capital  INTEGER DEFAULT 0,
	lon BIGINT NOT NULL,
	lat BIGINT NOT NULL,
	no_accents VARCHAR(512),
	alternative_names VARCHAR(512),
	microregion VARCHAR(512),
	mesoregion VARCHAR(512),
	PRIMARY KEY(ibge_id)
);