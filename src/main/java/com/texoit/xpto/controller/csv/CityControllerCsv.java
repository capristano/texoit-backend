package com.texoit.xpto.controller.csv;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.texoit.xpto.model.City;
import com.texoit.xpto.repository.CityRepository;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe responsável por comunicações com o arquivo CSV 
 * @author Cleiton
 */
@Service
public class CityControllerCsv {
	
	@Autowired
	private CityRepository cityRepository;
	private static final String CSV_FILE_PATH = "./city.csv";
	private static final String[] COLUMNS = {"ibge_id", "uf", "name", "capital", "lon", "lat", "no_accents", "alternative_names", "microregion", "mesoregion"};
	private static List<City> listCity;
	
	/**
	 * Retorna uma lista de cidades do arquivo CSV
	 * @return List<City>
	 */
	public List<City> getListCityFromCsv() {		
		if(listCity == null) {
			listCity = new ArrayList<City>();
			try {
				Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH));                    
				CsvToBean<City> csvToBean = new CsvToBeanBuilder<City>(reader)
						.withType(City.class)
						.withIgnoreLeadingWhiteSpace(true)
						.build();
				listCity = IteratorUtils.toList(csvToBean.iterator());
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}
		return listCity;
    }
	
	/**
	 * Salva os registros resgatados do CSV para a base de dados, caso a tabela city esteja vazia
	 */
    public void csvToDataBase()  {
    	if(cityRepository.count() == 0)
    		try {
    			cityRepository.saveAll(getListCityFromCsv());
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    }
    
    /**
     * Retorna os dados de uma coluna do arquivo CSV
     * @param column
     * @param value
     * @return List<String>
     */
    public List<String> getColumnValue(String column, String value) {
    	List<String> result = new ArrayList<String>();
    	
    	Integer index = null;
    	for(int x = 0; x < COLUMNS.length; x++)
    		if(COLUMNS[x].equals(column))
    			index = x;
    	if(index != null)
    		try {
    			Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH));
    			CSVReader csvReader = new CSVReaderBuilder(reader)
    	                .withSkipLines(1)
    	                .build();
    			String [] nextLine;
    			while ((nextLine = csvReader.readNext()) != null)
    				if(nextLine[index].toLowerCase().contains(value.toLowerCase()))
    					result.add(nextLine[index]);
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	return result;
    }
    
}
