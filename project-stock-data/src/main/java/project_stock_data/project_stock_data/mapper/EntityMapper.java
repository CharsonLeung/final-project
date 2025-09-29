package project_stock_data.project_stock_data.mapper;

import java.time.LocalDate;
import org.springframework.stereotype.Component;
import project_stock_data.project_stock_data.entity.StockProfilesEntity;
import project_stock_data.project_stock_data.model.CompanyDTO;

@Component
public class EntityMapper {
  public StockProfilesEntity map(CompanyDTO companyDTO) {
    LocalDate ipoDate = 
      companyDTO.getIpo() != null ? LocalDate.parse(companyDTO.getIpo()) : null;
    return StockProfilesEntity.builder() //
        .country(companyDTO.getCountry())
        .currency(companyDTO.getCurrency())
        .estimateCurrency(companyDTO.getEstimateCurrency())
        .exchange(companyDTO.getExchange())
        .finnhubIndustry(companyDTO.getFinnhubIndustry())
        .ipoDate(ipoDate)
        .logo(companyDTO.getLogo())
        .marketCapitalization(companyDTO.getMarketCapitalization())
        .name(companyDTO.getName())
        .phone(companyDTO.getPhone())
        .shareOutstanding(companyDTO.getShareOutstanding())
        .ticker(companyDTO.getTicker())
        .weburl(companyDTO.getWeburl())
        .build();
  }
  
}
