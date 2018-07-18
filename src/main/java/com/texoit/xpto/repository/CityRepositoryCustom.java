package com.texoit.xpto.repository;

import java.util.List;

/**
 * Repoositório customizado de comunicação com o banco de dados
 * @author Cleiton
 */
public interface CityRepositoryCustom {
	
	public List<?> getDistinctColumnsLike(String column);

}
