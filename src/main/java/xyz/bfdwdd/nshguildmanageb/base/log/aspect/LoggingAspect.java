package xyz.bfdwdd.nshguildmanageb.base.log.aspect;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import xyz.bfdwdd.nshguildmanageb.base.log.entity.Log;
import xyz.bfdwdd.nshguildmanageb.base.log.repository.LogRepository;
import xyz.bfdwdd.nshguildmanageb.base.user.entity.User;
import xyz.bfdwdd.nshguildmanageb.base.user.repository.UserRepository;

import java.time.LocalDateTime;

@Aspect
@Component
@RequiredArgsConstructor
public class LoggingAspect {

    private final LogRepository logRepository;
    private final UserRepository userRepository;

    // 定义切入点：所有 Controller 包下的方法
    @Pointcut("execution(* xyz.bfdwdd.nshguildmanageb..controller.*.*(..))")
    public void loggableMethods() {}

    @AfterReturning("loggableMethods()")
    public void logAfterMethodCall(org.aspectj.lang.JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        String userId = null;
        String action = methodName;
        String description = "Called method: " + methodName;
        String ipAddress = "127.0.0.1"; // 可从 Request 获取真实IP

        if (args.length > 0 && args[0] instanceof String id) {
            userId = id;
        } else if (args.length > 0 && args[0] instanceof User user) {
            userId = user.getId();
        }

        Log log = new Log();
        log.setAction(action);
        log.setUserId(userId);
        log.setDescription(description);
        log.setIpAddress(ipAddress);
        log.setTimestamp(LocalDateTime.now());

        logRepository.save(log);
    }
}