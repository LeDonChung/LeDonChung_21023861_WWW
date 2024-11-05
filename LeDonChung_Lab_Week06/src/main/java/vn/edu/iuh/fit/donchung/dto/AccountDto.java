package vn.edu.iuh.fit.donchung.dto;

import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link vn.edu.iuh.fit.donchung.entities.Account}
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class AccountDto implements Serializable {
    Long id;
    String accountName;
    String accountNumber;
    double balance;
}