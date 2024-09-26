package vn.edu.iuh.fit.donchung.backend.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import vn.edu.iuh.fit.donchung.backend.models.OrderDetail;
import vn.edu.iuh.fit.donchung.backend.utils.AppUtils;
import vn.edu.iuh.fit.donchung.backend.repositories.OrderDetailRepository;

import java.util.ArrayList;
import java.util.List;

@Transactional
public class OrderDetailRepositoryImpl implements OrderDetailRepository {
    @PersistenceContext(unitName = AppUtils.PERSISTENCE_UNIT_NAME)
    private EntityManager em;
    @Override
    public List<OrderDetail> saveAll(List<OrderDetail> orderDetails) {
        List<OrderDetail> orderDetailsNew = new ArrayList<>();
        for (OrderDetail orderDetail : orderDetails) {
            if(orderDetail.getId() == null) {
                em.persist(orderDetail);
            } else {
                orderDetail = em.merge(orderDetail);
            }
            orderDetailsNew.add(orderDetail);
        }
        return orderDetailsNew;
    }
}
