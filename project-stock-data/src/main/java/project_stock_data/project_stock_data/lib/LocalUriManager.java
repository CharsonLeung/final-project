package project_stock_data.project_stock_data.lib;

import org.springframework.web.util.UriComponentsBuilder;

public class LocalUriManager {

  private UriComponentsBuilder uriComponentsBuilder;

  public LocalUriManager() {
    this.uriComponentsBuilder = UriComponentsBuilder
        .newInstance()
        .scheme("http");
  }
  public static LocalUriManager newInstance() {
    return new LocalUriManager();
  }
  public LocalUriManager host() {
    String host = "localhost";
    this.uriComponentsBuilder.host(host);
    return this;
  }

  public LocalUriManager scheme(String scheme) {
    this.uriComponentsBuilder.scheme(scheme);
    return this;
  }
  public LocalUriManager port(Integer port) {
    this.uriComponentsBuilder.port(port);
    return this;
  }
  public LocalUriManager pathSegment(String... pathSegment) {
    this.uriComponentsBuilder.pathSegment(pathSegment);
    return this;
  }
  public LocalUriManager stockSymbol(String symbol) {
    // http://localhost:8080/data/stock?symbol=TSLA
    this.uriComponentsBuilder.path("stock?symbol=" + symbol);
    return this;
  }
  public LocalUriManager companySymbol(String symbol) {
    this.uriComponentsBuilder.path("companyprofile?symbol=" + symbol);
    return this;
  }
  public String getUrl() {
    return this.uriComponentsBuilder.build().toUriString();
  }
  public static void main(String[] args) {
    String url = LocalUriManager.newInstance()
                  .host()
                  .port(8080)
                  .pathSegment("data")
                  .companySymbol("AAPL")
                  .getUrl();
  System.out.println("url=" + url);
  // http://localhost:8080/data/stock?symbol=TSLA
  }
}
