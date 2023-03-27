package com.company.apirest.backend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.company.apirest.backend.model.Suma;
import com.company.apirest.backend.model.dao.ISumaDao;
import com.company.apirest.backend.response.SumaResponseRest;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
public class SumaServiceImpl implements ISumaService{
	
	private static final Logger log = LoggerFactory.getLogger(SumaServiceImpl.class);

	@Autowired
	private ISumaDao sumaDao;
	
	@Autowired
	private IGuardarService guardarService;
	
	@Override
	public ResponseEntity<SumaResponseRest>  sumaNum(float num1, float num2) {
		
		log.info("inicio metodo suma()");
		
		SumaResponseRest response = new SumaResponseRest();
		List<Suma> list = new ArrayList<>();
		
		try {
			
			float porcentaje = this.obtenerPorcentaje();
			
			// Si porcentaje es -1, es invalido, por lo cual debo buscar en la base el ultimo registro
			if (porcentaje == -1) {
				log.info("inicio metodo buscar el ultimo registro");
				
				try {
					Optional<Suma> registro = Optional.of(sumaDao.findFirstByOrderByIdDesc());
					
					if (registro.isPresent()){
						log.info("registro presente");
						porcentaje = registro.get().getPorcentaje();
						response.setMetada("Respuesta ok", "01", "Respuesta exitosa, con ultimo porcentaje obtenido.");
					} else {
						log.info("no hay anteriores");
						response.setMetada("Respuesta nok", "-2", "No hay valores de porcentaje, ni nuevos ni anteriores.");
						return new ResponseEntity<SumaResponseRest>(response, HttpStatus.NOT_FOUND); 
					}
				} catch (Exception e) {
					log.info("error en consulta");
					response.setMetada("Respuesta nok", "-2", "Fallo el pedido de porcentaje, tanto al servicio como a la base de datos.");
					return new ResponseEntity<SumaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
				}
				
			} else {
				response.setMetada("Respuesta ok", "00", "Respuesta exitosa");
			}
			
			float resultado = (num1 + num2) * (porcentaje/100 + 1);
		
			Suma registro = new Suma();

			LocalDateTime timestamp = LocalDateTime.now();
			long timestampmillis = timestamp.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
			
			registro.setNum1(num1);
			registro.setNum2(num2);
			registro.setPorcentaje(porcentaje);
			registro.setResultado(resultado);
			registro.setTimestamp(timestampmillis);
			
			list.add(registro);
			response.getSumaResponseRest().setSuma(list);
			
			// async guardar registro en base de datos
			//sumaDao.save(registro);
			guardarService.guardarRegistro(registro);
			log.info("siguiendo");
			
			
		} catch (Exception e) {
			response.setMetada("Respuesta nok", "-1", "Error al sumar");
			log.error("error al realizar la suma: {}", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<SumaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<SumaResponseRest>(response, HttpStatus.OK);
	}
	
	

	@Override
	public float obtenerPorcentaje() {
		
		log.info("inicio metodo obtener porcentaje");
		
		// Intento 3 veces, si fallas las 3, devuelve el codigo de error.
		for (int i = 0; i < 3; i++) {
			try {
				RestTemplate restTemplate = new RestTemplate();
				float porcentaje = restTemplate.getForObject("https://external-service.com/percentage", float.class);
				return porcentaje;
			} catch (Exception e) {
				i++;
				log.error("Falla al obtener el porcentaje.",e.getMessage());
			}	
		}
		
		return 5;
	}

	public ISumaDao getSumaDao() {
		return sumaDao;
	}

	public void setSumaDao(ISumaDao sumaDao) {
		this.sumaDao = sumaDao;
	}
	

}
