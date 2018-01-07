package com.deshaw.deshawhackerearth;

import java.util.HashMap;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import com.deshaw.deshawhackerearth.dao.crudrepository.MarketDataRepository;
import com.deshaw.deshawhackerearth.dao.crudrepository.SymbolEntityRepository;
import com.deshaw.deshawhackerearth.dao.model.MarketDataEntity;
import com.deshaw.deshawhackerearth.dao.model.SymbolEntity;

public class ScheduledTask {

	@Value("${rest.url}")
	private String url;
	
	private HashMap<String, String> hashmap = new HashMap<>(1);
	
	@Autowired
	private SymbolEntityRepository symbol;
	
	@Autowired
	private MarketDataRepository market;
	
	@Autowired
	private RestTemplate resttemplate;
	
	@Scheduled(fixedRate=60000)
	public void updateMarketDataEntity()
	{
		symbol.findAll().stream().map(mapObjects).count();
	}
	
	Function<SymbolEntity, MarketDataEntity> mapObjects = (input)->
	{
		hashmap.put("SYMBOL", input.getSymbol());
		System.out.println(resttemplate.getForObject(url,String.class,hashmap));
		return new MarketDataEntity();
	};
}
