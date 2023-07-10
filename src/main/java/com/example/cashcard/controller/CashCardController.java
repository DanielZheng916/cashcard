package com.example.cashcard.controller;

import com.example.cashcard.dto.CashCardResponse;
import com.example.cashcard.model.CashCard;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cashcards")
public class CashCardController {

    @GetMapping("/{id}")
    public CashCardResponse findById() {
        CashCard newCashCard = new CashCard(1000L, 0.0);
        CashCardResponse cashCardResponse = CashCardResponse.builder()
                .id(newCashCard.getId())
                .amount(newCashCard.getAmount())
                .build();
        return cashCardResponse;
    }
}
