package com.techelper.tropsmart_backend.user.infrastructure.servicesImp;

import com.techelper.tropsmart_backend.configuration.application.exception.ResourceNotFoundException;
import com.techelper.tropsmart_backend.user.domain.models.Plan;
import com.techelper.tropsmart_backend.user.domain.models.Subscription;
import com.techelper.tropsmart_backend.user.domain.models.User;
import com.techelper.tropsmart_backend.user.domain.repositories.IPlanRepository;
import com.techelper.tropsmart_backend.user.domain.repositories.ISubscriptionRepository;
import com.techelper.tropsmart_backend.user.domain.repositories.IUserRepository;
import com.techelper.tropsmart_backend.user.domain.services.ISubscriptionService;
import com.techelper.tropsmart_backend.user.domain.services.comunications.SubscriptionResponse;
import com.techelper.tropsmart_backend.user.domain.services.outputs.SubscriptionOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionService implements ISubscriptionService {
    @Autowired
    private ISubscriptionRepository subscriptionRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IPlanRepository planRepository;

    @Override
    public SubscriptionResponse findSubscriptionById(int subscriptionId) {
        try{
            Subscription getSubscription = subscriptionRepository.findById(subscriptionId).get();
            return new SubscriptionResponse(toSubscriptionOutput(getSubscription));
        }
        catch (Exception e)
        {
            return new SubscriptionResponse("An error ocurred while getting the subscription : "+e.getMessage());
        }

    }

    @Override
    public SubscriptionResponse subscribe(int userId, int planId) {
        try
        {
            Plan getPlan = planRepository.findById(planId).get();
            User getUser = userRepository.findById(userId).get();

            Subscription newSubscription = new Subscription();
            newSubscription.setPlan(getPlan);
            newSubscription.setUser(getUser);
            newSubscription.setSubscriptionState("Actived");
            newSubscription.setStartTime(Calendar.getInstance().getTime());
            newSubscription.setFinishTime(Calendar.getInstance().getTime());

            List<Subscription> subscriptionList = subscriptionRepository.getSubscriptionsByUserId(userId);

            for (Subscription s: subscriptionList) {
                s.setSubscriptionState("Disabled");
            }

            subscriptionRepository.saveAll(subscriptionList);
            newSubscription = subscriptionRepository.save(newSubscription);

            return new SubscriptionResponse(toSubscriptionOutput(newSubscription));

        }
        catch (Exception e)
        {
            return new SubscriptionResponse("An error ocurred while registering the subscription : "+e.getMessage());
        }

    }

    @Override
    public SubscriptionResponse findSubscriptionsByUserId(int userId) {
        try
        {
            User getUser = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","id",userId));
            if(getUser.getPerson().getPersonType() == 1)
                return new SubscriptionResponse("Subscriptions are only available to drivers");
            List<Subscription> subscriptionList = subscriptionRepository.getSubscriptionsByUserId(userId);
            System.out.print("subscription list : "+ subscriptionList.size());
            List<SubscriptionOutput> subscriptionOutputList = new ArrayList<>();
            for (Subscription s:subscriptionList) {
                subscriptionOutputList.add(toSubscriptionOutput(s));
            }
            return new SubscriptionResponse(subscriptionOutputList);
        }
        catch (Exception e)
        {
            return new SubscriptionResponse("An error ocurred while getting the subscription list : "+e.getMessage());

        }

    }

    @Override
    public SubscriptionResponse findAllSubscriptions() {
        try
        {
            List<Subscription> subscriptionList = subscriptionRepository.findAll();
            List<SubscriptionOutput> subscriptionOutputList = new ArrayList<>();
            for (Subscription s:subscriptionList) {
                subscriptionOutputList.add(toSubscriptionOutput(s));
            }
            return new SubscriptionResponse(subscriptionOutputList);
        }
        catch (Exception e)
        {
            return new SubscriptionResponse("An error ocurred while getting the subscription list : "+e.getMessage());
        }
    }

    @Override
    public SubscriptionResponse cancelSubscription(int subscriptionId) {
        try
        {
            Subscription getSubscription = subscriptionRepository.findById(subscriptionId)
                    .orElseThrow(()->new ResourceNotFoundException("subscription","id",subscriptionId));
            getSubscription.setSubscriptionState("Canceled");
            getSubscription = subscriptionRepository.save(getSubscription);

            return new SubscriptionResponse(toSubscriptionOutput(getSubscription));
        }
        catch (Exception e)
        {
            return new SubscriptionResponse("An error ocurred while getting the subscription : "+e.getMessage());
        }
    }

    @Override
    public SubscriptionResponse enableSubscriptionById(int subscriptionId) {
        try
        {
            Subscription getSubscription = subscriptionRepository.findById(subscriptionId)
                    .orElseThrow(()->new ResourceNotFoundException("Id","subscription", subscriptionId));

            List<Subscription> subscriptionList = subscriptionRepository.getSubscriptionsByUserId(getSubscription.getUser().getId());
            for (Subscription s: subscriptionList) {
                if(s.getId()!=subscriptionId)
                    s.setSubscriptionState("Disabled");
                else
                    s.setSubscriptionState("Actived");
                s = subscriptionRepository.save(s);
            }

            return new SubscriptionResponse(toSubscriptionOutput(getSubscription));

        }
        catch (Exception e)
        {
            return new SubscriptionResponse("An error ocurred while getting the subscription : "+e.getMessage());
        }
    }

    @Override
    public SubscriptionResponse deleteSubscriptionBySubscriptionId(int subscriptionId) {
        try{
            Subscription getSubscription = subscriptionRepository.findById(subscriptionId)
                    .orElseThrow(()-> new ResourceNotFoundException("Id","subscription",subscriptionId));
            subscriptionRepository.deleteById(subscriptionId);
            return new SubscriptionResponse(toSubscriptionOutput(getSubscription));
        }
        catch (Exception e)
        {
            return new SubscriptionResponse("An error ocurred while getting the subscription : "+e.getMessage());
        }
    }

    @Override
    public Subscription save(Subscription subscription) throws Exception {
        return subscriptionRepository.save(subscription);
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        subscriptionRepository.deleteById(id);
    }

    @Override
    public Optional<Subscription> findById(Integer id) throws Exception {
        return subscriptionRepository.findById(id);
    }

    @Override
    public List<Subscription> findAll() throws Exception {
        return subscriptionRepository.findAll();
    }

    public SubscriptionOutput toSubscriptionOutput (Subscription subscription) {
        SubscriptionOutput newSubscriptionOutput = new SubscriptionOutput();
        newSubscriptionOutput.setFirstName(subscription.getUser().getPerson().getFirstName());
        newSubscriptionOutput.setLastName(subscription.getUser().getPerson().getLastName());
        newSubscriptionOutput.setEmail(subscription.getUser().getEmail());
        newSubscriptionOutput.setPlan(subscription.getPlan().getName());
        newSubscriptionOutput.setId(subscription.getId());
        newSubscriptionOutput.setState(subscription.getSubscriptionState());
        newSubscriptionOutput.setPrice(subscription.getPlan().getPrice().getTotalPrice());
        return newSubscriptionOutput;
    }
}

