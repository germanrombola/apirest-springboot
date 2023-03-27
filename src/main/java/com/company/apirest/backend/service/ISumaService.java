package com.company.apirest.backend.service;

import org.springframework.http.ResponseEntity;

import com.company.apirest.backend.response.SumaResponseRest;

public interface ISumaService {

	public ResponseEntity<SumaResponseRest>  sumaNum(float num1, float num2);
	public float obtenerPorcentaje();
}
