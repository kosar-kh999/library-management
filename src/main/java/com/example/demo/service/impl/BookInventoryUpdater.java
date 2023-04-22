package com.example.demo.service.impl;

import com.example.demo.data.model.Book;
import com.example.demo.data.repository.BookRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.ZoneId;

@Component
public class BookInventoryUpdater {
    private final BookRepository bookRepository;

    public BookInventoryUpdater(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Scheduled(cron = "00 00 8 * * *", zone = "Asia/Tehran")
    public void updateBookInventory() {
        LocalTime currentTime = LocalTime.now(ZoneId.of("Asia/Tehran"));
        if (currentTime.getHour() == 8) {
            for (Book book : bookRepository.findAll()) {
                book.setInventory(10);
                bookRepository.save(book);
            }
        }
    }
}
