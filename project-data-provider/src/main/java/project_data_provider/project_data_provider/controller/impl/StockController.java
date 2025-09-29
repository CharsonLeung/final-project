package project_data_provider.project_data_provider.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import project_data_provider.project_data_provider.controller.StockOperation;
import project_data_provider.project_data_provider.model.CompanyDTO;
import project_data_provider.project_data_provider.model.StockDTO;
import project_data_provider.project_data_provider.service.StockService;

@RestController
public class StockController implements StockOperation {
  
  @Override
  public String hello() {
    return "Hello!";
  }
  
  @Autowired
  private StockService stockService;
  
  @Override
  public StockDTO getStock(String symbol) throws JsonProcessingException {
    System.out.println("Symbol = " + symbol);
    System.out.println(stockService.getStock(symbol));
    return stockService.getStock(symbol);
  }

  @Override
  public CompanyDTO getCompanyProfile(String symbol) throws JsonProcessingException {
    System.out.println("Symbol = " + symbol);
    System.out.println(stockService.getCompanyProfile(symbol));
    return stockService.getCompanyProfile(symbol);
  }

  @Override
  public List<Object> getStockAndProfile(String symbol) throws JsonProcessingException {
    return stockService.getStockAndProfile(symbol);
  }

  @Override
  public List<StockDTO> getStocks() {
    return stockService.getStocks();
  }
  
}
