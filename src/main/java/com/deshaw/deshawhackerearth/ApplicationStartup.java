package com.deshaw.deshawhackerearth;

import java.io.FileReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.deshaw.deshawhackerearth.dao.crudrepository.MarketDataRepository;
import com.deshaw.deshawhackerearth.dao.crudrepository.SymbolEntityRepository;
import com.deshaw.deshawhackerearth.dao.model.MarketDataEntity;
import com.deshaw.deshawhackerearth.dao.model.SymbolEntity;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent>{
	
	@Autowired
	private MarketDataRepository marketData;
	
	@Autowired
	private SymbolEntityRepository symbol;

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		populateSymbol();
		populateMarketEntity();
	}

	private static final String [] SYMBOL_FILE_HEADER_MAPPING = {"Symbol","Name","MarketCap","Sector","Industry"};
	@Transactional
	private void populateSymbol() {	
		 CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(SYMBOL_FILE_HEADER_MAPPING);
		 try(CSVParser csvParser = new CSVParser(new FileReader(getClass()
						.getClassLoader().getResource("stocksf081a85.csv").getFile()), csvFileFormat)){
			 symbol.save(csvParser.getRecords().stream().skip(1).map(mapSymbolEntity).collect(Collectors.toList()));
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	private static final String [] MARKET_FILE_HEADER_MAPPING = {"date","symbol","open","close","low","high", "volume"};
	@Transactional
	private void populateMarketEntity() {
		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(MARKET_FILE_HEADER_MAPPING);
		 try(CSVParser csvParser = new CSVParser(new FileReader(getClass()
						.getClassLoader().getResource("prices763fefc.csv").getFile()), csvFileFormat)){
			 marketData.save(csvParser.getRecords().stream().skip(1).map(mapMarketDataEntity).collect(Collectors.toList()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Function<CSVRecord, SymbolEntity> mapSymbolEntity = (record) ->{
		SymbolEntity symbolEntity = new SymbolEntity();
		symbolEntity.setName(record.get(SYMBOL_FILE_HEADER_MAPPING[1]));
		symbolEntity.setSymbol(record.get(SYMBOL_FILE_HEADER_MAPPING[0]));
		symbolEntity.setMarketCap(new BigDecimal(record.get(SYMBOL_FILE_HEADER_MAPPING[2])));
		symbolEntity.setSector(record.get(SYMBOL_FILE_HEADER_MAPPING[3]));
		symbolEntity.setIndustry(record.get(SYMBOL_FILE_HEADER_MAPPING[4]));
		return symbolEntity;
	};
	
	private Function<CSVRecord, MarketDataEntity> mapMarketDataEntity = (record) -> {
		MarketDataEntity marketDataEntity = new MarketDataEntity();
		marketDataEntity.setDate(LocalDate.parse(record.get(MARKET_FILE_HEADER_MAPPING[0]).split(" ")[0],DateTimeFormatter.ofPattern("yyyy-M-dd")));
		marketDataEntity.setSymbol(symbol.findBySymbol(record.get(MARKET_FILE_HEADER_MAPPING[1]).trim()));
		marketDataEntity.setOpen(Double.valueOf(record.get(MARKET_FILE_HEADER_MAPPING[2]).trim()));
		marketDataEntity.setClose(Double.valueOf(record.get(MARKET_FILE_HEADER_MAPPING[3]).trim()));
		marketDataEntity.setLow(Double.valueOf(record.get(MARKET_FILE_HEADER_MAPPING[4]).trim()));
		marketDataEntity.setHigh(Double.valueOf(record.get(MARKET_FILE_HEADER_MAPPING[5]).trim()));
		marketDataEntity.setVolume(new BigDecimal(record.get(MARKET_FILE_HEADER_MAPPING[6]).trim()));
		return marketDataEntity;
	};
}
