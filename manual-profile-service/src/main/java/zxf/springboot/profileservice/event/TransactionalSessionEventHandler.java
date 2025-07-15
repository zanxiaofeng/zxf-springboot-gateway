package zxf.springboot.profileservice.event;

import org.springframework.session.events.SessionCreatedEvent;
import org.springframework.session.events.SessionDeletedEvent;
import org.springframework.session.events.SessionDestroyedEvent;
import org.springframework.session.events.SessionExpiredEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class TransactionalSessionEventHandler {

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleSessionCreatedEvent(SessionCreatedEvent event) {
        System.out.printf("事务提交后 - 会话已创建: %s\n", event.getSessionId());
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleSessionDeletedEvent(SessionDeletedEvent event) {
        System.out.printf("事务提交后 - 会话已删除: %s\n", event.getSessionId());
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleSessionExpiredEvent(SessionExpiredEvent event) {
        System.out.printf("事务提交后 - 会话已过期: %s\n", event.getSessionId());
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleSessionDestroyedEvent(SessionDestroyedEvent event) {
        System.out.printf("事务提交后 - 会话已销毁: %s\n", event.getSessionId());
    }
}
