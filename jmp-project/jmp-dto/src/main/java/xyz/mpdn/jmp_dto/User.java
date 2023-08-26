package xyz.mpdn.jmp_dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class User {
    private String name;
    private String surname;
    private LocalDate birthday;

//    public void setBirthday(Date date){
//        this.birthday = date.toLocalDate();
//    }
}
