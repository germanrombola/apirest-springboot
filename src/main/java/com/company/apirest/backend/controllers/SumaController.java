package com.company.apirest.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.apirest.backend.response.SumaResponseRest;
import com.company.apirest.backend.service.ISumaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping("/v1")
@Api(value="sumar", description="APIREST Endpoint de suma")
public class SumaController {

	@Autowired
	private ISumaService service;
	
	@ApiOperation(value = "Obtener la suma de dos números incrementado en un porcentaje", notes = "En caso de exito devuelve un resultado, sino un error..")
	@ApiResponses(value = {
			  @ApiResponse(code = 200, message = "Respuesta exitosa"),
			  @ApiResponse(code = 403, message = "No permitido"),
			  @ApiResponse(code = 404, message = "No encontrado - No se encontró la respuesta")
			})
	@GetMapping("/sumar")
	public ResponseEntity<SumaResponseRest>  sumarConPorcentaje(@RequestParam float num1, @RequestParam float num2) {

		ResponseEntity<SumaResponseRest> response = service.sumaNum(num1, num2);
		return response;

	}

	// getters y setters para ultimoResultado
}