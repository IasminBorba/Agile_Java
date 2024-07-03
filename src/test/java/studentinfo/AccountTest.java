package studentinfo;

import com.jimbob.ach.*;
import junit.framework.Assert;
import junit.framework.TestCase;
import java.math.BigDecimal;
import java.util.Date;

public class AccountTest extends TestCase {
    static final String ABA = "102000012";
    static final String ACCOUNT_NUMBER = "194431518811";
    private Account account;

    protected void setUp(){
        account = new Account();
        account.setBankAba(ABA);
        account.setBankAccountNumber(ACCOUNT_NUMBER);
        account.setBankAccountType(Account.BankAccountType.CHECKING);
    }
    public void testTransactions(){
        Account account = new Account();
        account.credit(new BigDecimal("0.10"));
        account.credit(new BigDecimal("11.00"));
        assertEquals(new BigDecimal("11.10"), account.getBalance());

        assertEquals(new BigDecimal("5.300"), new BigDecimal("5.000").add(new BigDecimal("0.3")));
    }

    public void testTransactionAverage(){
        Account account = new Account();
        account.credit(new BigDecimal("0.10"));
        account.credit(new BigDecimal("11.00"));
        account.credit(new BigDecimal("2.99"));
        assertEquals(new BigDecimal("4.70"), account.transactionAverage());
    }

    public void testTransferFromBank() {
        Ach mockAch = new Ach() {
            public AchResponse issueDebit(AchCredentials credentials, AchTransactionData data) {
                Assert.assertTrue(data.account.equals(AccountTest.ACCOUNT_NUMBER));
                Assert.assertTrue(data.aba.equals(AccountTest.ABA));
                AchResponse response = new AchResponse();
                response.timestamp = new Date();
                response.traceCode = "1";
                response.status = AchStatus.SUCCESS;
                return response;
            }
            public AchResponse markTransactionAsNSF(AchCredentials credentials, AchTransactionData data, String traceCode) {
                return null;
            }
            public AchResponse refundTransaction(AchCredentials credentials,AchTransactionData data, String traceCode) {
                return null;
            }
            public AchResponse issueCredit(AchCredentials credentials, AchTransactionData data) {
                return null;
            }
            public AchResponse voidSameDayTransaction(AchCredentials credentials, AchTransactionData data, String traceCode) {
                return null;
            }
            public AchResponse queryTransactionStatus (AchCredentials credentials, AchTransactionData data, String traceCode) {
                return null;
            }
        };
        account.setAch(mockAch);
        final BigDecimal amount = new BigDecimal("50.00");
        account.transferFromBank(amount);
        assertEquals(amount, account.getBalance());
    }
}
