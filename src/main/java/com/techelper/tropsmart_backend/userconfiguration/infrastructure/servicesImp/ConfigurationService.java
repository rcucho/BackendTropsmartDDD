package com.techelper.tropsmart_backend.userconfiguration.infrastructure.servicesImp;

import com.techelper.tropsmart_backend.user.domain.models.Person;
import com.techelper.tropsmart_backend.user.domain.models.User;
import com.techelper.tropsmart_backend.user.domain.repositories.IPersonRepository;
import com.techelper.tropsmart_backend.user.domain.repositories.IUserRepository;
import com.techelper.tropsmart_backend.userconfiguration.domain.models.Configuration;
import com.techelper.tropsmart_backend.userconfiguration.domain.models.PaymentMethod;
import com.techelper.tropsmart_backend.userconfiguration.domain.repositories.IConfigurationRepository;
import com.techelper.tropsmart_backend.userconfiguration.domain.repositories.IPaymentMethodRepository;
import com.techelper.tropsmart_backend.userconfiguration.domain.services.IConfigurationService;
import com.techelper.tropsmart_backend.userconfiguration.domain.services.comunications.ConfigurationResponse;
import com.techelper.tropsmart_backend.userconfiguration.domain.services.inputs.ConfigurationInput;
import com.techelper.tropsmart_backend.userconfiguration.domain.services.inputs.PaymentMethodInput;
import com.techelper.tropsmart_backend.userconfiguration.domain.services.outputs.ConfigurationOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConfigurationService implements IConfigurationService {

    @Autowired
    IConfigurationRepository configurationRepository;

    @Autowired
    IUserRepository userRepository;

    @Autowired
    IPersonRepository personRepository;

    @Autowired
    IPaymentMethodRepository paymentMethodRepository;





    @Override
    public ConfigurationResponse addPaymentMethod(int userId, PaymentMethodInput paymentMethodInput) {
        try
        {
            User getUser = userRepository.findById(userId).get();
            PaymentMethod newPaymentMethod = new PaymentMethod();
            newPaymentMethod.setAccountNumber(paymentMethodInput.getAccountNumber());
            newPaymentMethod.setBankName(paymentMethodInput.getBankName());
            newPaymentMethod.setBillingAdress(paymentMethodInput.getBillingAdress());
            newPaymentMethod.setSwiftCode(paymentMethodInput.getSwiftCode());
            newPaymentMethod.setConfiguration(getUser.getConfiguration());

            newPaymentMethod = paymentMethodRepository.save(newPaymentMethod);

            ConfigurationOutput getConfigurationOutput = new ConfigurationOutput();
            getConfigurationOutput.setFirstName(getUser.getPerson().getFirstName());
            getConfigurationOutput.setLastName(getUser.getPerson().getLastName());
            getConfigurationOutput.setLanguage(getUser.getConfiguration().getLanguage().toString());
            getConfigurationOutput.setPaymentCurrency(getUser.getConfiguration().getPaymentCurrency().toString());
            getConfigurationOutput.setPhone(getUser.getPerson().getPhone());
            return new ConfigurationResponse(getConfigurationOutput);
        }
        catch (Exception e)
        {
            return new ConfigurationResponse("An error ocurred while getting a configuration");
        }
    }


    @Override
    public ConfigurationResponse findAllConfigurations() {
        try
        {
            List<Configuration> configurationList = configurationRepository.findAll();
            List<ConfigurationOutput> configurationOutputList = new ArrayList<>();
            for(Configuration c:configurationList)
            {
                ConfigurationOutput newConfigurationOutput = new ConfigurationOutput();
                newConfigurationOutput.setFirstName(c.getUser().getPerson().getFirstName());
                newConfigurationOutput.setLastName(c.getUser().getPerson().getLastName());
                newConfigurationOutput.setLanguage(c.getLanguage().toString());
                newConfigurationOutput.setPhone(c.getUser().getPerson().getPhone());
                newConfigurationOutput.setPaymentCurrency(c.getPaymentCurrency().toString());
                configurationOutputList.add(newConfigurationOutput);
            }
            return new ConfigurationResponse(configurationOutputList);
        }
        catch (Exception e)
        {
            return new ConfigurationResponse("An error ocurred while getting a configuration list: "+e.getMessage());
        }
    }


    @Override
    public ConfigurationResponse findConfigurationByUserId(int userId) {
        try
        {
            User getUser = userRepository.findById(userId).get();
            ConfigurationOutput newConfigurationOutput = new ConfigurationOutput();
            newConfigurationOutput.setFirstName(getUser.getPerson().getFirstName());
            newConfigurationOutput.setLastName(getUser.getPerson().getLastName());
            newConfigurationOutput.setPaymentCurrency(getUser.getConfiguration().getPaymentCurrency().toString());
            newConfigurationOutput.setLanguage(getUser.getConfiguration().getLanguage().toString());
            newConfigurationOutput.setPhone(getUser.getPerson().getPhone());
            return new ConfigurationResponse(newConfigurationOutput);
        }
        catch (Exception e)
        {
            return new ConfigurationResponse("An error ocurred while getting a configuration");
        }
    }

    @Override
    public ConfigurationResponse updateConfiguration(int userId, ConfigurationInput configurationInput) {
        try
        {
            User getUser = userRepository.findById(userId).get();
            Person getPerson = getUser.getPerson();
            Configuration getConfiguration = getUser.getConfiguration();
            getConfiguration.setLanguage(configurationInput.getLanguage());
            getConfiguration.setPaymentCurrency(configurationInput.getPaymentCurrency());
            getPerson.setFirstName(configurationInput.getFirstName());
            getPerson.setLastName(configurationInput.getLastName());
            getPerson.setPhone(configurationInput.getPhone());
            getPerson = personRepository.save(getPerson);
            getConfiguration = configurationRepository.save(getConfiguration);

            ConfigurationOutput newConfigurationOutput = new ConfigurationOutput();
            newConfigurationOutput.setPaymentCurrency(getConfiguration.getPaymentCurrency());
            newConfigurationOutput.setLanguage(getConfiguration.getLanguage());
            newConfigurationOutput.setPhone(getConfiguration.getUser().getPerson().getPhone());
            newConfigurationOutput.setFirstName(getConfiguration.getUser().getPerson().getFirstName());
            newConfigurationOutput.setLastName(getConfiguration.getUser().getPerson().getLastName());

            return new ConfigurationResponse(newConfigurationOutput);
        }
        catch (Exception e)
        {
            return new ConfigurationResponse("An error ocurred while getting a configuration");
        }
    }

    @Override
    public ConfigurationResponse generateConfiguration(int userId) {
        try{
            User getUser = userRepository.findById(userId).get();
            if(getUser.getConfiguration()== null)
            {
                Configuration newConfiguration = new Configuration();
                newConfiguration.setLanguage("Spanish");
                newConfiguration.setPaymentCurrency("Soles");

                newConfiguration = configurationRepository.save(newConfiguration);

                getUser.setConfiguration(newConfiguration);
                getUser = userRepository.save(getUser);

                ConfigurationOutput newConfigurationOutput = new ConfigurationOutput();
                newConfigurationOutput.setPaymentCurrency(newConfiguration.getPaymentCurrency());
                newConfigurationOutput.setLanguage(newConfiguration.getLanguage());
                newConfigurationOutput.setPhone(newConfiguration.getUser().getPerson().getPhone());
                newConfigurationOutput.setFirstName(newConfiguration.getUser().getPerson().getFirstName());
                newConfigurationOutput.setLastName(newConfiguration.getUser().getPerson().getLastName());
                return new ConfigurationResponse(newConfigurationOutput);

            }
            else{
                Configuration getConfiguration = getUser.getConfiguration();
                ConfigurationOutput newConfigurationOutput = new ConfigurationOutput();
                newConfigurationOutput.setPaymentCurrency(getConfiguration.getPaymentCurrency());
                newConfigurationOutput.setLanguage(getConfiguration.getLanguage());
                newConfigurationOutput.setPhone(getConfiguration.getUser().getPerson().getPhone());
                newConfigurationOutput.setFirstName(getConfiguration.getUser().getPerson().getFirstName());
                newConfigurationOutput.setLastName(getConfiguration.getUser().getPerson().getLastName());

                return new ConfigurationResponse(newConfigurationOutput);
            }
        }catch (Exception e)
        {
            return new ConfigurationResponse("An error ocurred while getting a configuration");
        }
    }

    @Override
    public Configuration save(Configuration configuration) throws Exception {
        return configurationRepository.save(configuration);
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        configurationRepository.deleteById(id);
    }

    @Override
    public Optional<Configuration> findById(Integer id) throws Exception {
        return configurationRepository.findById(id);
    }

    @Override
    public List<Configuration> findAll() throws Exception {
        return configurationRepository.findAll();
    }
}
