package project_stock_data.project_stock_data.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import com.fasterxml.jackson.core.JsonProcessingException;
import project_stock_data.project_stock_data.entity.StockProfilesEntity;
import project_stock_data.project_stock_data.mapper.EntityMapper;
import project_stock_data.project_stock_data.model.CompanyDTO;
import project_stock_data.project_stock_data.repository.StockRepository;
import project_stock_data.project_stock_data.service.StockDataService;

public class AppStartRunner implements CommandLineRunner {
  @Autowired
  private StockDataService stockDataService;
  @Autowired
  private EntityMapper entityMapper;
  
  @Autowired
  private StockRepository stockRepository;
  @Autowired

  @Override
  public void run(String... args) throws Exception {
    this.stockDataService.deleteAllProfiles();
    this.stockRepository.findAll().forEach(s -> {
      try {
        try {
        CompanyDTO companyDTO =
          this.stockDataService.getCompanyProfile(s.getSymbol());
        StockProfilesEntity stockProfilesEntity = this.entityMapper.map(companyDTO);
        stockProfilesEntity.setStockEntity(s);
        this.stockDataService.saveProfile(stockProfilesEntity);
        } catch (JsonProcessingException e) {
          System.out.println("No symbols found.");
        }
        Thread.sleep(2000L);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        throw new RuntimeException("Interrupted while sleeping", e);
      }
    });
  }
}
