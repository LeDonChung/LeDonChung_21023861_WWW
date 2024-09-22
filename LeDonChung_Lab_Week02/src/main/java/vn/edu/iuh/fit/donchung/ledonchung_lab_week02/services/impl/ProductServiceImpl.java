package vn.edu.iuh.fit.donchung.ledonchung_lab_week02.services.impl;

import jakarta.inject.Inject;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week02.dtos.ProductDto;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week02.enums.ProductStatus;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week02.mapper.ProductMapper;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week02.models.Product;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week02.models.ProductPrice;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week02.models.ProductPriceId;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week02.repositories.ProductImageRepository;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week02.repositories.ProductPriceRepository;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week02.repositories.ProductRepository;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week02.services.ProductService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
public class ProductServiceImpl implements ProductService {
    @Inject
    private ProductRepository productRepository;
    @Inject
    private ProductImageRepository productImageRepository;
    @Inject
    private ProductPriceRepository productPriceRepository;
    @Inject
    private ProductMapper productMapper;
    @Override
    public List<ProductDto> getAll() {
        return productRepository.findAll().stream().map(productMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public ProductDto getById(Long id) {
        return productRepository.findById(id).map(productMapper::toDto).orElse(null);
    }

    @Override
    public ProductDto save(ProductDto product) {
        Product entity = productMapper.toEntity(product);
        if (entity.getId() == null) {
            entity.setStatus(ProductStatus.ACTIVE);
        } else {
            Product oldProduct = productRepository.findById(product.getId()).orElse(null);
            if (oldProduct != null) {
                entity = productMapper.partialUpdate(product, oldProduct);
            }
        }
        entity = productRepository.save(entity);
        return productMapper.toDto(entity);
    }

    @Override
    public boolean updateStatus(Long id, ProductStatus status) {
        return productRepository.updateStatus(id, status);
    }

    @Override
    public ProductDto savePrice(ProductPrice productPrice) {
        ProductPriceId productPriceId = productPrice.getId();
        Product product = productRepository.findById(productPriceId.getProductId()).orElse(null);
        if(product != null) {
            productPriceId.setPriceDateTime(LocalDateTime.now());
            productPrice.setProduct(product);

            productPriceRepository.save(productPrice);

            return productRepository.findById(product.getId()).map(productMapper::toDto).orElse(null);
        }
        return null;
    }
}
