# Site 1
- default-auth-service
# Site 2
- manual-auth-service + manual-profile-service + gateway-service

# Status:
- Redis works well
- Mongo does not work

# Security:
- org.springframework.security.web.context.SecurityContextPersistenceFilter

# WebMVC
- javax.servlet.Filter
- javax.servlet.FilterChain
- javax.servlet.Servlet
- javax.servlet.ServletRequest
- javax.servlet.ServletResponse
- javax.servlet.http.HttpServlet
- javax.servlet.http.HttpServletRequest
- javax.servlet.http.HttpServletResponse
- javax.servlet.http.HttpSession*


# WebMVC.HttpSession:
- org.springframework.session.Session*
- org.springframework.session.MapSession
- org.springframework.session.SessionRepository
- org.springframework.session.MapSessionRepository
- org.springframework.session.FindByIndexNameSessionRepository
- org.springframework.session.web.http.HttpSessionAdapter
- org.springframework.session.web.http.HttpSessionAdapter.setAttribute(#, #)
- org.springframework.session.web.http.HttpSessionIdResolver(org.springframework.session:spring-session-core:2.6.3)
- org.springframework.session.web.http.SessionRepositoryFilter
- org.springframework.session.web.http.SessionRepositoryFilter.SessionRepositoryRequestWrapper
- org.springframework.session.web.http.SessionRepositoryFilter.SessionRepositoryResponseWrapper
- org.springframework.session.web.http.SessionRepositoryFilter.SessionRepositoryRequestWrapper.HttpSessionWrapper


# WebMVC.HttpSession.Redis:
- org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession
- org.springframework.session.data.redis.config.annotation.web.http.RedisHttpSessionConfiguration
- org.springframework.session.data.redis.RedisIndexedSessionRepository
- org.springframework.session.data.redis.RedisIndexedSessionRepository.RedisSession*
- org.springframework.data.redis.serializer.RedisSerializer

# WebMVC.HttpSession.Mongo:
- org.springframework.session.data.mongo.config.annotation.web.http.EnableMongoHttpSession
- org.springframework.session.data.mongo.config.annotation.web.http.MongoHttpSessionConfiguration
- org.springframework.session.data.mongo.MongoSession*
- org.springframework.session.data.mongo.MongoIndexedSessionRepository
- org.springframework.session.data.mongo.JdkMongoSessionConverter

# WebFlux
- org.springframework.web.server.ServerWebExchange(Request, Response)
- org.springframework.web.server.WebSession*
- org.springframework.web.server.WebFilter
- org.springframework.web.server.WebFilterChain
- org.springframework.web.server.session.WebSessionManager
- org.springframework.web.server.session.WebSessionIdResolver(org.springframework:spring-web:5.3.20)
- org.springframework.web.server.session.WebSessionStore
- org.springframework.web.server.session.InMemoryWebSessionStore
- org.springframework.web.server.session.InMemoryWebSessionStore.InMemoryWebSession*

# WebFlux.WebSession:
- org.springframework.session.web.server.session.SpringSessionWebSessionStore
- org.springframework.session.web.server.session.SpringSessionWebSessionStore.SpringSessionWebSession*
- org.springframework.session.web.server.session.SpringSessionWebSessionStore.SpringSessionMap.put(#, #)
- org.springframework.session.Session*
- org.springframework.session.MapSession
- org.springframework.session.ReactiveSessionRepository
- org.springframework.session.ReactiveMapSessionRepository


# WebFlux.WebSession.Redis:
- org.springframework.session.data.redis.config.annotation.web.server.EnableRedisWebSession
- org.springframework.session.data.redis.config.annotation.web.server.RedisWebSessionConfiguration
- org.springframework.session.config.annotation.web.server.SpringWebSessionConfiguration
- org.springframework.session.data.redis.ReactiveRedisSessionRepository
- org.springframework.session.data.redis.ReactiveRedisSessionRepository.RedisSession*
- org.springframework.data.redis.serializer.RedisSerializer

# WebFlux.WebSession.Mongo:
- org.springframework.session.data.mongo.config.annotation.web.reactive.EnableMongoWebSession
- org.springframework.session.data.mongo.config.annotation.web.reactive.ReactiveMongoWebSessionConfiguration
- org.springframework.session.data.redis.config.annotation.web.server.RedisWebSessionConfiguration
- org.springframework.session.data.mongo.MongoSession
- org.springframework.session.data.mongo.ReactiveMongoSessionRepository
- org.springframework.session.data.mongo.JdkMongoSessionConverter