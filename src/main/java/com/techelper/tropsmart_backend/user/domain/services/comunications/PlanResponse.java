package com.techelper.tropsmart_backend.user.domain.services.comunications;

import com.techelper.tropsmart_backend.configuration.domain.services.comunications.BaseResponse;
import com.techelper.tropsmart_backend.user.domain.services.outputs.PlanOutput;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PlanResponse extends BaseResponse<PlanOutput> {
    public PlanResponse(List<PlanOutput> planOutputList) {super(planOutputList);}
    public PlanResponse(PlanOutput planOutput) {super(planOutput);}
    public PlanResponse(String message) { super(message);}
}