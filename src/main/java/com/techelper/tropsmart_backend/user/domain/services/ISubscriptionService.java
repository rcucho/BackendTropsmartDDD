package com.techelper.tropsmart_backend.user.domain.services;

import com.techelper.tropsmart_backend.configuration.domain.services.ICrudService;
import com.techelper.tropsmart_backend.user.domain.models.Subscription;
import com.techelper.tropsmart_backend.user.domain.services.comunications.SubscriptionResponse;

public interface ISubscriptionService extends ICrudService<Subscription> {
    SubscriptionResponse findSubscriptionById(int subscriptionId);
    SubscriptionResponse subscribe(int userId, int planId);
    SubscriptionResponse findSubscriptionsByUserId(int userId);
    SubscriptionResponse findAllSubscriptions();
    SubscriptionResponse cancelSubscription(int subscriptionId);
    SubscriptionResponse enableSubscriptionById(int subscriptionId);
    SubscriptionResponse deleteSubscriptionBySubscriptionId(int subscriptionId);
}