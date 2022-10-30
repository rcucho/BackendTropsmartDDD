package com.techelper.tropsmart_backend.userconfiguration.domain.services;

import com.techelper.tropsmart_backend.configuration.domain.services.ICrudService;
import com.techelper.tropsmart_backend.userconfiguration.domain.models.Configuration;
import com.techelper.tropsmart_backend.userconfiguration.domain.services.comunications.ConfigurationResponse;
import com.techelper.tropsmart_backend.userconfiguration.domain.services.inputs.ConfigurationInput;
import com.techelper.tropsmart_backend.userconfiguration.domain.services.inputs.PaymentMethodInput;

public interface IConfigurationService extends ICrudService<Configuration> {
    ConfigurationResponse findAllConfigurations();
    ConfigurationResponse findConfigurationByUserId(int userId);
    ConfigurationResponse addPaymentMethod(int userId, PaymentMethodInput paymentMethodInput);
    ConfigurationResponse updateConfiguration(int userId, ConfigurationInput configurationInput);
    ConfigurationResponse generateConfiguration(int userId);
}
