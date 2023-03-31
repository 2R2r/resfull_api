package demo.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.DTO.CategoryDTO;
import demo.DTO.ProductDTO;
import demo.entity.CategoryEntity;
import demo.entity.ProductEntity;
import demo.repository.CategoryRepository;
import demo.repository.ProductRepository;
import demo.service.IProductService;


@Service
public class ProductService implements IProductService {
	ModelMapper mapper = new ModelMapper();

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public ProductDTO save(ProductDTO productDTO) {
		ProductEntity productEntity = mapper.map(productDTO, ProductEntity.class);
		Optional<CategoryEntity> categoryEntity = categoryRepository.findById(productDTO.getCategoryId());
		productEntity.setCategory(categoryEntity.get());
		productRepository.save(productEntity);
		return mapper.map(productEntity, ProductDTO.class);
	}

	@Override
	public List<ProductDTO> getAllProduct() {
		List<ProductEntity> list = new ArrayList<>();
		List<ProductDTO> result = new ArrayList<>();
		list = productRepository.findAll();
		for (ProductEntity productEntity : list) {
			result.add(mapper.map(productEntity, ProductDTO.class));
		}
		return result;
	}

	@Override
	public ProductDTO update(ProductDTO productDTO, Integer id) {
		ProductEntity exitingProduct = productRepository.getById(id);
		CategoryEntity categoryEntity = categoryRepository.getById(productDTO.getCategoryId());
		ProductEntity productEntity = mapper.map(productDTO, ProductEntity.class);
		BeanUtils.copyProperties(productEntity, exitingProduct, "id");
		exitingProduct.setCategory(categoryEntity);
		productRepository.save(exitingProduct);
		return mapper.map(exitingProduct, ProductDTO.class);
	}

	@Override
	public ProductDTO findProductById(Integer id) {
		return mapper.map(productRepository.findById(id), ProductDTO.class);
	}

	@Override
	public void deleteProduct(Integer id) {
		productRepository.deleteById(id);

	}
	
	@Override
	public List<ProductDTO> findByName(String name) {
		List<ProductEntity> lProdutcEntity  = productRepository.findByName(name);
		List<ProductDTO> lproductDTO = new ArrayList<>();
		for (ProductEntity productEntity : lProdutcEntity) {
			lproductDTO.add(mapper.map(productEntity, ProductDTO.class));
		}
		return lproductDTO;
	}

	@Override
	public List<ProductDTO> findByNameContaining(String name) {
		List<ProductEntity> lProdutcEntity  = productRepository.findByNameContaining(name);
		List<ProductDTO> lproductDTO = new ArrayList<>();
		for (ProductEntity productEntity : lProdutcEntity) {
			lproductDTO.add(mapper.map(productEntity, ProductDTO.class));
		}
		System.out.println(lproductDTO);
		return lproductDTO;
	}

//	@Override
//	public List<ProductDTO> findByName1(String name) {
//		List<ProductEntity> lProdutcEntity  = productRepository.findByNameContaining(name);
//		List<ProductDTO> productDTOs = new ArrayList<>();
//		for (ProductEntity productEntity : lProdutcEntity) {
//			productDTOs.add(mapper.map(productEntity, ProductDTO.class));
//		}
//		return productDTOs;
//	}

//	@Override
//	public List<ProductDTO> findByName2(String name) {
//		List<ProductEntity> lProdutcEntity  = productRepository.findByNameLike(name);
//		List<ProductDTO> productDTOs = new ArrayList<>();
//		for (ProductEntity productEntity : lProdutcEntity) {
//			productDTOs.add(mapper.map(productEntity, ProductDTO.class));
//		}
//		return productDTOs;
//	}
	
	
	// Get All Products by Category Id

	@Override
	public List<ProductDTO> getProductByCategoryId(int CategoryId) {
		List<ProductEntity> lProdutcEntity  = productRepository.findAll();
		List<ProductDTO> lproductDTO = new ArrayList<>();
		List<ProductDTO> lproductDTO2 = new ArrayList<>();
		for (ProductEntity productEntity : lProdutcEntity) {
			lproductDTO.add(mapper.map(productEntity, ProductDTO.class));
		}
		
		CategoryDTO categoryDTO = mapper.map(categoryRepository.findById(CategoryId), CategoryDTO.class);
		for (ProductDTO productDTO : lproductDTO) {
			if (productDTO.getCategoryId() == categoryDTO.getId()) {
				lproductDTO2.add(productDTO);
			}
		}
		return lproductDTO2;
	}


}


