package com.cloudtimes.app.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AfterConnectedData {
    private String isSupervise;

    public AfterConnectedData(String isSupervise) {
        this.isSupervise = isSupervise;
    }
}
