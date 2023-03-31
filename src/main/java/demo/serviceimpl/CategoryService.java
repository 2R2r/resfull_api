package demo.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.DTO.CategoryDTO;
import demo.entity.CategoryEntity;
import demo.repository.CategoryRepository;
import demo.service.ICategotyService;
@Service
public class CategoryService implements ICategotyService{
	ModelMapper mapper = new ModelMapper();
	
	@Autowired
	private CategoryRepository categoryRepository;
	@Override
	public CategoryDTO save(CategoryDTO categoryDTO) {
		CategoryEntity categoryEntity = mapper.map(categoryDTO, CategoryEntity.class);
		categoryRepository.save(categoryEntity);
		return mapper.map(categoryEntity, CategoryDTO.class);
	}
	@Override
	public List<CategoryDTO> getAllCategory() {
		List<CategoryEntity> categoryEntity = categoryRepository.findAll();
		List<CategoryDTO> listCategoryDTO = new ArrayList<>();
		for (CategoryEntity item : categoryEntity) {
			listCategoryDTO.add(mapper.map(item, CategoryDTO.class));
		}
		return listCategoryDTO;
	}
	@Override
	public CategoryDTO update(CategoryDTO categoryDTO, Integer id) {
		CategoryEntity exitingCategory = categoryRepository.getById(id);
		CategoryEntity categoryEntity = mapper.map(categoryDTO, CategoryEntity.class);
		BeanUtils.copyProperties(categoryEntity, exitingCategory, "id");
		exitingCategory	= categoryRepository.save(exitingCategory);
		return mapper.map(exitingCategory, CategoryDTO.class);
	}
	@Override
	public CategoryDTO findCategoyById(Integer id) {
		return mapper.map(categoryRepository.findById(id), CategoryDTO.class);
	}
	@Override
	public void deleteCategory(Integer id) {
		categoryRepository.deleteById(id);
		
	}
	@Override
	public List<CategoryDTO> findByCategoryName(String categoryName) {
		List<CategoryEntity> categoryEntity = categoryRepository.findByCategoryName(categoryName);
		List<CategoryDTO> listCategoryDTO = new ArrayList<>();
		for (CategoryEntity item : categoryEntity) {
			listCategoryDTO.add(mapper.map(item, CategoryDTO.class));
		}
		return listCategoryDTO;
	}

}
