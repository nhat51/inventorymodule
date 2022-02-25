package com.example.inventorymodule.repositoty;

import com.example.inventorymodule.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderRepository extends JpaRepository<Provider,Integer> {
}
