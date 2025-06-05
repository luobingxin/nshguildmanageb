package xyz.bfdwdd.nshguildmanageb.extended.meritconversion.controller;

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
import xyz.bfdwdd.nshguildmanageb.extended.meritconversion.dto.request.EnableConversionRequest;
import xyz.bfdwdd.nshguildmanageb.extended.meritconversion.dto.request.SetConversionRateRequest;
import xyz.bfdwdd.nshguildmanageb.extended.meritconversion.dto.response.ConversionRateResponse;
import xyz.bfdwdd.nshguildmanageb.extended.meritconversion.exception.ConversionNotAllowedException;
import xyz.bfdwdd.nshguildmanageb.extended.meritconversion.exception.ConversionDisabledException;
import xyz.bfdwdd.nshguildmanageb.extended.meritconversion.service.MeritConversionService;

@RestController
@RequestMapping("/api/conversion")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class MeritConversionController {

    private final MeritConversionService conversionService;

    @Operation(summary = "设置军功点转换率", description = "Set merit to currency conversion rates")
    @PostMapping("/set-rate")
    public ResponseEntity<ConversionRateResponse> setConversionRate(@RequestBody SetConversionRateRequest request) throws ConversionNotAllowedException {
        return ResponseEntity.ok(conversionService.setRate(request));
    }

    @Operation(summary = "启用军功点转换", description = "Enable merit point conversion")
    @PutMapping("/enable")
    public ResponseEntity<ConversionRateResponse> enableConversion(@RequestBody EnableConversionRequest request) throws ConversionDisabledException {
        return ResponseEntity.ok(conversionService.enableConversion(request));
    }

    @Operation(summary = "获取帮会转换率", description = "Get current conversion rate for a specific guild")
    @GetMapping("/{guildId}")
    public ResponseEntity<ConversionRateResponse> getConversionRate(@PathVariable String guildId) {
        return ResponseEntity.ok(conversionService.getRateByGuild(guildId));
    }
}