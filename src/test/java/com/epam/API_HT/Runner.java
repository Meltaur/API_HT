package com.epam.API_HT;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.epam.API_HT.stepdefs"}
)
public class Runner extends AbstractTestNGCucumberTests {
}
