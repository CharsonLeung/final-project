package project_stock_data.project_stock_data.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.fasterxml.jackson.core.JsonProcessingException;
import project_stock_data.project_stock_data.entity.StockEntity;
import project_stock_data.project_stock_data.entity.StockProfilesEntity;
import project_stock_data.project_stock_data.model.CompanyDTO;
import project_stock_data.project_stock_data.model.StockDTO;


public interface StockDataService {
  Optional<StockProfilesEntity> findProfile(Long id);
  StockDTO getStock(String symbol) throws JsonProcessingException;
  CompanyDTO getCompanyProfile(String symbol) throws JsonProcessingException;
  Map<String, StockProfilesEntity> findProfiles(List<StockEntity> stockEntities);
  StockProfilesEntity saveProfile(StockProfilesEntity stockProfilesEntities);
  void deleteAllProfiles();


}