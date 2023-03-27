package com.company.apirest.backend.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.apirest.backend.model.Suma;

@Repository
public interface ISumaDao  extends JpaRepository<Suma, Long>{
	 Suma findFirstByOrderByIdDesc();
}
