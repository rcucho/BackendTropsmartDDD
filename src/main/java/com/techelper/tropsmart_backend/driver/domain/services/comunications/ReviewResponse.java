package com.techelper.tropsmart_backend.driver.domain.services.comunications;

import com.techelper.tropsmart_backend.configuration.domain.services.comunications.BaseResponse;
import com.techelper.tropsmart_backend.driver.domain.services.outputs.ReviewOutput;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ReviewResponse extends BaseResponse<ReviewOutput> {
    public ReviewResponse(List<ReviewOutput> reviewOutputList) {super(reviewOutputList);}
    public ReviewResponse(ReviewOutput reviewOutput){super(reviewOutput);}
    public ReviewResponse(String message) {super(message);}
}