package xyz.bfdwdd.nshguildmanageb.functional.guild.service.impl;

import lombok.*;
import org.springframework.stereotype.Service;
import xyz.bfdwdd.nshguildmanageb.functional.constant.enums.RoleHierarchy;
import xyz.bfdwdd.nshguildmanageb.functional.guild.dto.request.*;
import xyz.bfdwdd.nshguildmanageb.functional.guild.dto.response.*;
import xyz.bfdwdd.nshguildmanageb.functional.guild.entity.Guild;
import xyz.bfdwdd.nshguildmanageb.functional.guild.entity.GuildMember;
import xyz.bfdwdd.nshguildmanageb.functional.guild.exception.*;
import xyz.bfdwdd.nshguildmanageb.functional.guild.repository.GuildMemberRepository;
import xyz.bfdwdd.nshguildmanageb.functional.guild.repository.GuildRepository;
import xyz.bfdwdd.nshguildmanageb.functional.guild.service.GuildService;
import xyz.bfdwdd.nshguildmanageb.base.user.entity.User;
import xyz.bfdwdd.nshguildmanageb.base.user.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GuildServiceImpl implements GuildService {

    private final GuildRepository guildRepository;
    private final GuildMemberRepository guildMemberRepository;
    private final UserRepository userRepository;

    @Override
    public GuildResponse createGuild(CreateGuildRequest request) {
        Guild guild = new Guild();
        guild.setName(request.getName());
        guild.setDescription(request.getDescription());
        guild.setOwnerId(request.getOwnerId());
        guild.setMaxMembers(request.getMaxMembers());

        Guild savedGuild = guildRepository.save(guild);

        GuildMember owner = new GuildMember();
        owner.setUserId(request.getOwnerId());
        owner.setRole(RoleHierarchy.GUILD_SUPER_ADMIN); // 初始角色为超级管理员
        owner.setGuild(savedGuild);
        guildMemberRepository.save(owner);

        return mapToGuildResponse(savedGuild, List.of(mapToGuildMemberResponse(owner)));
    }

    @Override
    public GuildResponse joinGuild(JoinGuildRequest request) {
        Guild guild = guildRepository.findById(request.getGuildId())
                .orElseThrow(() -> new GuildNotFoundException("Guild not found"));

        if (guild.getStatus().equals("DISSOLVED")) {
            throw new GuildOperationException("Guild is dissolved");
        }

        if (guild.getMembers().size() >= guild.getMaxMembers()) {
            throw new GuildOperationException("Guild is full");
        }

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (guildMemberRepository.existsByUserIdAndGuildId(request.getUserId(), request.getGuildId())) {
            throw new GuildOperationException("User already in the guild");
        }

        GuildMember member = new GuildMember();
        member.setUserId(request.getUserId());
        member.setRole(RoleHierarchy.MEMBER);
        member.setGuild(guild);
        guildMemberRepository.save(member);

        return mapToGuildResponse(guild, guild.getMembers().stream()
                .map(this::mapToGuildMemberResponse).collect(Collectors.toList()));
    }

    @Override
    public GuildResponse updateGuild(Long guildId, UpdateGuildRequest request) {
        Guild guild = guildRepository.findById(guildId)
                .orElseThrow(() -> new GuildNotFoundException("Guild not found"));

        guild.setName(request.getName());
        guild.setDescription(request.getDescription());
        guild.setMaxMembers(request.getMaxMembers());

        return mapToGuildResponse(guildRepository.save(guild), guild.getMembers().stream()
                .map(this::mapToGuildMemberResponse).collect(Collectors.toList()));
    }

    @Override
    public GuildResponse getGuildById(Long guildId) {
        Guild guild = guildRepository.findById(guildId)
                .orElseThrow(() -> new GuildNotFoundException("Guild not found"));

        return mapToGuildResponse(guild, guild.getMembers().stream()
                .map(this::mapToGuildMemberResponse).collect(Collectors.toList()));
    }

    @Override
    public List<GuildResponse> getAllGuilds() {
        return guildRepository.findAll().stream()
                .map(guild -> mapToGuildResponse(guild, guild.getMembers().stream()
                        .map(this::mapToGuildMemberResponse).collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    @Override
    public List<GuildMemberResponse> getMembersByGuild(Long guildId) {
        return guildMemberRepository.findByGuildId(guildId).stream()
                .map(this::mapToGuildMemberResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void transferOwnership(TransferOwnerRequest request) {
        Guild guild = guildRepository.findById(request.getGuildId())
                .orElseThrow(() -> new GuildNotFoundException("Guild not found"));

        GuildMember currentOwner = (GuildMember) guildMemberRepository.findByUserIdAndGuildId(request.getCurrentOwnerId(), request.getGuildId())
                .orElseThrow(() -> new GuildOperationException("Current owner not found"));

        if (!currentOwner.getRole().equals(RoleHierarchy.GUILD_SUPER_ADMIN)) {
            throw new GuildOperationException("Only guild super admin can transfer ownership");
        }

        GuildMember newOwner = (GuildMember) guildMemberRepository.findByUserIdAndGuildId(request.getNewOwnerId(), request.getGuildId())
                .orElseThrow(() -> new GuildOperationException("New owner not found"));

        currentOwner.setRole(RoleHierarchy.MEMBER);
        newOwner.setRole(RoleHierarchy.GUILD_SUPER_ADMIN);

        guildMemberRepository.save(currentOwner);
        guildMemberRepository.save(newOwner);
    }

    @Override
    public void disbandGuild(Long guildId) {
        Guild guild = guildRepository.findById(guildId)
                .orElseThrow(() -> new GuildNotFoundException("Guild not found"));

        guild.setStatus("DISSOLVED");
        guildRepository.save(guild);
    }

    private GuildResponse mapToGuildResponse(Guild guild, List<GuildMemberResponse> members) {
        GuildResponse response = new GuildResponse();
        response.setId(guild.getId());
        response.setName(guild.getName());
        response.setDescription(guild.getDescription());
        response.setCreatedAt(guild.getCreatedAt());
        response.setOwnerId(guild.getOwnerId());
        response.setMaxMembers(guild.getMaxMembers());
        response.setStatus(guild.getStatus());
        response.setMembers(members);
        return response;
    }

    private GuildMemberResponse mapToGuildMemberResponse(GuildMember member) {
        GuildMemberResponse response = new GuildMemberResponse();
        response.setUserId(member.getUserId());
        response.setJoinedAt(member.getJoinedAt());
        response.setRole(member.getRole().name());
        return response;
    }
}