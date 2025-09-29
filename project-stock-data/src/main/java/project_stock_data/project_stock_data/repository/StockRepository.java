package project_stock_data.project_stock_data.repository;

import org.springframework.stereotype.Repository;
import project_stock_data.project_stock_data.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface StockRepository extends JpaRepository<StockEntity, Long> {

}
