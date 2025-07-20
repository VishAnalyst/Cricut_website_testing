package testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class) //Run with cucumber class is important
@CucumberOptions(features = "Features", glue = "stepDefinitions") //Here we need tp copy path from the feature folder content root and gle it with step definitions.


public class TestRunner {

}