package com.texoit.xpto.controller.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.texoit.xpto.model.City;
import com.texoit.xpto.controller.calc.CityControllerCalc;
import com.texoit.xpto.controller.csv.CityControllerCsv;
import com.texoit.xpto.model.AmountUfDetail;
import com.texoit.xpto.repository.CityRepository;

/**
 * Classe de controle de solicitações REST
 * @author Cleiton
 */
@Controller
@RequestMapping(path="/city")
public class CityControllerRest {
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	CityControllerCsv cityControllerCsv;
	
	@Autowired
	CityControllerCalc cityControllerCalc;
	
	/**
	 * 2 - Retorna cidades que são capitais ordenadas por nome
	 * @return List<City>
	 */
	@GetMapping(path="/findAllCapital")
	public @ResponseBody List<City> findAllCapital() {
		return cityRepository.findAllCapital();
	}
	
	/**
	 * 3 - Retorna o nome do estado com a maior e menor quantidade de cidades e a quantidade de cidades
	 * @return List<AmountUfDetail>
	 */
	@GetMapping(path="/findSmallerAndBiggest")
	public @ResponseBody List<AmountUfDetail> findSmallerAndBiggest() {
		List<AmountUfDetail> listSmallerAndBiggestDetail = new ArrayList<AmountUfDetail>();

		listSmallerAndBiggestDetail.add(new AmountUfDetail(cityRepository.findSmaller()));
		listSmallerAndBiggestDetail.add(new AmountUfDetail(cityRepository.findBiggest()));
		
		return listSmallerAndBiggestDetail;
	}
	
	/**
	 * Caso seja necessário se obter somente as cidades do menor estado
	 * @return AmountUfDetail
	 */
	@GetMapping(path="/findSmaller")
	public @ResponseBody AmountUfDetail findSmaller() {	
		return new AmountUfDetail(cityRepository.findSmaller());
	}
	
	/**
	 * Caso seja necessário se obter somente as cidades do maior estado
	 * @return AmountUfDetail
	 */
	@GetMapping(path="/findBiggest")
	public @ResponseBody AmountUfDetail findBiggest() {		
		return new AmountUfDetail(cityRepository.findBiggest());
	}
	
	/**
	 * 4 - Retorna a quantidade de cidades por estado
	 * @return List<AmountUfDetail>
	 */
	@GetMapping(path="/countCityUf")
	public @ResponseBody List<AmountUfDetail> countCityUf() {
		List<AmountUfDetail> listAmountUfDetail = new ArrayList<AmountUfDetail>();
		for(String value : cityRepository.countCityUf()) {
			listAmountUfDetail.add(new AmountUfDetail(value));
		}
		return listAmountUfDetail;
	}
	
	/**
	 * 5 - Retorna a cidade pelo código do IBGE
	 * @param ibgeId
	 * @return City
	 */
	@GetMapping(path="/findByIbgeId")
	public @ResponseBody City findByIbgeId(long ibgeId) {
		return cityRepository.findByIbgeId(ibgeId);
	}
	
	/**
	 * 6 - Retorna o nome das cidades conforme o estado
	 * @param uf
	 * @return List<String>
	 */
	@GetMapping(path="/findByUf")
	public @ResponseBody List<String> findByUf(String uf) {
		return cityRepository.findByUf(uf);
	}
	
	/**
	 * 7 - Adiciona uma nova cidade e retorna a entidade com o status da operação
	 * @param city
	 * @return ResponseEntity<Object>
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Object> add(@RequestBody City city) {
		return new ResponseEntity<Object>(cityRepository.save(city), HttpStatus.OK);
	}
	
	/**
	 * 8 - Exclui uma cidade com base no código do IBGE e retorna o status da operação
	 * @param ibgeId
	 * @return
	 */
	@GetMapping(path="/delete")
	public @ResponseBody ResponseEntity<Object> delete(long ibgeId) {
		cityRepository.deleteById(ibgeId);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	/**
	 * 9 - Seleciona uma coluna do CSV com base no parâmetro 'column'<br>
	 * e retorna os valores da coluna com base no parâmetro 'value'
	 * @param column
	 * @param value
	 * @return List<String>
	 */
	@GetMapping(path="/getColumnValue")
	public @ResponseBody List<String> getColumnValue(String column, String value) {
		return cityControllerCsv.getColumnValue(column, value);
	}
	
	/**
	 * 10 - Retorna a quantidade de registros baseado no parâmetro 'column', sem contar itens iguais
	 * @param column
	 * @return int
	 */
	@GetMapping(path="/getDistinctColumnsLike")
	public @ResponseBody int getDistinctColumnsLike(String column) {		
		return cityRepository.getDistinctColumnsLike(column).size();
	}
	
	/**
	 * 11 - Retorna a quantidade total de registros
	 * @return long
	 */
	@GetMapping(path="/count")
	public @ResponseBody long count() {		
		return cityRepository.count();
	}
	
	/**
	 * 12 - Obtem as duas cidades mais distantes uma da outra
	 * @return List<City>
	 */
	@GetMapping(path="/findCitiesFaraway")
	public @ResponseBody List<City> findCitiesFaraway() {
		List<City> cityFaraway = new ArrayList<City>();
		
		City cityMinLat = cityRepository.findMinLat();
		City cityMaxLat = cityRepository.findMaxLat();
		City cityMinLon = cityRepository.findMinLon();
		City cityMaxLon = cityRepository.findMaxLon();

		double maxLatitude = cityControllerCalc.getDistanceBetweenCities(cityMinLat, cityMaxLat);
		double maxLongitude = cityControllerCalc.getDistanceBetweenCities(cityMinLon, cityMaxLon);

		if(maxLatitude > maxLongitude) {
			cityFaraway.add(cityMinLat);
			cityFaraway.add(cityMaxLat);
		}else {
			cityFaraway.add(cityMinLon);
			cityFaraway.add(cityMaxLon);
		}

		return cityFaraway;
	}

}
