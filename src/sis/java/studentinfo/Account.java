package studentinfo;

import java.math.*;

import com.jimbob.ach.*;

public class Account implements Accountable {
    private BigDecimal balance = new BigDecimal("0.00");
    private int transactionCount = 0;
    private String bankAba;
    private String bankAccountNumber;
    private BankAccountType bankAccountType;
    private Ach ach;

    public enum BankAccountType {
        CHECKING("ck"), SAVINGS("sv");
        private final String value;

        BankAccountType(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    public void credit(BigDecimal amount) {
        balance = balance.add(amount);
        transactionCount++;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public BigDecimal transactionAverage() {
        return balance.divide(new BigDecimal(transactionCount), RoundingMode.HALF_UP);
    }

    public void setBankAba(String bankAba) {
        this.bankAba = bankAba;
    }

    public void setBankAccountNumber(String bankAccountNUmber) {
        this.bankAccountNumber = bankAccountNUmber;
    }

    public void setBankAccountType(Account.BankAccountType bankAccountType) {
        this.bankAccountType = bankAccountType;
    }

    public void transferFromBank(BigDecimal amount) {
        AchResponse achResponse = getAch().issueDebit(createCredentials(), createData(amount));
        if (achResponse.status == AchStatus.SUCCESS)
            credit(amount);
    }

    private AchCredentials createCredentials() {
        AchCredentials credentials = new AchCredentials();
        credentials.merchantId = "12355";
        credentials.userName = "sismerc1920";
        credentials.password = "pitseleh411";
        return credentials;
    }

    private AchTransactionData createData(BigDecimal amount) {
        AchTransactionData data = new AchTransactionData();
        data.description = "transfer from bank";
        data.amount = amount;
        data.aba = bankAba;
        data.account = bankAccountNumber;
        data.accounrType = bankAccountType.toString();
        return data;
    }

    public synchronized void withdraw(BigDecimal amount) {
        if (amount.compareTo(balance) > 0)
            return;
        balance = balance.subtract(amount);
    }

    private Ach getAch() {
        return ach;
    }

    void setAch(Ach ach) {
        this.ach = ach;
    }
}
