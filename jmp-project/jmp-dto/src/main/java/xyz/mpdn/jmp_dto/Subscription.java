package xyz.mpdn.jmp_dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class Subscription {
    private String bankcard;
    private LocalDate startDate;
}
