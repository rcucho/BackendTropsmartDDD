package com.techelper.tropsmart_backend.user.domain.services.comunications;

import com.techelper.tropsmart_backend.configuration.domain.services.comunications.BaseResponse;
import com.techelper.tropsmart_backend.user.domain.services.outputs.FavoriteOutput;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class FavoriteResponse extends BaseResponse<FavoriteOutput> {
    public FavoriteResponse(List<FavoriteOutput> favouriteOutputList) {super(favouriteOutputList);}
    public FavoriteResponse(FavoriteOutput favouriteOutput) {super(favouriteOutput);}
    public FavoriteResponse(String message) {super(message);}
}
