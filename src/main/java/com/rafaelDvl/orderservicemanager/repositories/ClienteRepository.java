package com.rafaelDvl.orderservicemanager.repositories;

import com.rafaelDvl.orderservicemanager.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
