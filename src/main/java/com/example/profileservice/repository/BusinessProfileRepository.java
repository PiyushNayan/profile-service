package com.example.profileservice.repository;

import com.example.profileservice.entity.BusinessProfile;
import com.example.profileservice.entity.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessProfileRepository extends CrudRepository<BusinessProfile, String> {
    BusinessProfile getProfileByBusinessProfileId(String businessProfileId);
    int getPointsByBusinessProfileId(String businessProfileId);
}
