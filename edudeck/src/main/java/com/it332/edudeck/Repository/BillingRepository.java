package com.it332.edudeck.Repository;

import com.it332.edudeck.Entity.BillingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingRepository extends JpaRepository<BillingEntity, Long> {
}
