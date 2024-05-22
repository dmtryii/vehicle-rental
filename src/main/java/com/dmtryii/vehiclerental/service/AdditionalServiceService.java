package com.dmtryii.vehiclerental.service;

import com.dmtryii.vehiclerental.entity.AdditionalService;

import java.util.List;

public interface AdditionalServiceService {
    AdditionalService getById(long id);
    AdditionalService create(AdditionalService additionalService);
    List<AdditionalService> getByIds(List<Long> ids);
}
