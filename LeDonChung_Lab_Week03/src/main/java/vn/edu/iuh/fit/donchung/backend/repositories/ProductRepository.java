package vn.edu.iuh.fit.donchung.backend.repositories;

import jakarta.ejb.Local;
import vn.edu.iuh.fit.donchung.backend.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> findAll();
    Optional<Product> findById(Integer id);
    Product save(Product product);
}
