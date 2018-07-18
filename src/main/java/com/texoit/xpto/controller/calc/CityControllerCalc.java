package com.texoit.xpto.controller.calc;

import org.springframework.stereotype.Service;
import com.texoit.xpto.model.City;

/**
 * Classe responsável por cálculos gerais relacionados com a entidade City
 * @author Cleiton
 */
@Service
public class CityControllerCalc {
	
	/**
	 * Retorna a distância em KM entre duas cidades
	 * @param cityMin
	 * @param cityMax
	 * @return double
	 */
	public double getDistanceBetweenCities(City cityMin, City cityMax) {
	    final int R = 6371;//radius of the earth

	    double latDistance = Math.toRadians(cityMax.getLat().doubleValue() - cityMin.getLat().doubleValue());
	    double lonDistance = Math.toRadians(cityMax.getLon().doubleValue() - cityMin.getLon().doubleValue());
	    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
	            + Math.cos(Math.toRadians(cityMin.getLat().doubleValue())) * Math.cos(Math.toRadians(cityMax.getLat().doubleValue()))
	            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    double distance = R * c;
	    distance = Math.pow(distance, 2);

	    return Math.sqrt(distance);
	}

}
