package com.springBank.bankacc.query.api.repositories;

import com.springBank.bankacc.core.models.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcountRepository extends JpaRepository<BankAccount, String> {
}
