package vn.edu.iuh.fit.donchung.backend.services.impl;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import vn.edu.iuh.fit.donchung.backend.dtos.ProductDto;
import vn.edu.iuh.fit.donchung.backend.entities.Product;
import vn.edu.iuh.fit.donchung.backend.mapper.ProductMapper;
import vn.edu.iuh.fit.donchung.backend.repositories.ProductRepository;
import vn.edu.iuh.fit.donchung.backend.services.ProductBeanRemote;

import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class ProductBean implements ProductBeanRemote {

    @Inject
    private ProductRepository productRepository;
    @Inject
    private ProductMapper productMapper;
    @Override
    public List<ProductDto> getAll() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(productMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public ProductDto getById(Integer id) {
        return productMapper.toDto(productRepository.findById(id).orElse(null));
    }

    @Override
    public ProductDto save(ProductDto productDto) {
        return productMapper.toDto(productRepository.save(productMapper.toEntity(productDto)));
    }
}
