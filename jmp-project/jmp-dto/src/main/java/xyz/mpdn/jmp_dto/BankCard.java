package xyz.mpdn.jmp_dto;

import lombok.Data;

@Data
public class BankCard {
    private User user;
    private String number;
}
