package vn.edu.iuh.fit.donchung.ledonchung_lab_week02.repositories;

import vn.edu.iuh.fit.donchung.ledonchung_lab_week02.enums.ProductStatus;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week02.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    /**
     * Tìm tất cả sản phẩm
     * @return danh sách sản phẩm
     */
    List<Product> findAll();
    /**
     * Tìm sản phẩm theo id
     * @param id id của sản phẩm
     * @return sản phẩm tìm thấy, null nếu không tìm thấy
     */
    Optional<Product> findById(Long id);

    /**
     * Lưu sản phẩm
     * @param product sản phẩm cần lưu
     * @return sản phẩm đã lưu
     */
    Product save(Product product);

    /**
     * Cập nhật trạng thái sản phẩm
     * @param id là mã của sản phẩm
     * @param status là trạng thái của sản phẩm
     * @return true nếu cập nhật thành công, ngược lại false
     */
    boolean updateStatus(Long id, ProductStatus status);
}
