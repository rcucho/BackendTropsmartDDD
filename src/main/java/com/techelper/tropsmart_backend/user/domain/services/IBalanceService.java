package com.techelper.tropsmart_backend.user.domain.services;

import com.techelper.tropsmart_backend.configuration.domain.services.ICrudService;
import com.techelper.tropsmart_backend.user.domain.models.Balance;
import com.techelper.tropsmart_backend.user.domain.services.comunications.BalanceResponse;

public interface IBalanceService extends ICrudService<Balance> {
    BalanceResponse findBalanceById(int userId);
    BalanceResponse findAllBalances();
}
