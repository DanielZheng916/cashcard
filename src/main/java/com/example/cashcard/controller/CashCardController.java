package com.example.cashcard.controller;

import com.example.cashcard.dto.CashCardResponse;
import com.example.cashcard.model.CashCard;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/cashcards")
@Slf4j
public class CashCardController {

    @GetMapping("/{id}")
    public CashCardResponse findById(@PathVariable Long id) {
        if (id.equals(99L)) {
            CashCard newCashCard = new CashCard(99L, 123.45);
            CashCardResponse cashCardResponse = CashCardResponse.builder()
                    .id(newCashCard.getId())
                    .amount(newCashCard.getAmount())
                    .build();
            return cashCardResponse;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cash card not found");
        }
    }
}
