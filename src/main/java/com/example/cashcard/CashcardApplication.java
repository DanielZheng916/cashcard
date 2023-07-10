package com.example.cashcard;

import com.example.cashcard.model.CashCard;
import com.example.cashcard.repository.CashCardRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CashcardApplication {

    public static void main(String[] args) {
        SpringApplication.run(CashcardApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(CashCardRepository cashCardRepository) {
        return args -> {
            CashCard cashCard = CashCard.builder()
                    .amount(123.45)
                    .build();

            cashCardRepository.save(cashCard);
        };
    }
}
