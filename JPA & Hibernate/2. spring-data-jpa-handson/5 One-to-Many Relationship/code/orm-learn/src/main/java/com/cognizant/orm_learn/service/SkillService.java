package com.cognizant.orm_learn.service;

import com.cognizant.orm_learn.model.Skill;
import com.cognizant.orm_learn.repository.SkillRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    @Transactional
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    @Transactional
    public Skill getSkill(int id) {
        return skillRepository.findById(id).orElse(null);
    }

    @Transactional
    public void saveSkill(Skill skill) {
        skillRepository.save(skill);
    }

    // Hands-on 4 method
    @Transactional
    public Skill get(int id) {
        return skillRepository.findById(id).get();
    }

    @Transactional
    public void save(Skill skill) {
        skillRepository.save(skill);
    }
}