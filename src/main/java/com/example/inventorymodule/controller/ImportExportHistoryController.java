package com.example.inventorymodule.controller;

import com.example.inventorymodule.service.importExportHistory.ImportExportHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("api/v1/importExportHistory")
public class ImportExportHistoryController {
    @Autowired
    ImportExportHistoryService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok().body(
                service.findAll()
        );
    }

    @RequestMapping(method = RequestMethod.GET, path = "provider")
    public ResponseEntity<?> findByProvider(@RequestParam(name = "id") int providerId){
        return ResponseEntity.ok().body(
                service.findByProvider(providerId)
        );
    }
}
