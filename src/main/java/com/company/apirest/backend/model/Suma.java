package com.company.apirest.backend.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sumas")
public class Suma implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2164553723990982332L;
	
	
	
	/*public Suma(long id, long fecha, float num1, float num2,  float porcentaje, float resultado) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.num1 = num1;
		this.num2 = num2;
		this.porcentaje = porcentaje;
		this.resultado = resultado;
		
	}*/
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "timestamp")
	private Long timestamp;
	
	@Column(name = "num1")
	private Float num1;
	
	@Column(name = "num2")
	private Float num2;
	
	@Column(name = "porcentaje")
	private Float porcentaje;
	
	@Column(name = "resultado")
	private Float resultado;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public Float getResultado() {
		return resultado;
	}

	public void setResultado(Float resultado) {
		this.resultado = resultado;
	}

	public Float getNum1() {
		return num1;
	}

	public void setNum1(Float num1) {
		this.num1 = num1;
	}

	public Float getNum2() {
		return num2;
	}

	public void setNum2(Float num2) {
		this.num2 = num2;
	}

	public Float getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(Float porcentaje) {
		this.porcentaje = porcentaje;
	}



}