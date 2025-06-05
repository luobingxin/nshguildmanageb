package xyz.bfdwdd.nshguildmanageb.extended.guildshop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.bfdwdd.nshguildmanageb.extended.guildshop.dto.request.AddGuildProductRequest;
import xyz.bfdwdd.nshguildmanageb.extended.guildshop.dto.request.BuyGuildProductRequest;
import xyz.bfdwdd.nshguildmanageb.extended.guildshop.dto.request.ConfirmVoteRequest;
import xyz.bfdwdd.nshguildmanageb.extended.guildshop.dto.request.InitiateVoteRequest;
import xyz.bfdwdd.nshguildmanageb.extended.guildshop.dto.response.GuildProductResponse;
import xyz.bfdwdd.nshguildmanageb.extended.guildshop.dto.response.VoteStatusResponse;
import xyz.bfdwdd.nshguildmanageb.extended.guildshop.entity.GuildProduct;
import xyz.bfdwdd.nshguildmanageb.extended.guildshop.entity.GuildVote;
import xyz.bfdwdd.nshguildmanageb.extended.guildshop.entity.GuildVoteRecord;
import xyz.bfdwdd.nshguildmanageb.extended.guildshop.exception.ProductAlreadyExistsException;
import xyz.bfdwdd.nshguildmanageb.extended.guildshop.exception.ConversionNotAllowedException;
import xyz.bfdwdd.nshguildmanageb.extended.guildshop.repository.GuildProductRepository;
import xyz.bfdwdd.nshguildmanageb.extended.guildshop.repository.GuildVoteRepository;
import xyz.bfdwdd.nshguildmanageb.extended.guildshop.repository.GuildVoteRecordRepository;
import xyz.bfdwdd.nshguildmanageb.extended.guildshop.service.GuildShopService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GuildShopServiceImpl implements GuildShopService {

    private final GuildProductRepository productRepository;
    private final GuildVoteRepository voteRepository;
    private final GuildVoteRecordRepository voteRecordRepository;

    @Override
    public GuildProductResponse addProduct(AddGuildProductRequest request) {
        if (productRepository.existsById(request.getProductId())) {
            throw new ProductAlreadyExistsException("商品已存在");
        }

        GuildProduct product = new GuildProduct();
        product.setId(request.getProductId());
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        product.setCurrencyType(request.getCurrencyType());
        product.setGuildId(request.getGuildId());

        return mapToResponse(productRepository.save(product));
    }

    @Override
    public GuildProductResponse getProductById(String id) {
        GuildProduct product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("商品未找到"));
        return mapToResponse(product);
    }

    @Override
    public List<GuildProductResponse> getAllProductsByGuild(String guildId) {
        return productRepository.findByGuildId(guildId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public VoteStatusResponse initiateVote(InitiateVoteRequest request) {
        GuildVote vote = new GuildVote();
        vote.setId(UUID.randomUUID().toString());
        vote.setInitiatorId(request.getInitiatorId());
        vote.setActionType(request.getActionType());
        vote.setTargetId(request.getTargetId());
        vote.setGuildId(request.getGuildId());
        vote.setStatus("PENDING");
        vote.setCreatedAt(LocalDateTime.now());

        return mapToVoteStatus(voteRepository.save(vote));
    }

    @Override
    public VoteStatusResponse confirmVote(ConfirmVoteRequest request) {
        GuildVote vote = voteRepository.findById(request.getVoteId())
                .orElseThrow(() -> new RuntimeException("投票未找到"));

        GuildVoteRecord record = new GuildVoteRecord();
        record.setVoteId(request.getVoteId());
        record.setVoterId(request.getConfirmorId());
        record.setApproved(request.getApproved());
        record.setVoteTime(LocalDateTime.now());

        voteRecordRepository.save(record);

        return mapToVoteStatus(vote);
    }

    private GuildProductResponse mapToResponse(GuildProduct product) {
        GuildProductResponse response = new GuildProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setStock(product.getStock());
        response.setCurrencyType(product.getCurrencyType());
        response.setGuildId(product.getGuildId());
        return response;
    }

    private VoteStatusResponse mapToVoteStatus(GuildVote vote) {
        VoteStatusResponse response = new VoteStatusResponse();
        response.setVoteId(vote.getId());
        response.setActionType(vote.getActionType());
        response.setCurrentVotes(0); // 示例值，实际从数据库获取
        response.setRequiredVotes(5); // 示例值，实际可配置
        response.setStatus(vote.getStatus());
        response.setCreatedAt(vote.getCreatedAt());
        return response;
    }
}