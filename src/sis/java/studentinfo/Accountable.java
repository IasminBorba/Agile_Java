package studentinfo;

import java.math.*;

public interface Accountable {
    void credit(BigDecimal amount);

    BigDecimal getBalance();

    BigDecimal transactionAverage();

    void setBankAba(String bankAba);

    void setBankAccountNumber(String bankAccountNumber);

    void setBankAccountType(Account.BankAccountType bankAccountType);

    void transferFromBank(BigDecimal amount);
}
