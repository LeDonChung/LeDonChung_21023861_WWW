package vn.edu.fit.student.donchung.backend.specifications;

import org.springframework.data.jpa.domain.Specification;
import vn.edu.fit.student.donchung.backend.entities.Job;

public class JobSpecification {
    public static Specification<Job> hasFilter(String filter) {
        return (root, query, criteriaBuilder) -> {
            if (filter == null || filter.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.or(
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("jobName")), "%" + filter.toLowerCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("company").get("compName")), "%" + filter.toLowerCase() + "%")
            );
        };
    }

    public static Specification<Job> hasAddress(String address) {
        return (root, query, criteriaBuilder) -> {
            if (address == null || address.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("company").get("address").get("city")), "%" + address.toLowerCase() + "%");
        };
    }
}
