package project_stock_data.project_stock_data.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project_stock_data.project_stock_data.model.CompanyDTO;
import project_stock_data.project_stock_data.model.StockDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

@RequestMapping("/update")
public interface StockUpdateOperation {
  
  @GetMapping(value = "/companyfile")
  CompanyDTO getCompanyProfile(@RequestParam String symbol)
    throws JsonProcessingException;

  @GetMapping(value = "/stock")
  StockDTO getStock(@RequestParam String symbol)
    throws JsonProcessingException;
}
 

