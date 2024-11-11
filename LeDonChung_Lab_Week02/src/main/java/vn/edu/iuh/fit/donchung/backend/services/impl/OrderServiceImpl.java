package vn.edu.iuh.fit.donchung.backend.services.impl;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import vn.edu.iuh.fit.donchung.backend.dtos.CustomerDto;
import vn.edu.iuh.fit.donchung.backend.mapper.CustomerMapper;
import vn.edu.iuh.fit.donchung.backend.models.Customer;
import vn.edu.iuh.fit.donchung.backend.models.Order;
import vn.edu.iuh.fit.donchung.backend.models.OrderDetail;
import vn.edu.iuh.fit.donchung.backend.models.Product;
import vn.edu.iuh.fit.donchung.backend.repositories.*;
import vn.edu.iuh.fit.donchung.backend.dtos.OrderDto;
import vn.edu.iuh.fit.donchung.backend.mapper.OrderDetailMapper;
import vn.edu.iuh.fit.donchung.backend.mapper.OrderMapper;
import vn.edu.iuh.fit.donchung.backend.services.OrderService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {
    @Inject
    private OrderRepository orderRepository;
    @Inject
    private OrderMapper orderMapper;
    @Inject
    private ProductPriceRepository productPriceRepository;
    @Inject
    private OrderDetailMapper orderDetailMapper;
    @Inject
    private OrderDetailRepository orderDetailRepository;
    @Inject
    private ProductRepository productRepository;
    @Inject
    private CustomerRepository customerRepository;
    @Inject
    private CustomerMapper customerMapper;

    @Override
    public List<OrderDto> findAll() {
        return orderRepository
                .findAll()
                .stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto findById(Long id) {
        return orderMapper.toDto(orderRepository.findById(id));
    }

    @Transactional
    @Override
    public OrderDto save(OrderDto order) {
        Order entity = orderMapper.toEntity(order);

        // Kiểm tra xem order đã tồn tại chưa
        if(order.getId() != null) {
            Order oldOrder = orderRepository.findById(order.getId());
            if(oldOrder != null) {
                entity = orderMapper.partialUpdate(order, oldOrder);
            }
        }

        // Thiết lập ngày đặt hàng cho đơn hàng là ngày hiện tại
        order.setOrderDate(LocalDateTime.now());
        // Thiết lập danh sách chi tiết đơn hàng rỗng để tránh lỗi khi thêm mới
        entity.setOrderDetails(new ArrayList<>());

        // Lưu khách hàng nếu là mới
        if(order.getCustomer().getId() == null) {
            Customer customer = customerMapper.toEntity(order.getCustomer());
            customer = customerRepository.save(customer);
            entity.setCustomer(customer);
        }
        // Lưu đơn hàng
        entity = orderRepository.save(entity);

        // Lưu chi tiết đơn hàng
        Order finalEntity = entity;
        List<OrderDetail> orderDetails = order.getOrderDetails().stream().map(orderDetail -> {

            OrderDetail orderDetailNew = orderDetailMapper.toEntity(orderDetail);

            Optional<Product> product = productRepository.findById(orderDetail.getProductId());
            if(product.isPresent()) {
                orderDetailNew.setOrder(finalEntity);
                orderDetailNew.setPrice(productPriceRepository.findLastByProductId(orderDetail.getProductId()).getPrice());
                orderDetailNew.setProduct(product.get());
            }

            return orderDetailNew;
        }).collect(Collectors.toList());

        // Lưu chi tiết đơn hàng
        orderDetails = orderDetailRepository.saveAll(orderDetails);

        // Cập nhật lại danh sách chi tiết đơn hàng cho đơn hàng
        entity.setOrderDetails(orderDetails);

        return orderMapper.toDto(entity);
    }

    @Override
    public List<OrderDto> findByEmployeeId(Long employeeId) {
        return orderRepository
                .findByEmployeeId(employeeId)
                .stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }
}
