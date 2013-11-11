package net.continuumsecurity.web;

import net.continuumsecurity.web.steps.AutomatedScanningSteps;
import org.jbehave.core.model.ExamplesTable;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AutomatedScanningTest {
    protected AutomatedScanningSteps automatedScanningSteps = new AutomatedScanningSteps();

    @BeforeClass
    public void setUp() {
        this.automatedScanningSteps.createScanner();
    }

    @BeforeTest
    public void beforeScenario() {
        this.automatedScanningSteps.createScanner();
    }

    @Test
    public void testActiveSecurityScan() throws Exception {
        String workingDirectory = System.getProperty("user.dir");
        ExamplesTable falsePositives = new ExamplesTable(NgUtils.createStringFromJBehaveTable(workingDirectory + "/src/main/stories/false_positives.table"));

        this.automatedScanningSteps.navigateApp();
        this.automatedScanningSteps.runScanner();
        this.automatedScanningSteps.removeFalsePositives(falsePositives);
        this.automatedScanningSteps.checkHighRiskVulnerabilities();
        this.automatedScanningSteps.checkMediumRiskVulnerabilities();
    }

}
