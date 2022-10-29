package com.techelper.tropsmart_backend.user.domain.services.comunications;

import com.techelper.tropsmart_backend.configuration.domain.services.comunications.BaseResponse;
import com.techelper.tropsmart_backend.user.domain.services.outputs.UserOutput;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserResponse extends BaseResponse<UserOutput> {
    public UserResponse(List<UserOutput> userOutputList) {super(userOutputList);}
    public UserResponse(UserOutput userOutput) {super(userOutput);}
    public UserResponse(String message) {super(message);}
}
