package com.example.cashcard.controller;

import com.example.cashcard.dto.CashCardRequest;
import com.example.cashcard.dto.CashCardResponse;
import com.example.cashcard.model.CashCard;
import com.example.cashcard.repository.CashCardRepository;
import com.example.cashcard.service.CashCardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/cashcards")
@Slf4j
@RequiredArgsConstructor
public class CashCardController {

    private final CashCardService cashCardService;

    @GetMapping("/{id}")
    public CashCardResponse findById(@PathVariable Long id, Principal principal) {
        CashCard cashCard = cashCardService.findCashCardById(id, principal.getName());
        if (cashCard != null) {
            CashCardResponse cashCardResponse = CashCardResponse.builder()
                    .id(cashCard.getId())
                    .amount(cashCard.getAmount())
                    .build();
            return cashCardResponse;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cash card not found");
        }
    }

    @GetMapping
    public List<CashCardResponse> findAllCashCard(Pageable pageable, Principal principal) {
        List<CashCardResponse> cashCards = cashCardService.findAll(pageable, principal.getName()).stream()
                .map(cashCard -> CashCardResponse.builder()
                        .id(cashCard.getId())
                        .amount(cashCard.getAmount())
                        .build())
                .toList();
        return cashCards;
    }

    @PostMapping
    private CashCardResponse createCashCard(@RequestBody CashCardRequest cashCardRequest, Principal principal) {
        CashCard newCashCard = cashCardService.create(cashCardRequest, principal.getName());
        CashCardResponse cashCardResponse = CashCardResponse.builder()
                .id(newCashCard.getId())
                .amount(newCashCard.getAmount())
                .build();
        return cashCardResponse;
    }
}
