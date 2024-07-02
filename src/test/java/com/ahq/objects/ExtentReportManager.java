package com.ahq.objects;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.qmetry.qaf.automation.core.ConfigurationManager.getBundle;


public class ExtentReportManager {
    
    private static ExtentReports extent;
    
    public static ExtentReports getInstance() {
        if (extent == null) {
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy-HHmm");
            String formattedDateTime = currentDateTime.format(formatter);
            extent = new ExtentReports();
            String reportPrefix;
            String environment = (getBundle().getString("env") != null) ? getBundle().getString("env").toLowerCase() : "env";
            String suite = (getBundle().getString("suite") != null) ? getBundle().getString("suite").toLowerCase() : "suite";
            reportPrefix = environment + "-" + suite;
            String reportFileName;
            if (getBundle().getString("html.report.generate").equalsIgnoreCase("single")) {
                reportFileName = "extent-reports/" + reportPrefix + "-TestReport.html";
            } else {
                reportFileName = "extent-reports/" + reportPrefix + "-TestReport-" + formattedDateTime + ".html";
            }
            ExtentSparkReporter spark = new ExtentSparkReporter(reportFileName);
            spark.config().setDocumentTitle(getBundle().getString("html.report.title"));
            spark.config().setReportName(getBundle().getString("html.report.name"));
            spark.config().thumbnailForBase64(true);
            extent.attachReporter(spark);

        }
        return extent;
    }
}
