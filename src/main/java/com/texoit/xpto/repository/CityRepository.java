package com.texoit.xpto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.texoit.xpto.model.City;

/**
 * Repositório de comunicação com o banco de dados
 * @author Cleiton
 */
public interface CityRepository extends CrudRepository<City, Long>, CityRepositoryCustom  {
	
	@Query("SELECT c FROM City c where c.capital = 1")
	public List<City> findAllCapital();

	@Query(value="SELECT tab.uf, MIN(tab.amount) FROM (SELECT uf, count(uf) AS amount FROM City group by uf) AS tab", nativeQuery=true)
	public String findSmaller();
	
	@Query(value="SELECT tab.uf, MAX(tab.amount) FROM (SELECT uf, count(uf) AS amount FROM City group by uf) AS tab", nativeQuery=true)
	public String findBiggest();
	
	@Query(value="SELECT uf, count(uf) AS amount FROM City GROUP BY uf", nativeQuery=true)
	public String[] countCityUf();
	
	public City findByIbgeId(long ibgeId);
	
	@Query("SELECT c.name FROM City c WHERE UPPER(c.uf) = UPPER(:uf)")
	public List<String> findByUf(@Param("uf") String uf);

	@Query(value="SELECT c.* FROM City c WHERE c.lat IN (SELECT MIN(cml.lat) FROM City cml)", nativeQuery=true)
	public City findMinLat();
	
	@Query(value="SELECT c.* FROM City c WHERE c.lat IN (SELECT MAX(cml.lat) FROM City cml)", nativeQuery=true)
	public City findMaxLat();
	
	@Query(value="SELECT c.* FROM City c WHERE c.lon IN (SELECT MIN(cml.lon) FROM City cml)", nativeQuery=true)
	public City findMinLon();
	
	@Query(value="SELECT c.* FROM City c WHERE c.lon IN (SELECT MAX(cml.lon) FROM City cml)", nativeQuery=true)
	public City findMaxLon();
	
}
