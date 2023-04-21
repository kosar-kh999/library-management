package com.example.demo.service;

import com.example.demo.data.model.Publisher;

public interface PublisherService {
    void addPublisher(Publisher publisher);

    Publisher getPublisherById(Long id);
}
