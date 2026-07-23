package com.cognizant.orm_learn;

import com.cognizant.orm_learn.model.Attempt;
import com.cognizant.orm_learn.model.AttemptOption;
import com.cognizant.orm_learn.model.AttemptQuestion;
import com.cognizant.orm_learn.model.Option;
import com.cognizant.orm_learn.service.AttemptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class OrmLearnApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);

    private static AttemptService attemptService;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);

        attemptService = context.getBean(AttemptService.class);

        testGetAttemptDetails();
    }

    private static void testGetAttemptDetails() {
        LOGGER.info("Start");

        Attempt attempt = attemptService.getAttempt(1, 1);

        LOGGER.debug("User: {}", attempt.getUser().getName());
        LOGGER.debug("Attempt Date: {}", attempt.getDate());

        for (AttemptQuestion attemptQuestion : attempt.getAttemptQuestions()) {
            LOGGER.debug("");
            LOGGER.debug("Question: {}", attemptQuestion.getQuestion().getText());

            int optionNumber = 1;

            for (AttemptOption attemptOption : attemptQuestion.getAttemptOptionList()) {
                Option option = attemptOption.getOption();

                LOGGER.debug(
                        "{} ) {}     {}     {}",
                        optionNumber,
                        option.getText(),
                        option.getScore(),
                        attemptOption.isSelected()
                );

                optionNumber++;
            }
        }

        LOGGER.info("End");
    }
}