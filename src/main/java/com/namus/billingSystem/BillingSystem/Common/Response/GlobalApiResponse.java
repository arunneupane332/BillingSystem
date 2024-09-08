package com.namus.billingSystem.BillingSystem.Common.Response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GlobalApiResponse {

    private int code;
    private String message;
    private Object data;
    private boolean status;
}
