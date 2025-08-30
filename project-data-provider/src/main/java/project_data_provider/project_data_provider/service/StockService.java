package project_data_provider.project_data_provider.service;

import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import project_data_provider.project_data_provider.model.CompanyDTO;
import project_data_provider.project_data_provider.model.StockDTO;

public interface StockService {
  StockDTO getStock(String symbol) throws JsonProcessingException;
  CompanyDTO getCompanyProfile(String symbol) throws JsonProcessingException;
  List<Object> getStockAndProfile(String symbol) throws JsonProcessingException;
  List<StockDTO> getStock2();
  List<StockDTO> getStocks();
}
