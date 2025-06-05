package xyz.bfdwdd.nshguildmanageb.functional.guild.service;

import xyz.bfdwdd.nshguildmanageb.functional.guild.dto.request.CreateGuildRequest;
import xyz.bfdwdd.nshguildmanageb.functional.guild.dto.request.JoinGuildRequest;
import xyz.bfdwdd.nshguildmanageb.functional.guild.dto.request.TransferOwnerRequest;
import xyz.bfdwdd.nshguildmanageb.functional.guild.dto.request.UpdateGuildRequest;
import xyz.bfdwdd.nshguildmanageb.functional.guild.dto.response.GuildResponse;
import xyz.bfdwdd.nshguildmanageb.functional.guild.dto.response.GuildMemberResponse;

import java.util.List;

public interface GuildService {

    GuildResponse createGuild(CreateGuildRequest request);

    GuildResponse joinGuild(JoinGuildRequest request);

    GuildResponse updateGuild(Long guildId, UpdateGuildRequest request);

    GuildResponse getGuildById(Long guildId);

    List<GuildResponse> getAllGuilds();

    List<GuildMemberResponse> getMembersByGuild(Long guildId);

    void transferOwnership(TransferOwnerRequest request);

    void disbandGuild(Long guildId);
}