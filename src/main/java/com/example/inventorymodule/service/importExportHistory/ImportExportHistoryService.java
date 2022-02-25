package com.example.inventorymodule.service.importExportHistory;

import com.example.inventorymodule.entity.ImportExportHistory;
import com.example.inventorymodule.response.ResponseApi;

public interface ImportExportHistoryService {
    ResponseApi findAll();
    ResponseApi findByProvider(int providerId);
    ResponseApi create(ImportExportHistory importExportHistory);
    ResponseApi delete(int id);
}
