package xyz.bfdwdd.nshguildmanageb.extended.vote.controller;

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
import xyz.bfdwdd.nshguildmanageb.extended.vote.dto.request.InitiateVoteRequest;
import xyz.bfdwdd.nshguildmanageb.extended.vote.dto.request.SubmitVoteRequest;
import xyz.bfdwdd.nshguildmanageb.extended.vote.dto.request.UpdateVoteRequest;
import xyz.bfdwdd.nshguildmanageb.extended.vote.dto.response.VoteDetailResponse;
import xyz.bfdwdd.nshguildmanageb.extended.vote.dto.response.VoteStatusResponse;
import xyz.bfdwdd.nshguildmanageb.extended.vote.exception.*;
import xyz.bfdwdd.nshguildmanageb.extended.vote.service.VoteService;

import java.util.List;

@RestController
@RequestMapping("/api/votes")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class VoteController {

    private final VoteService voteService;

    @Operation(summary = "发起投票", description = "Initiate a new vote")
    @PostMapping
    public ResponseEntity<VoteDetailResponse> initiateVote(@RequestBody InitiateVoteRequest request) {
        return ResponseEntity.status(201).body(voteService.initiateVote(request));
    }

    @Operation(summary = "提交投票", description = "Submit a vote")
    @PostMapping("/submit")
    public ResponseEntity<VoteStatusResponse> submitVote(@RequestBody SubmitVoteRequest request) {
        return ResponseEntity.ok(voteService.submitVote(request));
    }

    @Operation(summary = "更新投票信息", description = "Update an existing vote")
    @PutMapping("/{id}")
    public ResponseEntity<VoteDetailResponse> updateVote(@PathVariable Long id, @RequestBody UpdateVoteRequest request) {
        return ResponseEntity.ok(voteService.updateVote(id, request));
    }

    @Operation(summary = "根据ID获取投票详情", description = "Get vote details by ID")
    @GetMapping("/{id}")
    public ResponseEntity<VoteDetailResponse> getVoteById(@PathVariable Long id) {
        return ResponseEntity.ok(voteService.getVoteById(id));
    }

    @Operation(summary = "获取帮会所有投票", description = "Get all votes of a guild")
    @GetMapping("/guild/{guildId}")
    public ResponseEntity<List<VoteDetailResponse>> getAllVotesByGuild(@PathVariable Long guildId) {
        return ResponseEntity.ok(voteService.getAllVotesByGuild(guildId));
    }
}