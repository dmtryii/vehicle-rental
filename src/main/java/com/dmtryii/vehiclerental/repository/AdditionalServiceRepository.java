package com.dmtryii.vehiclerental.repository;

import com.dmtryii.vehiclerental.entity.AdditionalService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdditionalServiceRepository extends JpaRepository<AdditionalService, Long> {
}
