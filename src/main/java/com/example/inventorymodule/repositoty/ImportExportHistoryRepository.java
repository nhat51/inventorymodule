package com.example.inventorymodule.repositoty;

import com.example.inventorymodule.entity.ImportExportHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImportExportHistoryRepository extends JpaRepository<ImportExportHistory,Integer> {
    List<ImportExportHistory> findImportExportHistoriesByProviderId(int providerId);
}
