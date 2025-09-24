package project_stock_data.project_stock_data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class StockDTO {
  private Float c;
  private Float d;
  private Float dp;
  private Float h;
  private Float l;
  private Float o;
  private Float pc;
  private Integer t;

}
