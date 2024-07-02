package com.ahq.utils;

import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.util.Validator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class utilsFilesystem {
    public static String getDownloadFolderPath() {
        String userHome = System.getProperty("user.home");
        Path downloadPath = Paths.get(userHome, "Downloads");

        if (Files.exists(downloadPath)) {
            return downloadPath.toString();
        } else {
            throw new RuntimeException("Download folder does not exist.");
        }
    }

    public static String getLatestDownloadedFileName(String downloadDir) throws IOException {
        return Files.list(Paths.get(downloadDir))
                .filter(Files::isRegularFile)
                .max(Comparator.comparingLong(f -> f.toFile().lastModified()))
                .orElseThrow(() -> new IOException("No files found in download directory"))
                .toFile()
                .getName();

    }

    @QAFTestStep(description = "I assert that the excel file is downloaded")
    public static void iCheckTheDownloadedExcelFile() throws Exception {
        String downloadPath = getDownloadFolderPath();
        String downloadFileFullPath = downloadPath + "\\" + getLatestDownloadedFileName(downloadPath);
        System.out.println("This is the latest file name: " + downloadFileFullPath);
    }

    @QAFTestStep(description = "I rename the excel file")
    public static void iSaveCheckAndRenameExcelFile() throws Exception {
        String downloadPath = getDownloadFolderPath();
        String fileName = getLatestDownloadedFileName(downloadPath);
        String downloadFileFullPath = downloadPath + "\\" + fileName;
        String newFolderPath = "C:\\saved";
        String newFileName = "visitor_arrivals";

        System.out.println("This is the latest file name: " + downloadFileFullPath);
        renameAndSaveFile(downloadFileFullPath, newFolderPath, newFileName);
    }

    public static void renameAndSaveFile(String originalPath, String newFolderPath, String newFileName) {
        System.out.println("Renaming file");
        File file = new File(originalPath);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = dateFormat.format(new Date());

        String uniqueFileName = newFileName + '_' + timestamp + ".xlsx";

        File newFile = new File(newFolderPath + File.separator + uniqueFileName);
        if (file.renameTo(newFile)) {
            System.out.println("File renamed successfully to " + newFile.getName());
        } else {
            System.out.println("Failed to rename file");
        }
    }

    @QAFTestStep(description = "I assert that the downloaded file has the extension {0}")
    public static void iVerifyFileExtentionName(String expectedFileExtension) throws Exception {
        String downloadPath = getDownloadFolderPath();

        String latestDownloadedFileName = getLatestDownloadedFileName(downloadPath);

        String downloadFileFullPath = downloadPath + "\\" + latestDownloadedFileName;

        System.out.println("Debug: Latest file name: " + latestDownloadedFileName);
        System.out.println("Debug: Expected extension: " + expectedFileExtension);

        String actualFileExtension = getFileExtension(latestDownloadedFileName);

        Validator.assertTrue(actualFileExtension.equals(expectedFileExtension), "The file " + latestDownloadedFileName + " does not have the expected extension. Expected: " + expectedFileExtension + ", but found: " + getFileExtension(latestDownloadedFileName), "The file " + latestDownloadedFileName + " has the expected extension: " + expectedFileExtension);

    }

    private static String getFileExtension(String fileName) {
        int lastIndexOfDot = fileName.lastIndexOf('.');
        if (lastIndexOfDot == -1) {
            return ""; // No extension found
        }
        return fileName.substring(lastIndexOfDot);
    }
}
