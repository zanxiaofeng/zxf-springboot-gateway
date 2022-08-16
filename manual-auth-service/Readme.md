Security:
org.springframework.security.web.context.SecurityContextPersistenceFilter

HttpSession:
org.springframework.session.data.mongo.config.annotation.web.http.EnableMongoHttpSession
org.springframework.session.web.http.SessionRepositoryFilter
org.springframework.session.SessionRepository
org.springframework.session.FindByIndexNameSessionRepository
org.springframework.session.data.mongo.MongoIndexedSessionRepository
org.springframework.session.web.http.HttpSessionIdResolver(org.springframework.session:spring-session-core:2.6.3)

WebSession:
org.springframework.session.data.mongo.config.annotation.web.reactive.EnableMongoWebSession
org.springframework.session.web.server.session.SpringSessionWebSessionStore
org.springframework.session.ReactiveSessionRepository
org.springframework.session.data.mongo.ReactiveMongoSessionRepository
org.springframework.web.server.session.WebSessionIdResolver(org.springframework:spring-web:5.3.20)
