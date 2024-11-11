package vn.edu.iuh.fit.donchung.backend.dtos;

import lombok.*;

import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PageDto<T>{
    private Collection<T> values;
    private int page;
    private int size;
    private int totalPages;
}
