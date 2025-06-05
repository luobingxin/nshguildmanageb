package xyz.bfdwdd.nshguildmanageb.extended.meritconversion.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.bfdwdd.nshguildmanageb.extended.meritconversion.dto.request.EnableConversionRequest;
import xyz.bfdwdd.nshguildmanageb.extended.meritconversion.dto.request.SetConversionRateRequest;
import xyz.bfdwdd.nshguildmanageb.extended.meritconversion.dto.response.ConversionRateResponse;
import xyz.bfdwdd.nshguildmanageb.extended.meritconversion.entity.ConversionRate;
import xyz.bfdwdd.nshguildmanageb.extended.meritconversion.exception.ConversionNotAllowedException;
import xyz.bfdwdd.nshguildmanageb.extended.meritconversion.repository.ConversionRateRepository;
import xyz.bfdwdd.nshguildmanageb.extended.meritconversion.service.MeritConversionService;

@Service
@RequiredArgsConstructor
public class MeritConversionServiceImpl implements MeritConversionService {

    private final ConversionRateRepository conversionRateRepository;

    @Override
    public ConversionRateResponse setRate(SetConversionRateRequest request) {
        if (!"SYSTEM_ADMIN".equals(request.getOperatorId()) && !"GUILD_LEADER".equals(request.getOperatorId())) {
            throw new ConversionNotAllowedException("无权设置转换率");
        }

        ConversionRate rate = new ConversionRate();
        rate.setId(request.getGuildId());
        rate.setMeritToLobiRate(request.getMeritToLobiRate());
        rate.setIncomingRate(request.getIncomingRate());
        rate.setOutgoingRate(request.getOutgoingRate());
        rate.setEnabled(false); // 新增的转换率默认关闭

        return mapToResponse(conversionRateRepository.save(rate));
    }

    @Override
    public ConversionRateResponse enableConversion(EnableConversionRequest request) {
        ConversionRate rate = conversionRateRepository.findByGuildId(request.getGuildId())
                .orElseThrow(() -> new RuntimeException("转换率未找到"));

        rate.setEnabled(request.getEnable());
        return mapToResponse(conversionRateRepository.save(rate));
    }

    @Override
    public ConversionRateResponse getRateByGuild(String guildId) {
        ConversionRate rate = conversionRateRepository.findByGuildId(guildId)
                .orElseThrow(() -> new RuntimeException("转换率未找到"));
        return mapToResponse(rate);
    }

    private ConversionRateResponse mapToResponse(ConversionRate rate) {
        ConversionRateResponse response = new ConversionRateResponse();
        response.setGuildId(rate.getId());
        response.setMeritToLobiRate(rate.getMeritToLobiRate());
        response.setIncomingRate(rate.getIncomingRate());
        response.setOutgoingRate(rate.getOutgoingRate());
        response.setEnabled(rate.getEnabled());
        return response;
    }
}