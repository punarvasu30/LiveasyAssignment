package com.example.liveasy.Interface;

import com.example.liveasy.DTO.LoadDTO;
import com.example.liveasy.Model.Load;

import java.util.List;
import java.util.UUID;

public interface LoadService {
    Load createLoad(LoadDTO load);
    List<Load> getAllLoads(String shipperId, String truckType);
    Load getLoadById(UUID loadId);
    Load updateLoad(UUID loadId, LoadDTO updatedLoad);
    void deleteLoad(UUID loadId);
}