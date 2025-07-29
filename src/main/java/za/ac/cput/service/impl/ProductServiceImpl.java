package za.ac.cput.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Product;
import za.ac.cput.repository.ProductRepository;
import za.ac.cput.service.ProductService;

import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {


private ProductRepository productRepository;
    @Autowired
public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product read(String productId) {
        return this.productRepository.findById(productId).orElse(null);
    }

    @Override
    public Product update(Product product) {
        return productRepository.save(product);
    }

    @Override
    public boolean delete(String productId) {
        if (productRepository.existsById(productId)) {
            productRepository.deleteById(productId);
            return true;
        }
        return false;
    }

    @Override
    public List<Product> getall() {
        return
                this.productRepository.findAll();
    }
}
