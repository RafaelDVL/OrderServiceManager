package com.rafaelDvl.orderservicemanager.repositories;

import com.rafaelDvl.orderservicemanager.domain.OS;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OsRepository extends JpaRepository<OS, Integer> {
}
