package xyz.bfdwdd.nshguildmanageb.extended.guildshop.controller;

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
import xyz.bfdwdd.nshguildmanageb.extended.guildshop.dto.request.AddGuildProductRequest;
import xyz.bfdwdd.nshguildmanageb.extended.guildshop.dto.request.BuyGuildProductRequest;
import xyz.bfdwdd.nshguildmanageb.extended.guildshop.dto.request.ConfirmVoteRequest;
import xyz.bfdwdd.nshguildmanageb.extended.guildshop.dto.request.InitiateVoteRequest;
import xyz.bfdwdd.nshguildmanageb.extended.guildshop.dto.response.GuildProductResponse;
import xyz.bfdwdd.nshguildmanageb.extended.guildshop.dto.response.VoteStatusResponse;
import xyz.bfdwdd.nshguildmanageb.extended.guildshop.exception.ConversionNotAllowedException;
import xyz.bfdwdd.nshguildmanageb.extended.guildshop.exception.ProductAlreadyExistsException;
import xyz.bfdwdd.nshguildmanageb.extended.guildshop.exception.ConversionDisabledException;
import xyz.bfdwdd.nshguildmanageb.extended.guildshop.service.GuildShopService;

import java.util.List;

@RestController
@RequestMapping("/api/guild-shop")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class GuildShopController {

    private final GuildShopService guildShopService;

    @Operation(summary = "添加商品到帮会商城", description = "Add a product to the guild shop")
    @PostMapping("/add-product")
    public ResponseEntity<GuildProductResponse> addGuildProduct(@RequestBody AddGuildProductRequest request) throws ProductAlreadyExistsException {
        return ResponseEntity.status(201).body(guildShopService.addProduct(request));
    }

    @Operation(summary = "获取帮会商城所有商品", description = "Get all products in a specific guild shop")
    @GetMapping("/{guildId}/products")
    public ResponseEntity<List<GuildProductResponse>> getGuildProducts(@PathVariable String guildId) {
        return ResponseEntity.ok(guildShopService.getAllProductsByGuild(guildId));
    }

    @Operation(summary = "发起投票", description = "Initiate a vote for guild shop operation")
    @PostMapping("/initiate-vote")
    public ResponseEntity<VoteStatusResponse> initiateVote(@RequestBody InitiateVoteRequest request) {
        return ResponseEntity.ok(guildShopService.initiateVote(request));
    }

    @Operation(summary = "确认投票", description = "Confirm a vote on a guild shop operation")
    @PostMapping("/confirm-vote")
    public ResponseEntity<VoteStatusResponse> confirmVote(@RequestBody ConfirmVoteRequest request) {
        return ResponseEntity.ok(guildShopService.confirmVote(request));
    }

    @Operation(summary = "购买商品", description = "Purchase a product from guild shop")
    @PostMapping("/buy")
    public ResponseEntity<GuildProductResponse> buyProduct(@RequestBody BuyGuildProductRequest request) {
        return ResponseEntity.ok(new GuildProductResponse()); // 示例返回
    }
}