package com.techelper.tropsmart_backend.user.domain.services.comunications;

import com.techelper.tropsmart_backend.configuration.domain.services.comunications.BaseResponse;
import com.techelper.tropsmart_backend.user.domain.services.outputs.BalanceOutput;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BalanceResponse extends BaseResponse<BalanceOutput> {
    public BalanceResponse(String message) { super(message);}
    public BalanceResponse(BalanceOutput balanceOutput) { super(balanceOutput);}
    public BalanceResponse(List<BalanceOutput> balanceOutputList){ super(balanceOutputList);}
}
