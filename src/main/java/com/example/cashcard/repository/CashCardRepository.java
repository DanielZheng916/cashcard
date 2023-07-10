package com.example.cashcard.repository;

import com.example.cashcard.model.CashCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashCardRepository extends JpaRepository<CashCard, Long> {
    CashCard findByIdAndOwner(Long id, String owner);
    Page<CashCard> findByOwner(String owner, PageRequest amount);
}
