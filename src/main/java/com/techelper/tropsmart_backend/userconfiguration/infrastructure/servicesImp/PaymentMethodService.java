package com.techelper.tropsmart_backend.userconfiguration.infrastructure.servicesImp;

import com.techelper.tropsmart_backend.configuration.application.exception.ResourceNotFoundException;
import com.techelper.tropsmart_backend.user.domain.models.User;
import com.techelper.tropsmart_backend.user.domain.repositories.IUserRepository;
import com.techelper.tropsmart_backend.userconfiguration.domain.models.PaymentMethod;
import com.techelper.tropsmart_backend.userconfiguration.domain.repositories.IPaymentMethodRepository;
import com.techelper.tropsmart_backend.userconfiguration.domain.services.IPaymentMethodService;
import com.techelper.tropsmart_backend.userconfiguration.domain.services.comunications.PaymentMethodResponse;
import com.techelper.tropsmart_backend.userconfiguration.domain.services.inputs.PaymentMethodInput;
import com.techelper.tropsmart_backend.userconfiguration.domain.services.outputs.PaymentMethodOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentMethodService implements IPaymentMethodService {

    @Autowired
    IPaymentMethodRepository paymentMethodRepository;
    @Autowired
    IUserRepository userRepository;

    @Override
    public PaymentMethodResponse findAllPaymentMethod() {
        try
        {
            List<PaymentMethod> paymentMethodList = paymentMethodRepository.findAll();
            List<PaymentMethodOutput> paymentMethodOutputList = new ArrayList<>();
            for (PaymentMethod p:paymentMethodList) {
                paymentMethodOutputList.add(new PaymentMethodOutput(p.getBankName(),
                        p.getSwiftCode(),p.getAccountNumber()));
            }
            return new PaymentMethodResponse(paymentMethodOutputList);
        }
        catch (Exception e)
        {
            return new PaymentMethodResponse("An error ocurred while getting paymentMethod: "+e.getMessage());
        }
    }

    @Override
    public PaymentMethodResponse findPaymentMethodById(int paymentMethodId) {
        try
        {
            PaymentMethod getPaymentMethod = paymentMethodRepository.findById(paymentMethodId)
                    .orElseThrow(()->new ResourceNotFoundException("paymentMethod","id",paymentMethodId));

            return new PaymentMethodResponse(new PaymentMethodOutput(getPaymentMethod.getBankName(),
                    getPaymentMethod.getSwiftCode(),getPaymentMethod.getAccountNumber()));

        }
        catch (Exception e)
        {
            return new PaymentMethodResponse("An error ocurred while getting paymentMethod: "+e.getMessage());
        }
    }

    @Override
    public PaymentMethodResponse findPaymentMethodByUserId(int userId) {
        try
        {
            List<PaymentMethod> paymentMethodList = paymentMethodRepository.findPaymentMethodByUserId(userId);
            List<PaymentMethodOutput> paymentMethodOutputList = new ArrayList<>();
            for (PaymentMethod p:paymentMethodList) {
                paymentMethodOutputList.add(new PaymentMethodOutput(p.getBankName(),
                        p.getSwiftCode(),p.getAccountNumber()));
            }

            return new PaymentMethodResponse(paymentMethodOutputList);
        }
        catch (Exception e)
        {
            return new PaymentMethodResponse("An error ocurred while getting paymentMethod: "+e.getMessage());
        }
    }

    @Override
    public PaymentMethodResponse addPaymentMethodByUserId(int userId, PaymentMethodInput paymentMethodInput) {
        try{
            User getUser = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("id","user",userId));
            PaymentMethod newPaymentMethod = new PaymentMethod();
            newPaymentMethod.setAccountNumber(paymentMethodInput.getAccountNumber());
            newPaymentMethod.setBankName(paymentMethodInput.getBankName());
            newPaymentMethod.setBillingAdress(paymentMethodInput.getBillingAdress());
            newPaymentMethod.setSwiftCode(paymentMethodInput.getSwiftCode());
            newPaymentMethod.setConfiguration(getUser.getConfiguration());

            newPaymentMethod = paymentMethodRepository.save(newPaymentMethod);

            return new PaymentMethodResponse(toPaymentMethodOutput(newPaymentMethod));
        }
        catch (Exception e)
        {
            return new PaymentMethodResponse("An error ocurred while getting paymentMethod: "+e.getMessage());
        }
    }

    @Override
    public PaymentMethod save(PaymentMethod paymentMethod) throws Exception {
        return paymentMethodRepository.save(paymentMethod);
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        paymentMethodRepository.deleteById(id);
    }

    @Override
    public Optional<PaymentMethod> findById(Integer id) throws Exception {
        return paymentMethodRepository.findById(id);
    }

    @Override
    public List<PaymentMethod> findAll() throws Exception {
        return paymentMethodRepository.findAll();
    }

    public PaymentMethodOutput toPaymentMethodOutput(PaymentMethod paymentMethod)
    {
        return new PaymentMethodOutput(paymentMethod.getBankName(),
                paymentMethod.getSwiftCode(),paymentMethod.getAccountNumber());
    }
}
