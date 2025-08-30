package project_data_provider.project_data_provider.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import project_data_provider.project_data_provider.lib.UriManager;
import project_data_provider.project_data_provider.model.CompanyDTO;
import project_data_provider.project_data_provider.model.StockDTO;
import project_data_provider.project_data_provider.service.StockService;

@Service
public class StockServiceImpl implements StockService {
  @Autowired
  private RestTemplate restTemplate;

  @Override
  public StockDTO getStock(String symbol) throws JsonProcessingException {
    String url = UriManager.newInstance()
                              .host("finnhub.io")
                              .pathSegment("api/v1")
                              .stockSymbol(symbol)
                              .token()
                              .getUrl();
    
      ResponseEntity<StockDTO> stock = this.restTemplate.exchange(url, HttpMethod.GET, null, StockDTO.class);
        return stock.getBody();
  }

  @Override
  public CompanyDTO getCompanyProfile(String symbol) throws JsonProcessingException {
    String url = UriManager.newInstance()
                              .host("finnhub.io")
                              .pathSegment("api/v1")
                              .companySymbol(symbol)
                              .token()
                              .getUrl();
    
      ResponseEntity<CompanyDTO> company = this.restTemplate.exchange(url, HttpMethod.GET, null, CompanyDTO.class);
        return company.getBody();
  }

  @Override
  public List<Object> getStockAndProfile(String symbol) throws JsonProcessingException {
    Object [] stockAndProfile = new Object[2];
    stockAndProfile[0] = getStock(symbol);
    stockAndProfile[1] = getCompanyProfile(symbol);
    return Arrays.asList(stockAndProfile);
  }
  
  @Override
  public List<StockDTO> getStocks() {
    Integer size = 503;
    StockDTO[] stockDTOs = new StockDTO[size];
    List<String> urls = new ArrayList<>();
    String csvUrl = 
      "https://raw.githubusercontent.com/datasets/s-and-p-500-companies/main/data/constituents.csv";
    String delimiter = ",";
    String[] symbols = {"Symbol"};

      try {
          URL fileUrl = new URI(csvUrl).toURL();
                BufferedReader reader = new BufferedReader(new InputStreamReader(fileUrl.openStream()));
                String line;
                boolean isSymbol = true;
                List<Integer> columnIndices = new ArrayList<>();
                
                while ((line = reader.readLine()) != null) {
                  String[] values = line.split(delimiter);  
                  
                  if (isSymbol) {
                  for (String desiredCol : symbols) {
                      for (int i = 0; i < values.length; i++) { // values.length
                        if (values[i].trim().equalsIgnoreCase(desiredCol.trim())) {
                          columnIndices.add(i);
                          break;
                        }
                      }
                    }
                    isSymbol = false;
                  } else {
                    for (int index : columnIndices) {
                      if (index < values.length) { // values.length
                      String url = UriManager.newInstance()
                              .host("finnhub.io")
                              .pathSegment("api/v1")
                              .stockSymbol(values[index].trim())
                              .token()
                              .getUrl();
                      urls.add(url);
                      }
                    } 
                  }
                }
      for (int i = 0; i < urls.size(); i++) {
        if ((double)((i + 1) % 60) != 0) {
      ResponseEntity<StockDTO> stock = this.restTemplate.exchange(urls.get(i), HttpMethod.GET, null, StockDTO.class);
      stockDTOs[i] = stock.getBody();
        } else {
          Thread.sleep(60 * 1000);
          ResponseEntity<StockDTO> stock = this.restTemplate.exchange(urls.get(i), HttpMethod.GET, null, StockDTO.class);
          stockDTOs[i] = stock.getBody();
              }
            }
                reader.close();

            } catch (IOException | URISyntaxException | InterruptedException e) {
                e.printStackTrace();
            }
              
            return Arrays.asList(stockDTOs);
            
        }

  }
