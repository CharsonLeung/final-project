package project_stock_data.project_stock_data.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import project_stock_data.project_stock_data.entity.StockEntity;
import project_stock_data.project_stock_data.entity.StockProfilesEntity;
import project_stock_data.project_stock_data.lib.LocalUriManager;
import project_stock_data.project_stock_data.model.CompanyDTO;
import project_stock_data.project_stock_data.model.StockDTO;
import project_stock_data.project_stock_data.repository.StockProfilesRepository;
import project_stock_data.project_stock_data.repository.StockRepository;
import project_stock_data.project_stock_data.service.StockDataService;

@Service
public class StockDataServiceImpl implements StockDataService {
  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private StockProfilesRepository stockProfilesRepository;

  @Autowired
  private StockRepository stockRepository;

  @Override
  public Optional<StockProfilesEntity> findProfile(Long id) {
    StockEntity stockEntity = this.stockRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Invalid Stock ID."));
    return this.stockProfilesRepository.findByStockEntity(stockEntity);
  }

  @Override
  public StockDTO getStock(String symbol) throws JsonProcessingException {
    String url = LocalUriManager.newInstance()
                              .host()
                              .port(8080)
                              .pathSegment("data")
                              .stockSymbol(symbol)
                              .getUrl();
                              System.out.println(url);
    ResponseEntity<StockDTO> stock = this.restTemplate.exchange(url, HttpMethod.GET, null, StockDTO.class);
        return stock.getBody();
    }

    @Override
    public CompanyDTO getCompanyProfile(String symbol) throws JsonProcessingException {
      String url = LocalUriManager.newInstance()
                  .host()
                  .port(8080)
                  .pathSegment("data")
                  .companySymbol(symbol)
                  .getUrl();
      System.out.println(url);
    ResponseEntity<CompanyDTO> company = this.restTemplate.exchange(url, HttpMethod.GET, null, CompanyDTO.class);
        return company.getBody();
    }

    @Override
    public Map<String, StockProfilesEntity> findProfiles(List<StockEntity> stockEntities) {
      return this.stockProfilesRepository.findByStockEntityIn(stockEntities).stream()
          .collect(Collectors.toMap(e -> e.getTicker(), e -> e));
    }

    @Override
    public StockProfilesEntity saveProfile(StockProfilesEntity stockProfilesEntities) {
      return this.stockProfilesRepository.save(stockProfilesEntities);
    }
    @Override
    public void deleteAllProfiles() {
      this.stockProfilesRepository.deleteAll();
    }
    

public static void main(String[] args) {
  StockDataServiceImpl obj = new StockDataServiceImpl();
  try {
  System.out.println(obj.getCompanyProfile("AAPL"));
} catch (JsonProcessingException e) {System.out.println(e);}
      
    }
  }