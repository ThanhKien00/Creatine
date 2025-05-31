package io.creatine.account.api.mapper;

import io.creatine.account.api.response.AccountResponse;
import io.creatine.account.domain.Account;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING)
public interface AccountMapper {

    AccountResponse toResponse(Account account);

}
