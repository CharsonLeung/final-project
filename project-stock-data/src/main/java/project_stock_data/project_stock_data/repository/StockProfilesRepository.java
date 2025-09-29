package project_stock_data.project_stock_data.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project_stock_data.project_stock_data.entity.StockEntity;
import project_stock_data.project_stock_data.entity.StockProfilesEntity;

@Repository
public interface StockProfilesRepository extends JpaRepository<StockProfilesEntity, Long> {
  // List<StockProfilesEntity> findByMarket(String market);

  Optional<StockProfilesEntity> findByStockEntity(StockEntity stockEntity);

  List<StockProfilesEntity> findByStockEntityIn(List<StockEntity> stockEntities);

  void deleteByStockEntity(StockEntity stockEntity);
}