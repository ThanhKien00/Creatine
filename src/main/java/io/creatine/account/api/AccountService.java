package io.creatine.account.api;

import io.creatine.account.api.response.AccountResponse;
import io.creatine.account.domain.command.UpdateAccountProfile;
import io.creatine.account.domain.query.QueryAccountProfile;

import java.util.List;

public interface AccountService {

    AccountResponse current();

    AccountResponse getProfile(QueryAccountProfile profile);

    List<AccountResponse> limit();

    AccountResponse updateProfile(UpdateAccountProfile command);

    boolean delete(String accountId);

}
