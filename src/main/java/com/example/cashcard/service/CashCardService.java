package com.example.cashcard.service;

import com.example.cashcard.dto.CashCardRequest;
import com.example.cashcard.model.CashCard;
import com.example.cashcard.repository.CashCardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CashCardService {


    private final CashCardRepository cashCardRepository;

    public CashCard findCashCardById(Long id, String owner) {
        return cashCardRepository.findByIdAndOwner(id, owner);
    }

    public List<CashCard> findAll(Pageable pageable, String owner) {
        if (pageable != null) {
            return cashCardRepository.findByOwner(owner,
                    PageRequest.of(
                            pageable.getPageNumber(),
                            pageable.getPageSize(),
                            pageable.getSortOr(Sort.by(Sort.Direction.ASC, "amount"))
                    )
            ).getContent();
        }
        else {
            return cashCardRepository.findAll();
        }
    }

    public CashCard create(CashCardRequest cashCardRequest, String owner) {
        CashCard cashCard = CashCard.builder()
                .amount(cashCardRequest.getAmount())
                .owner(owner)
                .build();
        CashCard newCashCard = cashCardRepository.save(cashCard);
        return newCashCard;
    }
}
