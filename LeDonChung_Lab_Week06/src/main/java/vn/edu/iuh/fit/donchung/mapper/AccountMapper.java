package vn.edu.iuh.fit.donchung.mapper;

import org.mapstruct.*;
import vn.edu.iuh.fit.donchung.dto.AccountDto;
import vn.edu.iuh.fit.donchung.entities.Account;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AccountMapper {
    Account toEntity(AccountDto accountDto);

    AccountDto toDto(Account account);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Account partialUpdate(AccountDto accountDto, @MappingTarget Account account);
}