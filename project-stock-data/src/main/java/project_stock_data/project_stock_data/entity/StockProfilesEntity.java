package project_stock_data.project_stock_data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "stock_profiles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class StockProfilesEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String ticker;
  @Column(nullable = false)
  private String name;
  @Column(nullable = false)
  private String finnhubIndustry; 
  @Column(nullable = false)
  private String weburl;
  @Column(nullable = false)
  private String phone;
  @Column(nullable = false)
  private String country;
  @Column(nullable = false)
  private String currency;
  @Column(nullable = false)
  private String estimateCurrency;
  @Column(nullable = false)
  private String exchange;
  @Column(nullable = false)
  private String ipo;
  @Column(nullable = false)
  private String logo;
  @Column(nullable = false)
  private Double marketCapitalization;
  @Column(nullable = false)
  private Double shareOutstanding;
  
  @OneToOne
  @JoinColumn(name = "stock_symbol", nullable = false)
  private StockEntity stockEntity;
}
