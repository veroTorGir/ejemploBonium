package org.example.service.impl;

import org.example.entities.ProductEntity;
import org.example.repositories.ProductRepository;
import org.example.repositories.OrderRepository;
import org.example.service.ProductService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    public ProductServiceImpl(ProductRepository productRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }
    @Override
    public ProductEntity createProduct(ProductEntity product) {
        return productRepository.save(product);
    }
    @Override
    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }
    @Override
    public ProductEntity getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }
    @Override
    public ProductEntity updateProduct(Long id, ProductEntity product) {
        ProductEntity existing = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        existing.setName(product.getName());
        existing.setDescription(product.getDescription());
        existing.setPrecio(product.getPrecio());
        existing.setPlato(product.isPlato());
        return productRepository.save(existing);
    }

    public void deleteProduct(Long id) {
        // Solo borrar si NO tiene órdenes asociadas
        if (!orderRepository.findByProductId(id).isEmpty()) {
            throw new RuntimeException("No se puede eliminar el producto porque tiene órdenes asociadas");
        }
        productRepository.deleteById(id);
    }
}
