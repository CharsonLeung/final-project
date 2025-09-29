package project_stock_data.project_stock_data.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import project_stock_data.project_stock_data.controller.StockUpdateOperation;
import project_stock_data.project_stock_data.model.CompanyDTO;
import project_stock_data.project_stock_data.model.StockDTO;
import project_stock_data.project_stock_data.service.StockDataService;

@RestController
public class StockUpdateController implements StockUpdateOperation {

  @Autowired
  private StockDataService stockDataService;

  @Override
  public StockDTO getStock(String symbol) throws JsonProcessingException {
    System.out.println("Symbol = " + symbol);
    return stockDataService.getStock(symbol);
  }


  @Override
  public CompanyDTO getCompanyProfile(String symbol) throws JsonProcessingException {
    System.out.println("Symbol = " + symbol);
    return stockDataService.getCompanyProfile(symbol);
  }
  
}
