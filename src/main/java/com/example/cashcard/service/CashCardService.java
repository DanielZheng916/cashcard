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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public void update(Long id, double amount, String name) {
        CashCard cashCard = cashCardRepository.findByIdAndOwner(id, name);
        if (cashCard == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cash card not found");
        }
        CashCard updatedCashCard = new CashCard(cashCard.getId(), amount, name);
        cashCardRepository.save(updatedCashCard);
    }

    public void delete(Long id, String name) {
        CashCard cashCard = cashCardRepository.findByIdAndOwner(id, name);
        if (cashCard == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cash card not found");
        }
        cashCardRepository.deleteById(id);
    }
}
