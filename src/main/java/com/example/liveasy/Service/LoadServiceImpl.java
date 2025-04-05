package com.example.liveasy.Service;

import com.example.liveasy.DTO.FacilityDTO;
import com.example.liveasy.DTO.LoadDTO;
import com.example.liveasy.Exception.ResourceNotFoundException;
import com.example.liveasy.Interface.LoadService;
import com.example.liveasy.Model.Facility;
import com.example.liveasy.Model.Load;
import com.example.liveasy.Model.LoadStatus;
import com.example.liveasy.Repository.LoadRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LoadServiceImpl implements LoadService {

    private static final Logger logger = LoggerFactory.getLogger(LoadServiceImpl.class);

    private final LoadRepository loadRepository;

    @Override
    @Transactional
    public Load createLoad(LoadDTO loadDTO) {
        logger.info("Creating new load for shipper: {}", loadDTO.getShipperId());

        Facility facility = Facility.builder()
                .loadingPoint(loadDTO.getFacility().getLoadingPoint())
                .unloadingPoint(loadDTO.getFacility().getUnloadingPoint())
                .loadingDate(loadDTO.getFacility().getLoadingDate())
                .unloadingDate(loadDTO.getFacility().getUnloadingDate())
                .build();

        Load load = Load.builder()
                .shipperId(loadDTO.getShipperId())
                .facility(facility)
                .productType(loadDTO.getProductType())
                .truckType(loadDTO.getTruckType())
                .noOfTrucks(loadDTO.getNoOfTrucks())
                .weight(loadDTO.getWeight())
                .comment(loadDTO.getComment())
                .datePosted(Timestamp.from(Instant.now()))
                .status(LoadStatus.POSTED)
                .build();

        Load savedLoad = loadRepository.save(load);
        logger.info("Load created successfully with ID: {}", savedLoad.getId());
        return savedLoad;
    }

    @Override
    public List<Load> getAllLoads(String shipperId, String truckType) {
        logger.info("Fetching loads with filters - shipperId: {}, truckType: {}", shipperId, truckType);

        if(shipperId != null && truckType != null) {
            return loadRepository.findByShipperIdAndTruckType(shipperId, truckType);
        } else if (shipperId != null) {
            return loadRepository.findByShipperId(shipperId);
        } else if (truckType != null) {
            return loadRepository.findByTruckType(truckType);
        }

        logger.info("Fetching all loads");
        return loadRepository.findAll();
    }

    @Override
    public Load getLoadById(UUID loadId) {
        logger.info("Fetching load by ID: {}", loadId);
        return loadRepository.findById(loadId)
                .orElseThrow(() -> new ResourceNotFoundException("Load", "id", loadId));
    }

    @Override
    @Transactional
    public Load updateLoad(UUID loadId, LoadDTO updatedLoadDTO) {
        logger.info("Updating load with ID: {}", loadId);

        Load existingLoad = getLoadById(loadId);

        if (updatedLoadDTO.getProductType() != null) {
            existingLoad.setProductType(updatedLoadDTO.getProductType());
        }

        if (updatedLoadDTO.getTruckType() != null) {
            existingLoad.setTruckType(updatedLoadDTO.getTruckType());
        }

        if (updatedLoadDTO.getNoOfTrucks() != null) {
            existingLoad.setNoOfTrucks(updatedLoadDTO.getNoOfTrucks());
        }

        if (updatedLoadDTO.getWeight() != null) {
            existingLoad.setWeight(updatedLoadDTO.getWeight());
        }

        if (updatedLoadDTO.getComment() != null) {
            existingLoad.setComment(updatedLoadDTO.getComment());
        }

        if (updatedLoadDTO.getFacility() != null) {
            FacilityDTO facilityDTO = updatedLoadDTO.getFacility();
            Facility facility = existingLoad.getFacility();

            if (facilityDTO.getLoadingPoint() != null) {
                facility.setLoadingPoint(facilityDTO.getLoadingPoint());
            }

            if (facilityDTO.getUnloadingPoint() != null) {
                facility.setUnloadingPoint(facilityDTO.getUnloadingPoint());
            }

            if (facilityDTO.getLoadingDate() != null) {
                facility.setLoadingDate(facilityDTO.getLoadingDate());
            }

            if (facilityDTO.getUnloadingDate() != null) {
                facility.setUnloadingDate(facilityDTO.getUnloadingDate());
            }

            existingLoad.setFacility(facility);
        }

        Load updatedLoad = loadRepository.save(existingLoad);
        logger.info("Load updated successfully: {}", updatedLoad.getId());
        return updatedLoad;
    }

    @Override
    @Transactional
    public void deleteLoad(UUID loadId) {
        logger.info("Deleting load with ID: {}", loadId);

        Load load = getLoadById(loadId);
        loadRepository.delete(load);
        logger.info("Load deleted successfully: {}", loadId);
    }
}
