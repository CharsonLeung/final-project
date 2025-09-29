package project_data_provider.project_data_provider.lib;

import org.springframework.web.util.UriComponentsBuilder;

public class UriManager {

  private UriComponentsBuilder uriComponentsBuilder;

  public UriManager() {
    this.uriComponentsBuilder = UriComponentsBuilder
        .newInstance()
        .scheme("https");
  }
  public static UriManager newInstance() {
    return new UriManager();
  }
  public UriManager host(String host) {
    this.uriComponentsBuilder.host(host);
    return this;
  }

  public UriManager scheme(String scheme) {
    this.uriComponentsBuilder.scheme(scheme);
    return this;
  }
  public UriManager path(String path) {
    this.uriComponentsBuilder.path(path);
    return this;
  }
  public UriManager pathSegment(String... pathSegment) {
    this.uriComponentsBuilder.pathSegment(pathSegment);
    return this;
  }
  public UriManager stockSymbol(String symbol) {
    this.uriComponentsBuilder.path("/quote?symbol=" + symbol);
    return this;
  }
  public UriManager companySymbol(String symbol) {
    this.uriComponentsBuilder.path("/stock/profile2?symbol=" + symbol);
    return this;
  }
  public UriManager token() {
    String token = "d2hhihpr01qon4ec95j0d2hhihpr01qon4ec95jg"; // **** CENSORED ****
    this.uriComponentsBuilder.path("&token=" + token);
    return this;
  }
  public String getUrl() {
    return this.uriComponentsBuilder.build().toUriString();
  }
  public static void main(String[] args) {
    String url = UriManager.newInstance()
                  .host("finnhub.io")
                  .pathSegment("api/v1")
                  .companySymbol("AAPL")
                  .token()
                  .getUrl();
  System.out.println(url);  
  }
}
