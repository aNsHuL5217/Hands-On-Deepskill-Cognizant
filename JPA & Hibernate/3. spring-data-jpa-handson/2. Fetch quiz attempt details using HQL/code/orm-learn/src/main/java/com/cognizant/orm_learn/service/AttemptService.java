package com.cognizant.orm_learn.service;

import com.cognizant.orm_learn.model.Attempt;
import com.cognizant.orm_learn.repository.AttemptRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttemptService {

    @Autowired
    private AttemptRepository repository;

    @Transactional
    public Attempt getAttempt(int userId,int attemptId){
        return repository.getAttempt(userId,attemptId);
    }
}