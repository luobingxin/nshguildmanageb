package xyz.bfdwdd.nshguildmanageb.base.log.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.bfdwdd.nshguildmanageb.base.log.dto.request.LogSearchRequest;
import xyz.bfdwdd.nshguildmanageb.base.log.dto.response.LogResponse;
import xyz.bfdwdd.nshguildmanageb.base.log.service.LogService;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class LogController {

    private final LogService logService;

    @Operation(summary = "搜索日志", description = "根据条件搜索日志")
    @PostMapping("/search")
    public ResponseEntity<List<LogResponse>> searchLogs(@RequestBody LogSearchRequest request) {
        return ResponseEntity.ok(logService.searchLogs(request));
    }

    @Operation(summary = "获取日志详情", description = "根据ID获取日志详情")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功找到日志"),
            @ApiResponse(responseCode = "404", description = "未找到日志", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<LogResponse> getLogById(@PathVariable Long id) {
        return ResponseEntity.ok(logService.getLogById(id));
    }
}