package com.techelper.tropsmart_backend.user.domain.services.inputs;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RefreshInput {
    private String token;
    private String refreshToken;
    private Integer userId;
}
