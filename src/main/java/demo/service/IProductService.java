package demo.service;

import java.util.List;



import demo.DTO.ProductDTO;
import demo.entity.ProductEntity;

public interface IProductService {
	ProductDTO save(ProductDTO productDTO);

	List<ProductDTO> getAllProduct();

	ProductDTO update(ProductDTO productDTO, Integer id);

	ProductDTO findProductById(Integer id);

	void deleteProduct(Integer id);
	
	List<ProductDTO> getProductByCategoryId(int CategoryId);
	
	 List<ProductDTO> findByNameContaining(String name);
	 
	List<ProductDTO> findByName(String name);
//	List<ProductDTO> findByName2( String name);
}
