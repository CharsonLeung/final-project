package project_data_provider.project_data_provider.lib;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class StockSymbol {

  public static void main(String[] args) {
    //List<String> stockSymbol = new ArrayList<>();
    String csvUrl = 
      "https://raw.githubusercontent.com/datasets/s-and-p-500-companies/main/data/constituents.csv";
    String delimiter = ",";
    String[] symbols = {"Symbol"};
    List<String> urls = new ArrayList<>();

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
                      for (int i = 0; i < values.length; i++) {
                        if (values[i].trim().equalsIgnoreCase(desiredCol.trim())) {
                          columnIndices.add(i);
                          break;
                        }
                      }
                    }
                    isSymbol = false;
                  } else {
                    for (int index : columnIndices) {
                      if (index < values.length) {
                      String url = UriManager.newInstance()
                              .host("finnhub.io")
                              .pathSegment("api/v1")
                              .stockSymbol(values[index].trim())
                              .token()
                              .getUrl();
                        urls.add(url);
                               
                        //System.out.println(values[index].trim());// + "\t");
                      }
                    }
                  }
                }
                reader.close();
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }  
            System.out.println(urls.get(1));
         
        }
  }

