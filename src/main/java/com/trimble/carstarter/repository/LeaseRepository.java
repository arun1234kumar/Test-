package com.trimble.carstarter.repository;

import com.trimble.carstarter.model.Lease;
import com.trimble.carstarter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaseRepository extends JpaRepository<Lease, Long> {
    List<Lease> findByCustomer(User customer);
}
