package xyz.bfdwdd.nshguildmanageb.extended.militarymerit.service.impl;

import lombok.*;
import org.springframework.stereotype.Service;
import xyz.bfdwdd.nshguildmanageb.extended.militarymerit.dto.request.*;
import xyz.bfdwdd.nshguildmanageb.extended.militarymerit.dto.response.*;
import xyz.bfdwdd.nshguildmanageb.extended.militarymerit.entity.MilitaryMerit;
import xyz.bfdwdd.nshguildmanageb.extended.militarymerit.exception.*;
import xyz.bfdwdd.nshguildmanageb.extended.militarymerit.repository.MilitaryMeritRepository;
import xyz.bfdwdd.nshguildmanageb.base.user.entity.User;
import xyz.bfdwdd.nshguildmanageb.base.user.repository.UserRepository;
import xyz.bfdwdd.nshguildmanageb.extended.militarymerit.service.MilitaryMeritService;
import xyz.bfdwdd.nshguildmanageb.functional.guild.entity.Guild;
import xyz.bfdwdd.nshguildmanageb.functional.guild.repository.GuildRepository;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class MilitaryMeritServiceImpl implements MilitaryMeritService {

    private final MilitaryMeritRepository militaryMeritRepository;
    private final UserRepository userRepository;
    private final GuildRepository guildRepository;

    @Override
    public MeritDetailResponse addMerit(AddMeritRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("用户不存在"));

        Guild guild = guildRepository.findById(request.getGuildId())
                .orElseThrow(() -> new RuntimeException("帮会不存在"));

        MilitaryMerit merit = militaryMeritRepository.findByUserIdAndGuildId(request.getUserId(), request.getGuildId())
                .orElseGet(() -> createNewMerit(request.getUserId(), request.getGuildId()));

        merit.setMeritPoints(merit.getMeritPoints() + request.getAmount());
        merit.setLastUpdated(LocalDateTime.now());

        return mapToMeritDetail(militaryMeritRepository.save(merit));
    }

    @Override
    public MeritDetailResponse deductMerit(DeductMeritRequest request) {
        MilitaryMerit merit = militaryMeritRepository.findByUserIdAndGuildId(request.getUserId(), request.getGuildId())
                .orElseThrow(() -> new MeritNotFoundException("军功点未找到"));

        if (merit.getMeritPoints() < request.getAmount()) {
            throw new InsufficientMilitaryMeritException("军功点不足");
        }

        merit.setMeritPoints(merit.getMeritPoints() - request.getAmount());
        merit.setLastUpdated(LocalDateTime.now());

        return mapToMeritDetail(militaryMeritRepository.save(merit));
    }

    @Override
    public void clearMerit(ClearMeritRequest request) {
        MilitaryMerit merit = militaryMeritRepository.findByUserIdAndGuildId(request.getUserId(), request.getGuildId())
                .orElseThrow(() -> new MeritNotFoundException("军功点未找到"));

        militaryMeritRepository.delete(merit);
    }

    @Override
    public MeritDetailResponse getMeritByUserAndGuild(String userId, Long guildId) {
        MilitaryMerit merit = militaryMeritRepository.findByUserIdAndGuildId(userId, guildId)
                .orElseThrow(() -> new MeritNotFoundException("军功点未找到"));

        return mapToMeritDetail(merit);
    }

    @Override
    public MeritSummaryResponse getMeritSummaryForUser(String userId) {
        List<MilitaryMerit> merits = militaryMeritRepository.findByUserId(userId);

        Map<Long, Integer> guildMerits = new HashMap<>();
        int total = 0;

        for (MilitaryMerit merit : merits) {
            guildMerits.put(merit.getGuildId(), merit.getMeritPoints());
            total += merit.getMeritPoints();
        }

        return new MeritSummaryResponse(userId, guildMerits, total);
    }

    private MilitaryMerit createNewMerit(String userId, Long guildId) {
        MilitaryMerit merit = new MilitaryMerit();
        merit.setUserId(userId);
        merit.setGuildId(guildId);
        merit.setMeritPoints(0);
        return militaryMeritRepository.save(merit);
    }

    private MeritDetailResponse mapToMeritDetail(MilitaryMerit merit) {
        MeritDetailResponse response = new MeritDetailResponse();
        response.setUserId(merit.getUserId());
        response.setGuildId(merit.getGuildId());
        response.setCurrentMeritPoints(merit.getMeritPoints());
        response.setLastUpdated(merit.getLastUpdated());
        return response;
    }
}