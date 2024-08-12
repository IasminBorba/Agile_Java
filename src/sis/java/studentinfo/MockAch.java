package studentinfo;

import com.jimbob.ach.*;

class MockAch implements Ach {
    public AchResponse issueDebit(AchCredentials credentials, AchTransactionData data) {
        return null;
    }

    public AchResponse markTransactionAsNSF(AchCredentials credentials, AchTransactionData data, String traceCode) {
        return null;
    }

    public AchResponse refundTransaction(AchCredentials credentials, AchTransactionData data, String traceCode) {
        return null;
    }

    public AchResponse issueCredit(AchCredentials credentials, AchTransactionData data) {
        return null;
    }

    public AchResponse voidSameDayTransaction(AchCredentials credentials, AchTransactionData data, String traceCode) {
        return null;
    }

    public AchResponse queryTransactionStatus(AchCredentials credentials, AchTransactionData data, String traceCode) {
        return null;
    }
}
