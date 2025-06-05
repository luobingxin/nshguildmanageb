package xyz.bfdwdd.nshguildmanageb.functional.guild.controller;

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
import xyz.bfdwdd.nshguildmanageb.functional.guild.dto.request.*;
import xyz.bfdwdd.nshguildmanageb.functional.guild.dto.response.GuildMemberResponse;
import xyz.bfdwdd.nshguildmanageb.functional.guild.dto.response.GuildResponse;
import xyz.bfdwdd.nshguildmanageb.functional.guild.exception.GuildNotFoundException;
import xyz.bfdwdd.nshguildmanageb.functional.guild.service.GuildService;

import java.util.List;

@RestController
@RequestMapping("/api/guilds")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class GuildController {

    private final GuildService guildService;

    @Operation(summary = "创建新帮会", description = "Create a new guild with specified parameters")
    @PostMapping
    public ResponseEntity<GuildResponse> createGuild(@RequestBody CreateGuildRequest request) {
        return ResponseEntity.status(201).body(guildService.createGuild(request));
    }

    @Operation(summary = "加入帮会", description = "Join an existing guild using invitation code")
    @PostMapping("/join")
    public ResponseEntity<GuildResponse> joinGuild(@RequestBody JoinGuildRequest request) {
        return ResponseEntity.ok(guildService.joinGuild(request));
    }

    @Operation(summary = "更新帮会信息", description = "Update an existing guild by ID")
    @PutMapping("/{id}")
    public ResponseEntity<GuildResponse> updateGuild(@PathVariable Long id, @RequestBody UpdateGuildRequest request) {
        return ResponseEntity.ok(guildService.updateGuild(id, request));
    }

    @Operation(summary = "根据ID获取帮会", description = "Get a guild by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功找到帮会"),
            @ApiResponse(responseCode = "404", description = "未找到帮会", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<GuildResponse> getGuildById(@Parameter(description = "帮会的ID") @PathVariable Long id) {
        return ResponseEntity.ok(guildService.getGuildById(id));
    }

    @Operation(summary = "获取所有帮会", description = "Get all available guilds")
    @GetMapping
    public ResponseEntity<List<GuildResponse>> getAllGuilds() {
        return ResponseEntity.ok(guildService.getAllGuilds());
    }

    @Operation(summary = "获取所有帮会成员", description = "Get all members of a guild")
    @GetMapping("/{id}/members")
    public ResponseEntity<List<GuildMemberResponse>> getGuildMembers(@PathVariable Long id) {
        return ResponseEntity.ok(guildService.getMembersByGuild(id));
    }

    @Operation(summary = "转让帮主身份", description = "Transfer guild ownership to another member")
    @PutMapping("/transfer-owner")
    public ResponseEntity<Void> transferOwnership(@RequestBody TransferOwnerRequest request) {
        guildService.transferOwnership(request);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "解散帮会", description = "Disband a guild by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> disbandGuild(@PathVariable Long id) {
        guildService.disbandGuild(id);
        return ResponseEntity.noContent().build();
    }
}