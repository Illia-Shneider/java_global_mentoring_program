package xyz.mpdn.jmp_bank_api;

import xyz.mpdn.jmp_dto.BankCard;
import xyz.mpdn.jmp_dto.BankCardType;
import xyz.mpdn.jmp_dto.User;

public interface Bank {
    BankCard createBankCard(User user, BankCardType cardType);
}
