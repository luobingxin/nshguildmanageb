package xyz.bfdwdd.nshguildmanageb.extended.vote.service;

import xyz.bfdwdd.nshguildmanageb.extended.vote.dto.request.InitiateVoteRequest;
import xyz.bfdwdd.nshguildmanageb.extended.vote.dto.request.SubmitVoteRequest;
import xyz.bfdwdd.nshguildmanageb.extended.vote.dto.request.UpdateVoteRequest;
import xyz.bfdwdd.nshguildmanageb.extended.vote.dto.response.VoteDetailResponse;
import xyz.bfdwdd.nshguildmanageb.extended.vote.dto.response.VoteStatusResponse;

import java.util.List;

public interface VoteService {

    VoteDetailResponse initiateVote(InitiateVoteRequest request);

    VoteStatusResponse submitVote(SubmitVoteRequest request);

    VoteDetailResponse updateVote(Long voteId, UpdateVoteRequest request);

    VoteDetailResponse getVoteById(Long voteId);

    List<VoteDetailResponse> getAllVotesByGuild(Long guildId);

    void endVoteIfExpired();
}