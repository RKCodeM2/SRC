package com.ahq.listeners;

import com.ahq.objects.ExtentReportManager;
import com.ahq.addons.stringManipulation;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.qmetry.qaf.automation.step.TestStep;
import com.qmetry.qaf.automation.step.client.Scenario;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.ArrayList;
import java.util.List;

import static com.qmetry.qaf.automation.core.ConfigurationManager.getBundle;

public class testListener implements ITestListener {

    public static ExtentTest extentTest;
    private static final List<String> completeStepDescriptionList = new ArrayList<>();

    @Override
    public void onTestStart(ITestResult iTestResult) {
        Object instance = iTestResult.getInstance();
        if (instance instanceof Scenario) {
            Scenario scenario = (Scenario) instance;
            for (TestStep step : scenario.getSteps()) {
                completeStepDescriptionList.add(step.getDescription());
            }
            extentTest = ExtentReportManager.getInstance().createTest(iTestResult.getMethod().getMethodName());
        } else {
            System.out.println("The object is not an instance of a feature Scenario. Extent Report will not be created.");
        }
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        if (extentTest != null) {
            logReportSteps();
            extentTest.pass(MarkupHelper.createLabel("Test Passed", ExtentColor.GREEN));
        }
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        if (extentTest != null) {
            logReportSteps();
            extentTest.fail(MarkupHelper.createLabel("Test Failed", ExtentColor.RED));
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        if (extentTest != null) {
            logReportSteps();
            Throwable throwable = iTestResult.getThrowable();
            if (throwable != null) {
                extentTest.skip(throwable);
            } else {
                extentTest.skip(MarkupHelper.createLabel("Test Skipped", ExtentColor.ORANGE));
            }
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        if (iTestContext.getName().toLowerCase().contains("pipeline")) {
            System.out.println("Executing Pipeline Smoke Test suite...");
            getBundle().setProperty("suite", "pipeline");
        } else if (iTestContext.getName().toLowerCase().contains("smoke")) {
            System.out.println("Executing Smoke Test suite...");
            getBundle().setProperty("suite", "smoke");
        } else if (iTestContext.getName().toLowerCase().contains("regression")) {
            System.out.println("Executing Regression Test suite...");
            getBundle().setProperty("suite", "regression");
        } else {
            System.out.println("Executing Test suite...");
        }
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        if (extentTest != null) {
            ExtentReportManager.getInstance().flush();
        }
    }

    private void logReportSteps() {
        if (!stepListener.executedTestSteps.isEmpty() && stepListener.executedTestSteps.size() <= completeStepDescriptionList.size()) {
            completeStepDescriptionList.subList(0, stepListener.executedTestSteps.size()).clear();
        }
        if (!completeStepDescriptionList.isEmpty()) {
            ExtentTest skippedNode = extentTest.createNode("Skipped Steps");
            for (String stepDescription : completeStepDescriptionList) {
                skippedNode.skip(stringManipulation.cleanBDDActions(stepDescription));
            }
        }
        stepListener.executedTestSteps.clear();
        completeStepDescriptionList.clear();
    }
}