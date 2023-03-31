package demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import demo.DTO.ProductDTO;
import demo.entity.ProductEntity;
import demo.global.ResponseObject;
import demo.repository.ProductRepository;
import demo.serviceimpl.ProductService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductRepository productRepository;

	@GetMapping("/product")
	public List<ProductDTO> getAll() {
		return productService.getAllProduct();
	}

	@GetMapping("/product/{id}")
	public ResponseEntity<ResponseObject> getProductById(@PathVariable Integer id) {
		Optional<ProductDTO> productDTO = Optional.ofNullable((productService.findProductById(id)));

		return productDTO.isPresent() ? ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseObject("ok", "Query product successfully", productDTO)) :

				ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ResponseObject("failed", "Can not find product with id = " + id, ""));
	}

	@PostMapping("/product")
	public ResponseEntity<ResponseObject> addProduct(@RequestBody ProductDTO productDTO) {

	    List<ProductDTO> listProductDTO = productService.findByName(productDTO.getName().trim());
	    if (listProductDTO.size() > 0) {
	        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
	                .body(new ResponseObject("failed", "Product name already taken", ""));
	    }

	    return ResponseEntity.status(HttpStatus.OK)
	            .body(new ResponseObject("ok", "Insert product successfully", productService.save(productDTO)));
	}
	
	@PutMapping("/product/{id}")
	public ResponseEntity<ResponseObject> update(@RequestBody ProductDTO productDTO, @PathVariable Integer id) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseObject("ok", "Update product successfully", productService.update(productDTO, id)));
	}

	@DeleteMapping("/product/{id}")
	public ResponseEntity<ResponseObject> delete(@PathVariable Integer id) {
		boolean exits = productRepository.existsById(id);
		if (exits) {
			productService.deleteProduct(id);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("ok", "Delete product successfully", ""));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseObject("failed", "Product does not exist ", ""));
		}
	}
	
	//Get productbyCategory
	
	@GetMapping("/shop/category/{id}")
	public List<ProductDTO> getProductsByCategoryId(@PathVariable int id) {
	
		return productService.getProductByCategoryId(id);
	}
	
	//Find by containing name
	 @GetMapping("/search")
	    public List<ProductDTO> findByNameContaining(@RequestParam("name") String name) {
	        List<ProductDTO> lProductDTO = productService.findByNameContaining(name);
	  
	            return lProductDTO;
	      
	    }

}
