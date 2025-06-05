package xyz.bfdwdd.nshguildmanageb.extended.guildshop.service;

import xyz.bfdwdd.nshguildmanageb.extended.guildshop.dto.request.AddGuildProductRequest;
import xyz.bfdwdd.nshguildmanageb.extended.guildshop.dto.request.BuyGuildProductRequest;
import xyz.bfdwdd.nshguildmanageb.extended.guildshop.dto.request.ConfirmVoteRequest;
import xyz.bfdwdd.nshguildmanageb.extended.guildshop.dto.request.InitiateVoteRequest;
import xyz.bfdwdd.nshguildmanageb.extended.guildshop.dto.response.GuildProductResponse;
import xyz.bfdwdd.nshguildmanageb.extended.guildshop.dto.response.VoteStatusResponse;

import java.util.List;

public interface GuildShopService {
    GuildProductResponse addProduct(AddGuildProductRequest request);
    GuildProductResponse getProductById(String id);
    List<GuildProductResponse> getAllProductsByGuild(String guildId);
    VoteStatusResponse initiateVote(InitiateVoteRequest request);
    VoteStatusResponse confirmVote(ConfirmVoteRequest request);
}