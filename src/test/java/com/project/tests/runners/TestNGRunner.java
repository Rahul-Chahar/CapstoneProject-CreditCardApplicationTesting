package com.project.tests.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
  features = "src/test/resources/features",
  glue = {"com.project.tests.stepdefinitions", "com.project.framework.base"},
  plugin = {"pretty","html:output/cucumber-html-report.html","json:output/cucumber.json"}
)
public class TestNGRunner extends AbstractTestNGCucumberTests {
}
