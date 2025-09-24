package project_stock_data.project_stock_data.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import project_stock_data.project_stock_data.model.CompanyDTO;
import project_stock_data.project_stock_data.model.StockDTO;

@Service
public class StockDataService {

  private final RestClient restClient;

  public StockDataService(RestClient.Builder restClientBuilder) {
    this.restClient = restClientBuilder.baseUrl("http://localhost:8080").build();
  }

  public String hello() {
    return restClient.get()
                    .uri("/hello")
                    .retrieve()
                    .body(String.class);
  }

  public StockDTO getStock(String symbol) {
    return restClient.get()
                    .uri("/stock")
                    .retrieve()
                    .body(StockDTO.class);
  }

  public CompanyDTO getCompanyProfile(String symbol) {
    return restClient.get()
                    .uri("/companyprofile")
                    .retrieve()
                    .body(CompanyDTO.class);
  }
  
}