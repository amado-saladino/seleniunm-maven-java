package runner;

import cucumber.api.CucumberOptions;
import tests.TestBase;


@CucumberOptions(features={"src//test//java//features"}
,glue={"steps"}
,plugin={"pretty","html:target/cucumber"}
,tags={"@contact"})
public class TestRunner extends TestBase {

}