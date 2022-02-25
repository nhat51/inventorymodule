package com.example.inventorymodule.service.importExportHistory;

import com.example.inventorymodule.entity.ImportExportHistory;
import com.example.inventorymodule.repositoty.ImportExportHistoryRepository;
import com.example.inventorymodule.response.ResponseApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImportExportHistoryServiceImpl implements ImportExportHistoryService{

    @Autowired
    ImportExportHistoryRepository importExportHistoryRepository;

    @Override
    public ResponseApi findAll() {
        List<ImportExportHistory> data = importExportHistoryRepository.findAll();
        return new ResponseApi(HttpStatus.OK,"success",data);
    }

    @Override
    public ResponseApi findByProvider(int providerId) {
        List<ImportExportHistory> data = importExportHistoryRepository.findImportExportHistoriesByProviderId( providerId);
        return new ResponseApi(HttpStatus.OK,"success",data);
    }

    @Override
    public ResponseApi create(ImportExportHistory importExportHistory) {
        return new ResponseApi(HttpStatus.CREATED,"created",importExportHistoryRepository.save(importExportHistory));
    }

    @Override
    public ResponseApi delete(int id) {
        return null;
    }
}
