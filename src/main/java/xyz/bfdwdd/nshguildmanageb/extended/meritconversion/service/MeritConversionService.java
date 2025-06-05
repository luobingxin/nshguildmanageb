package xyz.bfdwdd.nshguildmanageb.extended.meritconversion.service;

import xyz.bfdwdd.nshguildmanageb.extended.meritconversion.dto.request.EnableConversionRequest;
import xyz.bfdwdd.nshguildmanageb.extended.meritconversion.dto.request.SetConversionRateRequest;
import xyz.bfdwdd.nshguildmanageb.extended.meritconversion.dto.response.ConversionRateResponse;

public interface MeritConversionService {
    ConversionRateResponse setRate(SetConversionRateRequest request);
    ConversionRateResponse enableConversion(EnableConversionRequest request);
    ConversionRateResponse getRateByGuild(String guildId);
}