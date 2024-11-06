package vn.edu.fit.student.donchung.backend.dtos;

import lombok.*;

import java.util.Collection;

/**
 * DTO for pagination
 * @param <T> type of values
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Builder
public class PageDto <T>{
    // current page
    private int page;

    // number of items per page
    private int size;

    // list of values
    private Collection<T> values;

    // total number of items
    private int total;

    // total pages = total / size
    private int totalPages;

    // sort by field
    private String sortBy;

    // sort type ASC or DESC
    private String sortType = "ASC";
}
