package project_stock_data.project_stock_data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
  private String symbol;
  @Column(nullable = false, name = "current_price")
  private Float c;
  @Column(nullable = false, name = "change")
  private Float d;
  @Column(nullable = false, name = "percent_change")
  private Float dp;
  @Column(nullable = false, name = "high_price_of_the_day")
  private Float h;
  @Column(nullable = false, name = "low_price_of_the_day")
  private Float l;
  @Column(nullable = false, name = "open_price_of_the_day")
  private Float o;
  @Column(nullable = false, name = "previous_close_price")
  private Float pc;
  @Column(nullable = false, name = "unix")
  private Integer t;
  @Column(nullable = false, name = "volume")
  private Integer v;

  @ManyToOne
  @JoinColumn(name = "stock_symbol", nullable = false)
  private StockEntity stockEntity;

  
}
