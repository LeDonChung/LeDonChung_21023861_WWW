package vn.edu.iuh.fit.donchung.backend.services.impl;

import jakarta.inject.Inject;
import vn.edu.iuh.fit.donchung.backend.dtos.ProductDto;
import vn.edu.iuh.fit.donchung.backend.enums.ProductStatus;
import vn.edu.iuh.fit.donchung.backend.mapper.ProductMapper;
import vn.edu.iuh.fit.donchung.backend.models.Product;
import vn.edu.iuh.fit.donchung.backend.models.ProductPrice;
import vn.edu.iuh.fit.donchung.backend.models.ProductPriceId;
import vn.edu.iuh.fit.donchung.backend.repositories.ProductImageRepository;
import vn.edu.iuh.fit.donchung.backend.repositories.ProductPriceRepository;
import vn.edu.iuh.fit.donchung.backend.repositories.ProductRepository;
import vn.edu.iuh.fit.donchung.backend.services.ProductService;

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

            return productMapper.toDto(product);
        }
        return null;
    }

    @Override
    public List<ProductDto> getProductsByStatusAndPriceLatest(ProductStatus status) {
        List<Product> products = productRepository.findByStatus(status);

        return products.stream().peek(product -> {
            ProductPrice productPrice = productPriceRepository.findLastByProductId(product.getId());
            if (productPrice != null) {
                product.setProductPrices(List.of(productPrice));
            }
        }).map(productMapper::toDto).collect(Collectors.toList());
    }
}
