package com.techelper.tropsmart_backend.user.domain.services.comunications;

import com.techelper.tropsmart_backend.configuration.domain.services.comunications.BaseResponse;
import com.techelper.tropsmart_backend.user.domain.services.outputs.BlockedOutput;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BlockedResponse extends BaseResponse<BlockedOutput> {
    public BlockedResponse(List<BlockedOutput> blockedOutputList) { super(blockedOutputList);}
    public BlockedResponse(BlockedOutput blockedOutput) {super(blockedOutput);}
    public BlockedResponse(String message) {super(message);}
}
