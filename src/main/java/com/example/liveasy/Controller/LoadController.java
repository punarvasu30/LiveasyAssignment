package com.example.liveasy.Controller;

import com.example.liveasy.DTO.LoadDTO;
import com.example.liveasy.Interface.LoadService;
import com.example.liveasy.Model.Load;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/load")
@RequiredArgsConstructor
public class LoadController {
    private static final Logger logger = LoggerFactory.getLogger(LoadController.class);

    @Autowired
    private LoadService loadService;

    @PostMapping
    public ResponseEntity<Load> createLoad(@Valid @RequestBody LoadDTO loadDTO) {
        logger.info("POST /load request received");
        Load newLoad = loadService.createLoad(loadDTO);
        return new ResponseEntity<>(newLoad, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Load>> getAllLoads(
            @RequestParam(required = false) String shipperId,
            @RequestParam(required = false) String truckType) {
        logger.info("GET /load request received with shipperId: {}, truckType: {}", shipperId, truckType);
        List<Load> loads = loadService.getAllLoads(shipperId, truckType);
        return ResponseEntity.ok(loads);
    }

    @GetMapping("/{loadId}")
    public ResponseEntity<Load> getLoadById(@PathVariable UUID loadId) {
        logger.info("GET /load/{} request received", loadId);
        Load load = loadService.getLoadById(loadId);
        return ResponseEntity.ok(load);
    }

    @PutMapping("/{loadId}")
    public ResponseEntity<Load> updateLoad(
            @PathVariable UUID loadId,
            @Valid @RequestBody LoadDTO updatedLoadDTO) {
        logger.info("PUT /load/{} request received", loadId);
        Load updatedLoad = loadService.updateLoad(loadId, updatedLoadDTO);
        return ResponseEntity.ok(updatedLoad);
    }

    @DeleteMapping("/{loadId}")
    public ResponseEntity<Void> deleteLoad(@PathVariable UUID loadId) {
        logger.info("DELETE /load/{} request received", loadId);
        loadService.deleteLoad(loadId);
        return ResponseEntity.noContent().build();
    }
}