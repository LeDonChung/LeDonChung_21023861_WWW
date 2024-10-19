package vn.edu.iuh.fit.donchung.dto;

import lombok.*;

import java.util.Collection;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class PageDTO<T>{
    private int pageNum;
    private int pageSize;
    private Collection<T> content;
}
