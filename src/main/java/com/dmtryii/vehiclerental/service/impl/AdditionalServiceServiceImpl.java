package com.dmtryii.vehiclerental.service.impl;

import com.dmtryii.vehiclerental.entity.AdditionalService;
import com.dmtryii.vehiclerental.helper.exception.AdditionalServiceNotFoundException;
import com.dmtryii.vehiclerental.repository.AdditionalServiceRepository;
import com.dmtryii.vehiclerental.service.AdditionalServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AdditionalServiceServiceImpl implements AdditionalServiceService {

    private final AdditionalServiceRepository additionalServiceRepository;

    @Override
    public AdditionalService getById(long id) {
        return additionalServiceRepository.findById(id).orElseThrow(
                () -> new AdditionalServiceNotFoundException("Not Found: " + id)
        );
    }

    @Override
    public AdditionalService create(AdditionalService additionalService) {
        return additionalServiceRepository.save(additionalService);
    }

    @Override
    public List<AdditionalService> getByIds(List<Long> ids) {
        List<AdditionalService> services = additionalServiceRepository.findAllById(ids);
        if (services.size() != ids.size()) {
            List<Long> foundIds = services.stream()
                    .map(AdditionalService::getId)
                    .toList();
            ids.removeAll(foundIds);
            throw new AdditionalServiceNotFoundException("Not Found: " + ids);
        }
        return additionalServiceRepository.findAllById(ids);
    }
}
