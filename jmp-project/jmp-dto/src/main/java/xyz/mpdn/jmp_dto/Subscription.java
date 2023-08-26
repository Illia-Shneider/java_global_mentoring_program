package xyz.mpdn.jmp_dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class Subscription {
    private String bankcard;
    private LocalDate startDate;

    public void setStartDate(Date date){
        this.startDate = date.toLocalDate();
    }
}
