package demo.service;

import java.util.List;

import demo.DTO.CategoryDTO;
import demo.entity.CategoryEntity;


public interface ICategotyService {
	CategoryDTO save(CategoryDTO categoryDTO);
	
	List<CategoryDTO> getAllCategory();

	CategoryDTO update(CategoryDTO categoryDTO, Integer id);

	CategoryDTO findCategoyById(Integer id);

	void deleteCategory(Integer id);
	List<CategoryDTO> findByCategoryName(String categoryName);

}
