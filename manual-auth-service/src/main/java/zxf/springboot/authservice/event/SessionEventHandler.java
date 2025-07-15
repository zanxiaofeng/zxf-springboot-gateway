package zxf.springboot.authservice.event;

import org.springframework.context.event.EventListener;
import org.springframework.session.events.SessionCreatedEvent;
import org.springframework.session.events.SessionDeletedEvent;
import org.springframework.session.events.SessionExpiredEvent;
import org.springframework.session.events.SessionDestroyedEvent;
import org.springframework.stereotype.Component;

@Component
public class SessionEventHandler {
    @EventListener
    public void handleSessionCreatedEvent(SessionCreatedEvent event) {
        System.out.printf("会话已创建: %s\n", event.getSessionId());
    }

    @EventListener
    public void handleSessionDeletedEvent(SessionDeletedEvent event) {
        System.out.printf("会话已删除: %s\n", event.getSessionId());
    }

    @EventListener
    public void handleSessionExpiredEvent(SessionExpiredEvent event) {
        System.out.printf("会话已过期: %s\n", event.getSessionId());
    }

    @EventListener
    public void handleSessionDestroyedEvent(SessionDestroyedEvent event) {
        System.out.printf("会话已销毁: %s\n", event.getSessionId());
    }
}
