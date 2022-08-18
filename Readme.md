#Site 1
default-auth-service
#Site 2
manual-auth-service + manual-profile-service + gateway-service

Status:
Redis works well
Mongo does not work

Security:
org.springframework.security.web.context.SecurityContextPersistenceFilter

HttpSession:
org.springframework.session.web.http.SessionRepositoryFilter
org.springframework.session.SessionRepository
org.springframework.session.FindByIndexNameSessionRepository
org.springframework.session.web.http.HttpSessionIdResolver(org.springframework.session:spring-session-core:2.6.3)

HttpSession.Redis:
org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession
org.springframework.session.data.redis.config.annotation.web.http.RedisHttpSessionConfiguration
org.springframework.session.data.redis.RedisIndexedSessionRepository
org.springframework.data.redis.serializer.RedisSerializer

HttpSession.Mongo:
org.springframework.session.data.mongo.config.annotation.web.http.EnableMongoHttpSession
org.springframework.session.data.mongo.config.annotation.web.http.MongoHttpSessionConfiguration
org.springframework.session.data.mongo.MongoIndexedSessionRepository
org.springframework.session.data.mongo.JdkMongoSessionConverter

WebSession:
org.springframework.session.web.server.session.SpringSessionWebSessionStore
org.springframework.session.ReactiveSessionRepository
org.springframework.web.server.session.WebSessionIdResolver(org.springframework:spring-web:5.3.20)

WebSession.Redis:
org.springframework.session.data.redis.config.annotation.web.server.EnableRedisWebSession
org.springframework.session.data.redis.config.annotation.web.server.RedisWebSessionConfiguration
org.springframework.session.data.redis.ReactiveRedisSessionRepository
org.springframework.data.redis.serializer.RedisSerializer

WebSession.Mongo:
org.springframework.session.data.mongo.config.annotation.web.reactive.EnableMongoWebSession
org.springframework.session.data.mongo.config.annotation.web.reactive.ReactiveMongoWebSessionConfiguration
org.springframework.session.data.mongo.ReactiveMongoSessionRepository
org.springframework.session.data.mongo.JdkMongoSessionConverter