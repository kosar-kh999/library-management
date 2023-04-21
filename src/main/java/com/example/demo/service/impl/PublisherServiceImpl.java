package com.example.demo.service.impl;

import com.example.demo.data.model.Publisher;
import com.example.demo.data.repository.PublisherRepository;
import com.example.demo.service.PublisherService;
import com.example.demo.util.exception.ExistPublisher;
import com.example.demo.util.exception.NotFoundPublisher;
import org.springframework.stereotype.Service;

@Service
public class PublisherServiceImpl implements PublisherService {
    private final PublisherRepository publisherRepository;

    public PublisherServiceImpl(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public void addPublisher(Publisher publisher) {
        if (publisherRepository.findByName(publisher.getName()).isPresent())
            throw new ExistPublisher("Publisher is exist");
        publisherRepository.save(publisher);
    }

    public Publisher getPublisherById(Long id) {
        return publisherRepository.findById(id).orElseThrow(() -> new NotFoundPublisher("This publisher is not found"));
    }
}
