package com.techelper.tropsmart_backend.cargo.domain.services.comunications;

import com.techelper.tropsmart_backend.cargo.domain.services.outputs.RouteOutput;
import com.techelper.tropsmart_backend.configuration.domain.services.comunications.BaseResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class RouteResponse extends BaseResponse<RouteOutput> {
    public RouteResponse(String message) { super(message);}
    public RouteResponse(RouteOutput routeOutput) {super(routeOutput);}
    public RouteResponse(List<RouteOutput> routeOutputList) {super(routeOutputList);}
}
