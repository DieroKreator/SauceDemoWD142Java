package test.java.stepsPO;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features =  { "src/test/resources/featuresPO" },    // caminho para features
    glue =      { "stepsPO" },                          // caminho para a automação
    dryRun = false,                                     // Exibição do log
    monochrome = true                                   // Detalhes do log
)
public class Runner {
    
}