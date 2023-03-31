package demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import demo.entity.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
	List<ProductEntity> findByName(String name);
	
	 List<ProductEntity> findByNameContaining(String name);
	
//	List<ProductEntity> findByCategory_Id(int categoryId);
	

}
