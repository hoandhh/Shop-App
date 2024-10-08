package com.hoandhh.backend.responses;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse extends BaseResponse {
    private long id;

    @JsonProperty("fullname")
    private long userId;

    @JsonProperty("phone_number")
    private String phoneNumber;

    private String name;
    private String note;

    @JsonProperty("order_date")
    private LocalDateTime orderDate;

    private String status;
}
