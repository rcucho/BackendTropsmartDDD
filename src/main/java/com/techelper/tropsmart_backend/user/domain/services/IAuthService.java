package com.techelper.tropsmart_backend.user.domain.services;

import com.techelper.tropsmart_backend.user.domain.services.comunications.AuthResponse;
import com.techelper.tropsmart_backend.user.domain.services.inputs.SignUp;

public interface IAuthService {
    AuthResponse registerComplete(SignUp signUp);
    AuthResponse login(String email, String password);
    //AuthResponse refresh(RefreshInput refreshInput) throws Exception;
}

