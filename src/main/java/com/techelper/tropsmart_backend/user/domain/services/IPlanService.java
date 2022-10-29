package com.techelper.tropsmart_backend.user.domain.services;

import com.techelper.tropsmart_backend.configuration.domain.services.ICrudService;
import com.techelper.tropsmart_backend.user.domain.models.Plan;
import com.techelper.tropsmart_backend.user.domain.services.comunications.PlanResponse;
import com.techelper.tropsmart_backend.user.domain.services.inputs.PlanInput;

public interface IPlanService extends ICrudService<Plan> {
    PlanResponse findPlansByPrice(double priceValue);
    PlanResponse findPlansHigherThan(double priceValue);
    PlanResponse findPlansLessThan(double priceValue);
    PlanResponse findAllPlans();
    PlanResponse registerPlan(PlanInput planInput);
    PlanResponse findPlanById(int planId);
    PlanResponse deletePlanById(int planId);
}
