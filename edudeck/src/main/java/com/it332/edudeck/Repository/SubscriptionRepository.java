package com.it332.edudeck.Repository;

import com.it332.edudeck.Entity.SubscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.concurrent.Flow;

@Repository
public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, Long> {
}
