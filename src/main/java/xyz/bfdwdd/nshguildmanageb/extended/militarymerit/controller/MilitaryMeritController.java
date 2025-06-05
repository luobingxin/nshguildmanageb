package xyz.bfdwdd.nshguildmanageb.extended.militarymerit.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.bfdwdd.nshguildmanageb.extended.militarymerit.dto.request.*;
import xyz.bfdwdd.nshguildmanageb.extended.militarymerit.dto.response.*;
import xyz.bfdwdd.nshguildmanageb.extended.militarymerit.exception.*;
import xyz.bfdwdd.nshguildmanageb.extended.militarymerit.service.MilitaryMeritService;

import java.util.List;

@RestController
@RequestMapping("/api/merits")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class MilitaryMeritController {

    private final MilitaryMeritService militaryMeritService;

    @Operation(summary = "添加军功点", description = "为指定用户和帮会添加军功点")
    @PostMapping("/add")
    public ResponseEntity<MeritDetailResponse> addMerit(@RequestBody AddMeritRequest request) {
        return ResponseEntity.status(201).body(militaryMeritService.addMerit(request));
    }

    @Operation(summary = "扣除军功点", description = "从指定用户和帮会扣除军功点")
    @PostMapping("/deduct")
    public ResponseEntity<MeritDetailResponse> deductMerit(@RequestBody DeductMeritRequest request) {
        return ResponseEntity.ok(militaryMeritService.deductMerit(request));
    }

    @Operation(summary = "获取军功点详情", description = "根据用户ID和帮会ID获取军功点详情")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功找到军功点"),
            @ApiResponse(responseCode = "404", description = "未找到军功点", content = @Content)
    })
    @GetMapping("/{userId}/{guildId}")
    public ResponseEntity<MeritDetailResponse> getMeritDetails(
            @PathVariable String userId,
            @PathVariable Long guildId) {
        return ResponseEntity.ok(militaryMeritService.getMeritByUserAndGuild(userId, guildId));
    }

    @Operation(summary = "获取用户所有军功点总览", description = "获取某个用户在所有帮会中的军功点总数")
    @GetMapping("/summary/{userId}")
    public ResponseEntity<MeritSummaryResponse> getMeritSummary(@PathVariable String userId) {
        return ResponseEntity.ok(militaryMeritService.getMeritSummaryForUser(userId));
    }

    @Operation(summary = "清空军功点", description = "清空某个用户在指定帮会中的军功点")
    @PostMapping("/clear")
    public ResponseEntity<Void> clearMerit(@RequestBody ClearMeritRequest request) {
        militaryMeritService.clearMerit(request);
        return ResponseEntity.noContent().build();
    }
}