package com.company.apirest.backend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.apirest.backend.model.Suma;
import com.company.apirest.backend.model.dao.ISumaDao;

@Service
public class GuardarServiceImpl implements IGuardarService{

	private static final Logger log = LoggerFactory.getLogger(SumaServiceImpl.class);
	
	@Autowired
	private ISumaDao sumaDao;
	
	@Async
	@Override
	@Transactional
	public void guardarRegistro(Suma registro) {
		log.info("guardando registro");
		sumaDao.save(registro);
		/*try {
		    TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException ie) {
		    Thread.currentThread().interrupt();
		}*/
		log.info("saliendo de guardar registro");
	}
}
