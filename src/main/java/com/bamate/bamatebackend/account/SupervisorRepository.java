package com.bamate.bamatebackend.account;

import com.bamate.bamatebackend.account.models.Supervisor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupervisorRepository extends JpaRepository <Supervisor, Long> {
}
