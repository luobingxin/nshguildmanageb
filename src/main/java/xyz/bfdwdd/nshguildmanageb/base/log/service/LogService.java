package xyz.bfdwdd.nshguildmanageb.base.log.service;

import xyz.bfdwdd.nshguildmanageb.base.log.dto.request.LogSearchRequest;
import xyz.bfdwdd.nshguildmanageb.base.log.dto.response.LogResponse;

import java.util.List;

public interface LogService {
    List<LogResponse> searchLogs(LogSearchRequest request);
    LogResponse getLogById(Long id);
}