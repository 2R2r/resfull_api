package demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import demo.DTO.CategoryDTO;
import demo.entity.CategoryEntity;
import demo.entity.ProductEntity;
import demo.global.ResponseObject;
import demo.repository.CategoryRepository;
import demo.serviceimpl.CategoryService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	
	@GetMapping("/category")
	public List<CategoryDTO> getAll(){
		return categoryService.getAllCategory();
	}
	@GetMapping("/category/{id}")
	public ResponseEntity<ResponseObject> getCategoryById(@PathVariable Integer id){
		Optional<CategoryDTO> categoryDTO = Optional.ofNullable(categoryService.findCategoyById(id));
		if (categoryDTO.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseObject("ok","Query category successfull",categoryDTO));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
				new ResponseObject("failed","Can not find category with id = " + id,""));
		
	}
 
	@PostMapping("/category")
	public ResponseEntity<ResponseObject> addCategory(@RequestBody CategoryDTO categoryDTO) {
		List<CategoryDTO> lCategoryDTO = categoryService.findByCategoryName(categoryDTO.getCategoryName().trim());
		if (lCategoryDTO.size() > 0) {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
					new ResponseObject("failed","Categoty name already taken",""));
		}
		return ResponseEntity.status(HttpStatus.OK).body(
				new ResponseObject("ok","Insert Categoty successfull",categoryService.save(categoryDTO)));
	}
	
	@PutMapping("/category/{id}")
	public ResponseEntity<ResponseObject> updateCategory(@RequestBody CategoryDTO categoryDTO,@PathVariable Integer id) {
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseObject("ok","Update Categoty successfull",categoryService.update(categoryDTO, id)));
		
	}
	
	@DeleteMapping("/category/{id}")
	public ResponseEntity<ResponseObject> updateCategory(@PathVariable Integer id) {
		boolean exits = categoryRepository.existsById(id);
		if (exits) {
			categoryRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseObject("ok","Delete Categoty successfull",""));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
				new ResponseObject("failed","Categoty does not exits",""));
	}
	
	
}
