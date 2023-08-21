package com.ST.billeteraVirtual.repositories;

import com.ST.billeteraVirtual.entities.Billetera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BilleteraRepository extends JpaRepository <Billetera, Long> {
}
