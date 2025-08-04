package org.example.clinic_system.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Tuple <T1, T2> {
    private T1 first;
    private T2 second;
}
