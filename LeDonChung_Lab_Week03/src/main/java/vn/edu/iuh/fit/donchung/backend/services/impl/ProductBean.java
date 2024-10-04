package vn.edu.iuh.fit.donchung.backend.services.impl;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import vn.edu.iuh.fit.donchung.backend.dtos.ProductDto;
import vn.edu.iuh.fit.donchung.backend.dtos.ProductPriceDto;
import vn.edu.iuh.fit.donchung.backend.entities.Product;
import vn.edu.iuh.fit.donchung.backend.mapper.ProductMapper;
import vn.edu.iuh.fit.donchung.backend.entities.ProductPrice;
import vn.edu.iuh.fit.donchung.backend.mapper.ProductPriceMapper;
import vn.edu.iuh.fit.donchung.backend.repositories.ProductPriceRepository;
import vn.edu.iuh.fit.donchung.backend.repositories.ProductRepository;
import vn.edu.iuh.fit.donchung.backend.services.ProductBeanRemote;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Stateless
public class ProductBean implements ProductBeanRemote {

    @Inject
    private ProductRepository productRepository;
    @Inject
    private ProductMapper productMapper;
    @Inject
    private ProductPriceMapper productPriceMapper;
    @Inject
    private ProductPriceRepository productPriceRepository;

    @Override
    public List<ProductDto> getAll() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(product -> {
            ProductDto productDto = productMapper.toDto(product);
            ProductPrice pc = productPriceRepository.findActiveByProductId(product.getId());
            if (pc != null) {
                productDto.setPrice(pc.getValue());
            } else {
                productDto.setPrice(null);
            }
            return productDto;
        }).collect(Collectors.toList());
    }

    @Override
    public ProductDto getById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        ProductDto productDto = null;
        if (product != null) {
            productDto = productMapper.toDto(product);
            ProductPrice pc = productPriceRepository.findActiveByProductId(product.getId());
            if (pc != null) {
                productDto.setPrice(pc.getValue());
            } else {
                productDto.setPrice(null);
            }
        }
        return productDto;
    }

    @Override
    public ProductDto save(ProductDto productDto) throws Exception {
        Product product = productMapper.toEntity(productDto);
        if (productDto.getId() != null) {
            Product oldProduct = productRepository.findById(productDto.getId()).orElse(null);
            if (oldProduct != null) {
                product = productMapper.partialUpdate(productDto, oldProduct);
            }
        }
        product = productRepository.save(product);
        if (productDto.getPrice() != null) {
            ProductPriceDto productPriceDto = new ProductPriceDto();
            productPriceDto.setValue(productDto.getPrice());
            addPrice(product.getId(), productPriceDto);
        }
        return getById(product.getId());
    }

    @Override
    public ProductDto addPrice(Long productId, ProductPriceDto productPriceDto) throws Exception {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {

            ProductPrice pc = productPriceMapper.toEntity(productPriceDto);
            pc.setProduct(product.get());
            pc.getId().setProductId(productId);
            if (pc.getId().getPriceDateTime() == null) {
                pc.getId().setPriceDateTime(LocalDateTime.now());
            }
            pc.setStatus(1);
            pc.setApplyDate(Timestamp.valueOf(LocalDateTime.now()));

            if (product.get().getProductPrices() != null) {
                product.get().getProductPrices().forEach(productPrice -> {
                            productPrice.setStatus(0);
                        }
                );
                product.get().getProductPrices().add(pc);
            } else {
                product.get().setProductPrices(List.of(pc));
            }
            productRepository.save(product.get());
            return getById(productId);
        } else {
            throw new Exception("Product with id " + productPriceDto.getProductId() + " not found.");
        }
    }
}
