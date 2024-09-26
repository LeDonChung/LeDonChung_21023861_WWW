package vn.edu.iuh.fit.donchung.backend.services;

import vn.edu.iuh.fit.donchung.backend.dtos.ProductDto;
import vn.edu.iuh.fit.donchung.backend.enums.ProductStatus;
import vn.edu.iuh.fit.donchung.backend.models.ProductPrice;

import java.util.List;

/**
 * ProductService
 */
public interface ProductService {
    /**
     * Lấy tất cả sản phẩm
     * @return danh sách sản phẩm
     */
    List<ProductDto> getAll();

    /**
     * Lấy sản phẩm theo id
     * @param id là id của sản phẩm
     * @return sản phẩm tìm thấy, null nếu không tìm thấy
     */
    ProductDto getById(Long id);

    /**
     * Lưu sản phẩm
     * @param product sản phẩm cần lưu
     * @return sản phẩm đã lưu
     */

    ProductDto save(ProductDto product);

    /**
     * Cập nhật trạng thái sản phẩm
     * @param id là mã của sản phẩm
     * @param status là trạng thái của sản phẩm
     * @return true nếu cập nhật thành công, ngược lại false
     */
    boolean updateStatus(Long id, ProductStatus status);

    /**
     * Lưu giá sản phẩm
     * @param productPrice giá sản phẩm cần lưu
     * @return giá sản phẩm đã lưu
     */
    ProductDto savePrice(ProductPrice productPrice);

    /**
     * Lấy sản phẩm theo trạng thái hoạt động
     * @param status là trạng thái của sản phẩm: ACTIVE, INACTIVE, TERMINATED
     * @return danh sách sản phẩm đang hoạt động và có giá mới nhất (nếu có)
     */
    List<ProductDto> getProductsByStatusAndPriceLatest(ProductStatus status);



}
