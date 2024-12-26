package com.develop.revelryspringboot.constant.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReservationStatus {
    PENDING,
    CONFIRMED,
    CANCELLED
}
