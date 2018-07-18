package com.texoit.xpto.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.query.internal.NativeQueryImpl;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementação customizada do repositório de comunicação com o banco de dados
 * @author Cleiton
 */
@Repository
@Transactional(readOnly = true)
public class CityRepositoryImpl implements CityRepositoryCustom {
	
	@PersistenceContext
    EntityManager entityManager;
	
	/**
	 * Retorna registros diferentes de uma coluna conforme o parametro 'column'
	 */
    @Override
    public List<?> getDistinctColumnsLike(String column) {
        NativeQueryImpl<?> query = (NativeQueryImpl<?>) entityManager.createNativeQuery("SELECT DISTINCT c.".concat(column).concat(" FROM City as c"));
        return query.getResultList();
    }

}
