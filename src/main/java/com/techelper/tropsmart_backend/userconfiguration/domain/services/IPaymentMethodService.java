package com.techelper.tropsmart_backend.userconfiguration.domain.services;

import com.techelper.tropsmart_backend.configuration.domain.services.ICrudService;
import com.techelper.tropsmart_backend.userconfiguration.domain.models.PaymentMethod;
import com.techelper.tropsmart_backend.userconfiguration.domain.services.comunications.PaymentMethodResponse;
import com.techelper.tropsmart_backend.userconfiguration.domain.services.inputs.PaymentMethodInput;

public interface IPaymentMethodService extends ICrudService<PaymentMethod> {
    PaymentMethodResponse findAllPaymentMethod();
    PaymentMethodResponse findPaymentMethodById(int paymentMethodId);
    PaymentMethodResponse findPaymentMethodByUserId(int userId);
    PaymentMethodResponse addPaymentMethodByUserId(int userId, PaymentMethodInput paymentMethodInput);
}
