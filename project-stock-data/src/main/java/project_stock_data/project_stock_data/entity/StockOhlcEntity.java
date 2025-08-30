package project_stock_data.project_stock_data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "stock_ohlc_data")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class StockOhlcEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Float c;
  @Column(nullable = false, name = "Change")
  private Float d;
  @Column(nullable = false, name = "Percent_change")
  private Float dp;
  @Column(nullable = false, name = "High_price_of_the_day")
  private Float h;
  @Column(nullable = false, name = "Low_price_of_the_day")
  private Float l;
  @Column(nullable = false, name = "Open_price_of_the_day")
  private Float o;
  @Column(nullable = false, name = "Previous_close_price")
  private Float pc;
  @Column(nullable = false, name = "Unix")
  private Integer t;

  
  
}
