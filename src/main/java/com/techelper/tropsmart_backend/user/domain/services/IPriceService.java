package com.techelper.tropsmart_backend.user.domain.services;

import com.techelper.tropsmart_backend.configuration.domain.services.ICrudService;
import com.techelper.tropsmart_backend.user.domain.models.Price;
import com.techelper.tropsmart_backend.user.domain.services.comunications.PriceResponse;

public interface IPriceService extends ICrudService<Price> {
    PriceResponse findAllPrices();
    PriceResponse findPriceById(int priceId);
    PriceResponse findPricesByPriceType(int priceType);
}
