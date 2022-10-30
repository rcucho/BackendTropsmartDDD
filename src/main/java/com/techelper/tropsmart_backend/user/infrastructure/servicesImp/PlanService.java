package com.techelper.tropsmart_backend.user.infrastructure.servicesImp;

import com.techelper.tropsmart_backend.user.domain.models.Plan;
import com.techelper.tropsmart_backend.user.domain.models.Price;
import com.techelper.tropsmart_backend.user.domain.repositories.IPlanRepository;
import com.techelper.tropsmart_backend.user.domain.repositories.IPriceRepository;
import com.techelper.tropsmart_backend.user.domain.services.IPlanService;
import com.techelper.tropsmart_backend.user.domain.services.comunications.PlanResponse;
import com.techelper.tropsmart_backend.user.domain.services.inputs.PlanInput;
import com.techelper.tropsmart_backend.user.domain.services.outputs.PlanOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlanService implements IPlanService {

    @Autowired
    private IPlanRepository planRepository;

    @Autowired
    private IPriceRepository priceRepository;


    @Override
    public PlanResponse findPlansByPrice(double priceValue) {
        try
        {
            List<Plan> plans = planRepository.findPlansByPriceValue(priceValue);
            List<PlanOutput> planOutputList = new ArrayList<>();
            for (Plan p: plans) {
                PlanOutput newPlanOutput = new PlanOutput();
                newPlanOutput.setId(p.getId());
                newPlanOutput.setDurationDays(p.getDuration());
                newPlanOutput.setPrice(p.getPrice().getTotalPrice());
                newPlanOutput.setTax(p.getPrice().getTax());
                newPlanOutput.setPlanName(p.getName());
                planOutputList.add(newPlanOutput);
            }
            return new PlanResponse(planOutputList);
        }
        catch (Exception e)
        {
            return new PlanResponse("An error ocurred while getting the plan list: "+e.getMessage());
        }

    }

    @Override
    public PlanResponse findPlansHigherThan(double priceValue) {
        try
        {
            List<Plan> plans = planRepository.findPlansHigherThanPriceValue(priceValue);
            List<PlanOutput> planOutputList = new ArrayList<>();
            for (Plan p: plans) {
                PlanOutput newPlanOutput = new PlanOutput();
                newPlanOutput.setId(p.getId());
                newPlanOutput.setDurationDays(p.getDuration());
                newPlanOutput.setPrice(p.getPrice().getTotalPrice());
                newPlanOutput.setTax(p.getPrice().getTax());
                newPlanOutput.setPlanName(p.getName());
                planOutputList.add(newPlanOutput);
            }
            return new PlanResponse(planOutputList);
        }
        catch (Exception e)
        {
            return new PlanResponse("An error ocurred while getting the plan list: "+e.getMessage());
        }

    }

    @Override
    public PlanResponse findPlansLessThan(double priceValue) {
        try
        {
            List<Plan> plans = planRepository.findPlansLessThanPriceValue(priceValue);
            List<PlanOutput> planOutputList = new ArrayList<>();
            for (Plan p: plans) {
                PlanOutput newPlanOutput = new PlanOutput();
                newPlanOutput.setId(p.getId());
                newPlanOutput.setDurationDays(p.getDuration());
                newPlanOutput.setPrice(p.getPrice().getTotalPrice());
                newPlanOutput.setTax(p.getPrice().getTax());
                newPlanOutput.setPlanName(p.getName());
                planOutputList.add(newPlanOutput);
            }
            return new PlanResponse(planOutputList);
        }
        catch (Exception e)
        {
            return new PlanResponse("An error ocurred while getting the plan list: "+e.getMessage());
        }

    }

    @Override
    public PlanResponse findAllPlans() {
        try
        {
            List<Plan> plans = planRepository.findAll();
            List<PlanOutput> planOutputList = new ArrayList<>();
            for (Plan p: plans) {
                PlanOutput newPlanOutput = new PlanOutput();
                newPlanOutput.setId(p.getId());
                newPlanOutput.setDurationDays(p.getDuration());
                newPlanOutput.setPrice(p.getPrice().getTotalPrice());
                newPlanOutput.setTax(p.getPrice().getTax());
                newPlanOutput.setPlanName(p.getName());
                planOutputList.add(newPlanOutput);
            }
            return new PlanResponse(planOutputList);
        }
        catch (Exception e)
        {
            return new PlanResponse("An error ocurred while getting the plan list: "+e.getMessage());
        }

    }

    @Override
    public PlanResponse registerPlan(PlanInput planInput) {
        try
        {
            Price newPrice = new Price();
            newPrice.setTotalPrice(planInput.getPrice());
            newPrice.setTax((planInput.getPrice())*0.19);
            newPrice.setPriceType(1);
            newPrice = priceRepository.save(newPrice);

            Plan newPlan = new Plan();
            newPlan.setName(planInput.getPlanName());
            newPlan.setDuration(planInput.getDurationDays());
            newPlan.setPrice(newPrice);
            newPlan = planRepository.save(newPlan);

            PlanOutput newPlanOutput = new PlanOutput();
            newPlanOutput.setId(newPlan.getId());
            newPlanOutput.setPlanName(newPlan.getName());
            newPlanOutput.setDurationDays(newPlan.getDuration());
            newPlanOutput.setPrice(newPrice.getTotalPrice());
            newPlanOutput.setTax(newPrice.getTax());

            return new PlanResponse(newPlanOutput);
        }
        catch (Exception e)
        {
            return new PlanResponse("An error ocurred while registering the plan : "+e.getMessage());
        }
    }

    @Override
    public PlanResponse findPlanById(int planId) {
        try
        {
            Plan getPlan = planRepository.findById(planId).get();
            PlanOutput newPlanOutput = new PlanOutput();
            newPlanOutput.setId(getPlan.getId());
            newPlanOutput.setDurationDays(getPlan.getDuration());
            newPlanOutput.setPrice(getPlan.getPrice().getTotalPrice());
            newPlanOutput.setTax(getPlan.getPrice().getTax());
            newPlanOutput.setPlanName(getPlan.getName());

            return new PlanResponse(newPlanOutput);
        }
        catch (Exception e)
        {
            return new PlanResponse("An error ocurred while getting the plan : "+e.getMessage());
        }

    }

    @Override
    public PlanResponse deletePlanById(int planId) {
        return null;
    }

    @Override
    public Plan save(Plan plan) {
        return planRepository.save(plan);
    }

    @Override
    public void deleteById(Integer id) {
        planRepository.deleteById(id);
    }

    @Override
    public Optional<Plan> findById(Integer id) {
        return planRepository.findById(id);
    }

    @Override
    public List<Plan> findAll() throws Exception {
        return planRepository.findAll();
    }
}
