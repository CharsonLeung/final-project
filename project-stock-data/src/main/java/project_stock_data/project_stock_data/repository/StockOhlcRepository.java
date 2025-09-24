package project_stock_data.project_stock_data.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project_stock_data.project_stock_data.entity.StockOhlcEntity;

@Repository
public interface StockOhlcRepository extends JpaRepository<StockOhlcEntity, Long>{
  Optional<StockOhlcEntity> findOhlcBySymbol(String Symbol);
}
