package xyz.bfdwdd.nshguildmanageb.extended.vote.service.impl;

import lombok.*;
import org.springframework.stereotype.Service;
import xyz.bfdwdd.nshguildmanageb.extended.vote.dto.request.InitiateVoteRequest;
import xyz.bfdwdd.nshguildmanageb.extended.vote.dto.request.SubmitVoteRequest;
import xyz.bfdwdd.nshguildmanageb.extended.vote.dto.request.UpdateVoteRequest;
import xyz.bfdwdd.nshguildmanageb.extended.vote.dto.response.VoteDetailResponse;
import xyz.bfdwdd.nshguildmanageb.extended.vote.dto.response.VoteStatusResponse;
import xyz.bfdwdd.nshguildmanageb.extended.vote.entity.GuildVote;
import xyz.bfdwdd.nshguildmanageb.extended.vote.entity.GuildVoteRecord;
import xyz.bfdwdd.nshguildmanageb.extended.vote.entity.VoteType;
import xyz.bfdwdd.nshguildmanageb.extended.vote.exception.*;
import xyz.bfdwdd.nshguildmanageb.extended.vote.repository.VoteRecordRepository;
import xyz.bfdwdd.nshguildmanageb.extended.vote.repository.VoteRepository;
import xyz.bfdwdd.nshguildmanageb.extended.vote.service.VoteService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefaultVoteServiceImpl implements VoteService {

    private final VoteRepository voteRepository;
    private final VoteRecordRepository recordRepository;

    @Override
    public VoteDetailResponse initiateVote(InitiateVoteRequest request) {
        GuildVote vote = new GuildVote();
        vote.setGuildId(request.getGuildId());
        vote.setTitle(request.getTitle());
        vote.setDescription(request.getDescription());
        vote.setVoteType(VoteType.valueOf(request.getVoteType()));
        vote.setStartTime(request.getStartTime());
        vote.setEndTime(request.getEndTime());
        vote.setRequiredApprovals(request.getRequiredApprovals());
        vote.setStatus("PENDING");

        GuildVote saved = voteRepository.save(vote);

        return mapToDetailResponse(saved);
    }

    @Override
    public VoteStatusResponse submitVote(SubmitVoteRequest request) {
        GuildVote vote = voteRepository.findById(request.getVoteId())
                .orElseThrow(() -> new VoteNotFoundException("投票未找到"));

        if (recordRepository.existsByVoteIdAndVoterId(request.getVoteId(), request.getUserId())) {
            throw new IllegalArgumentException("用户已投过票");
        }

        if (LocalDateTime.now().isAfter(vote.getEndTime())) {
            throw new VoteAlreadyEndedException("投票已结束");
        }

        GuildVoteRecord record = new GuildVoteRecord();
        record.setVoteId(request.getVoteId());
        record.setVoterId(request.getUserId());
        record.setApproved(request.getApproved());
        recordRepository.save(record);

        int totalApprovals = recordRepository.countByVoteIdAndApprovedTrue(request.getVoteId());

        if (totalApprovals >= vote.getRequiredApprovals()) {
            vote.setStatus("APPROVED");
            voteRepository.save(vote);
        }

        return mapToVoteStatus(vote, totalApprovals);
    }

    @Override
    public VoteDetailResponse updateVote(Long voteId, UpdateVoteRequest request) {
        GuildVote vote = voteRepository.findById(voteId)
                .orElseThrow(() -> new VoteNotFoundException("投票未找到"));

        if (!"PENDING".equals(vote.getStatus())) {
            throw new VoteAlreadyEndedException("投票已结束，无法修改");
        }

        vote.setTitle(request.getTitle());
        vote.setDescription(request.getDescription());
        vote.setEndTime(request.getEndTime());
        GuildVote updated = voteRepository.save(vote);

        return mapToDetailResponse(updated);
    }

    @Override
    public VoteDetailResponse getVoteById(Long voteId) {
        GuildVote vote = voteRepository.findById(voteId)
                .orElseThrow(() -> new VoteNotFoundException("投票未找到"));
        return mapToDetailResponse(vote);
    }

    @Override
    public List<VoteDetailResponse> getAllVotesByGuild(Long guildId) {
        return voteRepository.findByGuildId(guildId).stream()
                .map(this::mapToDetailResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void endVoteIfExpired() {
        List<GuildVote> votes = voteRepository.findByStatus("PENDING");
        LocalDateTime now = LocalDateTime.now();

        for (GuildVote vote : votes) {
            if (now.isAfter(vote.getEndTime())) {
                vote.setStatus("ENDED");
                voteRepository.save(vote);
            }
        }
    }

    private VoteDetailResponse mapToDetailResponse(GuildVote vote) {
        VoteDetailResponse response = new VoteDetailResponse();
        response.setVoteId(vote.getId());
        response.setGuildId(vote.getGuildId());
        response.setTitle(vote.getTitle());
        response.setDescription(vote.getDescription());
        response.setVoteType(vote.getVoteType().name());
        response.setStartTime(vote.getStartTime());
        response.setEndTime(vote.getEndTime());
        response.setRequiredApprovals(vote.getRequiredApprovals());
        response.setStatus(vote.getStatus());
        return response;
    }

    private VoteStatusResponse mapToVoteStatus(GuildVote vote, int currentVotes) {
        VoteStatusResponse response = new VoteStatusResponse();
        response.setVoteId(vote.getId());
        response.setActionType(vote.getVoteType().getCode());
        response.setCurrentVotes(currentVotes);
        response.setRequiredVotes(vote.getRequiredApprovals());
        response.setStatus(vote.getStatus());
        return response;
    }
}