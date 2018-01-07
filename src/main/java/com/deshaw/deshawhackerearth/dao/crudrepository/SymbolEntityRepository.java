package com.deshaw.deshawhackerearth.dao.crudrepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deshaw.deshawhackerearth.dao.model.SymbolEntity;


public interface SymbolEntityRepository extends JpaRepository<SymbolEntity, String>{
	
	List<SymbolEntity> findAllByOrderByNameAsc();
	SymbolEntity findBySymbol(String symbol);
}
