package com.techelper.tropsmart_backend.configuration.domain.services.comunications;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public abstract class BaseResponse<T> {
    public boolean success;
    public String message;
    public T resource;
    public List<T> resourceList;

    public BaseResponse(T resource)
    {
        this.resource = resource;
        this.success = true;
        this.message = "Success";
    }

    public BaseResponse(List<T> resource)
    {
        this.resourceList = resource;
        this.success = true;
        this.message = "Success";
    }

    public BaseResponse(String message)
    {
        this.success = false;
        this.message = message;
    }
}
