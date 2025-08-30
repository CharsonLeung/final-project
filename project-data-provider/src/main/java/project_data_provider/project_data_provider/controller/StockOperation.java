package project_data_provider.project_data_provider.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.fasterxml.jackson.core.JsonProcessingException;
import project_data_provider.project_data_provider.model.CompanyDTO;
import project_data_provider.project_data_provider.model.StockDTO;

public interface StockOperation {
  
  @GetMapping(value = "/hello")
  String hello();

  @GetMapping(value ="/stock")
  StockDTO getStock(@RequestParam String symbol)
    throws JsonProcessingException;

  @GetMapping(value = "/companyprofile")
  CompanyDTO getCompanyProfile(@RequestParam String symbol)
    throws JsonProcessingException;
  
  @GetMapping(value = "/stockandprofile")
  List<Object> getStockAndProfile(@RequestParam String symbol)
    throws JsonProcessingException;
  
  @GetMapping(value ="/stocks")
  List<StockDTO> getStocks();


}
