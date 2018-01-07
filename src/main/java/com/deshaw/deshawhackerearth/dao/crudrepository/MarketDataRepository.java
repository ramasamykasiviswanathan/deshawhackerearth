package com.deshaw.deshawhackerearth.dao.crudrepository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deshaw.deshawhackerearth.dao.model.MarketDataEntity;

public interface MarketDataRepository extends JpaRepository<MarketDataEntity, LocalDateTime> {
	List<MarketDataEntity> findSymbolByOrderByDateDesc(String symbol);
}
