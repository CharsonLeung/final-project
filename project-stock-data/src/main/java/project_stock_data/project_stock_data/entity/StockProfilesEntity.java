package project_stock_data.project_stock_data.entity;

import java.io.Serializable;
import java.time.LocalDate;
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
import lombok.Setter;

@Entity
@Table(name = "stock_profiles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class StockProfilesEntity implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false)
  private String ticker;
  @Column(nullable = false)
  private String name;
  @Column(name = "industry", nullable = false)
  private String finnhubIndustry; 
  @Column(nullable = false)
  private String weburl;
  @Column(nullable = false)
  private String phone;
  @Column(nullable = false)
  private String country;
  @Column(nullable = false)
  private String currency;
  @Column(name = "estimate_currency", nullable = false)
  private String estimateCurrency;
  @Column(nullable = false)
  private String exchange;
  @Column(name = "ipo_date", nullable = false)
  private LocalDate ipoDate;
  @Column(nullable = false)
  private String logo;
  @Column(name = "market_cap", nullable = false)
  private Double marketCapitalization;
  @Column(name = "share_outstanding", nullable = false)
  private Double shareOutstanding;
  
  @Setter
  @ManyToOne
  @JoinColumn(name = "stock_id", nullable = true)
  private StockEntity stockEntity;
}