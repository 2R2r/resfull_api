package demo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import demo.entity.ProductEntity;
import demo.repository.ProductRepository;

@SpringBootTest
class MainApplicationTests {
	@Autowired ProductRepository productRepository;
	@Test
	void contextLoads() {
	}
	
}
