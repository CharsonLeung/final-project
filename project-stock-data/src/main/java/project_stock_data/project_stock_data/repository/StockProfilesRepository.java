package project_stock_data.project_stock_data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project_stock_data.project_stock_data.entity.StockProfilesEntity;

@Repository
public interface StockProfilesRepository extends JpaRepository<StockProfilesEntity, Long>{
  
}
