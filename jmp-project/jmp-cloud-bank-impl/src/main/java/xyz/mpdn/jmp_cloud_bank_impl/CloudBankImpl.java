package xyz.mpdn.jmp_cloud_bank_impl;

import lombok.Data;
import xyz.mpdn.jmp_bank_api.Bank;
import xyz.mpdn.jmp_dto.*;

import java.util.function.Supplier;

import static xyz.mpdn.jmp_dto.BankCardType.CREDIT;

@Data
public class CloudBankImpl implements Bank {
    @Override
    public BankCard createBankCard(User user, BankCardType cardType) {
        return CREDIT.equals(cardType) ?
                createBankCard(CreditBankCard::new) :
                createBankCard(DebitBankCard::new);
    }

    public <R> R createBankCard(Supplier<R> supplier) {
        return supplier.get();
    }

}
