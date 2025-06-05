package xyz.bfdwdd.nshguildmanageb.extended.militarymerit.service;

import xyz.bfdwdd.nshguildmanageb.extended.militarymerit.dto.request.AddMeritRequest;
import xyz.bfdwdd.nshguildmanageb.extended.militarymerit.dto.request.ClearMeritRequest;
import xyz.bfdwdd.nshguildmanageb.extended.militarymerit.dto.request.DeductMeritRequest;
import xyz.bfdwdd.nshguildmanageb.extended.militarymerit.dto.response.MeritDetailResponse;
import xyz.bfdwdd.nshguildmanageb.extended.militarymerit.dto.response.MeritSummaryResponse;

public interface MilitaryMeritService {

    MeritDetailResponse addMerit(AddMeritRequest request);

    MeritDetailResponse deductMerit(DeductMeritRequest request);

    void clearMerit(ClearMeritRequest request);

    MeritDetailResponse getMeritByUserAndGuild(String userId, Long guildId);

    MeritSummaryResponse getMeritSummaryForUser(String userId);
}