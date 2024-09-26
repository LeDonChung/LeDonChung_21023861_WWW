package vn.edu.iuh.fit.donchung.backend.enums;

import lombok.Getter;

/**
 * ProductStatus
 * - TERMINATED(-1) là sản phẩm đã ngừng kinh doanh
 * - ACTIVE(1) là sản phẩm đang kinh doanh
 * - IN_ACTIVE(0) là sản phẩm không hoạt động
 */
@Getter
public enum ProductStatus {
    TERMINATED(-1),
    ACTIVE(1),
    IN_ACTIVE(0);

    private int value;

    ProductStatus(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}