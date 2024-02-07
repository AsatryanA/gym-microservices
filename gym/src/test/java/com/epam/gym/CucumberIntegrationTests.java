package com.epam.gym;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features/",
        glue = {"com.epam.gym.cucumberGlue"})
public class CucumberIntegrationTests {
}
