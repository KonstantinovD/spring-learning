package ch6_hibernate.p220_oneToOne_joined_table.utils;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.Callable;


@Service
public class TransactionalProcessor {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public <V> V executeInNewTransaction(Callable<V> action) throws Exception {
        return action.call();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void runInNewTransaction(Runnable action) {
        action.run();
    }

}
