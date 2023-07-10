package com.example.cashcard.service;

import com.example.cashcard.dto.CashCardRequest;
import com.example.cashcard.model.CashCard;
import com.example.cashcard.repository.CashCardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CashCardService {


    private final CashCardRepository cashCardRepository;

    public CashCard findCashCardById(Long id) {
        return cashCardRepository.findById(id)
                .orElse(null);
    }

    public CashCard create(CashCardRequest cashCardRequest) {
        CashCard cashCard = CashCard.builder()
                .amount(cashCardRequest.getAmount())
                .build();
        CashCard newCashCard = cashCardRepository.save(cashCard);
        return newCashCard;
    }
}
