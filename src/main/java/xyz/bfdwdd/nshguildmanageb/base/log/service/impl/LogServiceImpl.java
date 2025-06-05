package xyz.bfdwdd.nshguildmanageb.base.log.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.bfdwdd.nshguildmanageb.base.log.dto.request.LogSearchRequest;
import xyz.bfdwdd.nshguildmanageb.base.log.dto.response.LogResponse;
import xyz.bfdwdd.nshguildmanageb.base.log.entity.Log;
import xyz.bfdwdd.nshguildmanageb.base.log.exception.LogNotFoundException;
import xyz.bfdwdd.nshguildmanageb.base.log.repository.LogRepository;
import xyz.bfdwdd.nshguildmanageb.base.log.service.LogService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LogServiceImpl implements LogService {

    private final LogRepository logRepository;

    @Override
    public List<LogResponse> searchLogs(LogSearchRequest request) {
        List<Log> logs = logRepository.findAll();

        if (request.getUserId() != null && !request.getUserId().isEmpty()) {
            logs = logs.stream()
                    .filter(log -> log.getUserId().equals(request.getUserId()))
                    .toList();
        }

        if (request.getGuildId() != null) {
            logs = logs.stream()
                    .filter(log -> request.getGuildId().equals(log.getGuildId()))
                    .toList();
        }

        if (request.getAction() != null && !request.getAction().isEmpty()) {
            logs = logs.stream()
                    .filter(log -> log.getAction().contains(request.getAction()))
                    .toList();
        }

        if (request.getKeyword() != null && !request.getKeyword().isEmpty()) {
            logs = logs.stream()
                    .filter(log -> log.getDescription().contains(request.getKeyword()))
                    .toList();
        }

        return logs.stream()
                .map(LogResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public LogResponse getLogById(Long id) {
        return logRepository.findById(id)
                .map(LogResponse::fromEntity)
                .orElseThrow(() -> new LogNotFoundException("Notification not found with ID: " + id));
    }
}