package com.example.profileservice.service.impl;

import com.example.profileservice.dto.BusinessProfileDto;
import com.example.profileservice.dto.ProfileDto;
import com.example.profileservice.entity.BusinessProfile;
import com.example.profileservice.entity.Profile;
import com.example.profileservice.repository.BusinessProfileRepository;
import com.example.profileservice.repository.ProfileRepository;
import com.example.profileservice.service.BusinessProfileService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class BusinessProfileImpl implements BusinessProfileService {

    @Autowired
    private BusinessProfileRepository businessProfileRepository;

    public BusinessProfile getProfileById(String profileId) {
        return businessProfileRepository.getProfileByBusinessProfileId(profileId);
    }

    @Override
    public Boolean addBusinessProfile(BusinessProfileDto businessProfileDto ) {
        BusinessProfile businessProfile = new BusinessProfile();
        BeanUtils.copyProperties(businessProfileDto, businessProfile);
        String profileId = UUID.randomUUID().toString();
        businessProfile.setBusinessProfileId(profileId);
        BusinessProfile newProfile = businessProfileRepository.save(businessProfile);
        return Objects.nonNull(newProfile);
    }

    @Override
    public Boolean updateProfileById(String businessProfileId, BusinessProfileDto businessProfileDto) {
        Optional<BusinessProfile> optionalProfile = businessProfileRepository.findById(businessProfileId);
        if(optionalProfile.isPresent()){
            BusinessProfile existingProfile = optionalProfile.get();
            BeanUtils.copyProperties(businessProfileDto, existingProfile);
            businessProfileRepository.save(existingProfile);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public Profile deleteProfileById(String businessProfileId) {
        businessProfileRepository.deleteById(businessProfileId);
        return null;
    }

    @Override
    public int getPointsByBusinessProfileId(String businessProfileId) {
        return businessProfileRepository.getPointsByBusinessProfileId(businessProfileId);
    }
}
