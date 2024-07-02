package com.ahq.pages.com;

import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class appUtills {

    public static String renameddestinationpath = null;

    // Method to Read excel file and convert contents to list
    @QAFTestStep(description = "I read Excel File and Converted to ArrayList")
    public static HashMap<String, String> readExcelFile(String excelFilePath, int excelSheetIndex) throws IOException {
        FileInputStream fs = new FileInputStream(excelFilePath);
        XSSFWorkbook workbook = new XSSFWorkbook(fs);
        XSSFSheet sheet = workbook.getSheetAt(excelSheetIndex);

        // Get the first and second rows
        int totalColumns = sheet.getRow(0).getLastCellNum();

        // Initialize the HashMap
        HashMap<String, String> dataMap = new HashMap<>();

        // Use DataFormatter to preserve the original data formatting
        DataFormatter formatter = new DataFormatter();
        for (int j = 0; j < totalColumns; j++) {
            String key = formatter.formatCellValue(sheet.getRow(0).getCell(j));
            String value = formatter.formatCellValue(sheet.getRow(1).getCell(j));
            dataMap.put(key, value);
        }

        // Close the workbook and file stream
        workbook.close();
        fs.close();
        System.out.println("DMAP: "+dataMap);
        return dataMap;
    }

    // Method to convert Values in list of WebElements to ArrayList
    @QAFTestStep(description = "I fetch values in WebElement List and store to Array list")
    public ArrayList convertValueToList(List<WebElement> element, int columns) {
        ArrayList UIValueList = new ArrayList();
        for (int s = 0; s < element.size(); s++) {
            UIValueList.add(element.get(s));
        }
        return UIValueList;
    }

    // Method to fetch value in table based on headers
    // Not used patterns, as used common tabluar tags
    @QAFTestStep(description = "I find the value in the table with {0} Column and {1} row")
    public static String getValueFromTable(String columnHeader, String rowHeader) {
        // Locate the table by a distinctive attribute or class
        WebElement table = new WebDriverTestBase().getDriver().findElement(By.xpath("//table[*]"));
        // Find all column headers in the table header section to determine the column index
        List<WebElement> columnHeaders = table.findElements(By.cssSelector("thead tr th"));
        int columnIndex = -1;
        for (int i = 0; i < columnHeaders.size(); i++) {
            System.out.println("columnHeaders.size(): " + columnHeaders.size());
            System.out.println("Column Header Text: " + columnHeaders.get(i).getText());
            System.out.println("i: " + i);
            if (columnHeaders.get(i).getText().contains(columnHeader)) {
                System.out.println("Column Header: " + columnHeader);
                columnIndex = i;
                System.out.println("i: " + i);
                break;
            }
        }
        // Handle case where column header is not found
        if (columnIndex == -1) {
            throw new IllegalArgumentException("Column header not found: " + columnHeader);
        }
        // Find all rows in the table body
        List<WebElement> rows = table.findElements(By.cssSelector("tbody tr"));
        for (WebElement row : rows) {
            // Check if the first cell in the row matches the row header
            WebElement firstCell = row.findElement(By.cssSelector("th"));
            if (firstCell.getText().contains(rowHeader)) {
                // Return the text from the cell at the found column index
                List<WebElement> cells = row.findElements(By.cssSelector("td"));
                // Adjust columnIndex for the first th element
                System.out.println("Cell data is: " + cells.get(columnIndex - 1).getText());
                return (cells.get(columnIndex - 1).getText());
            }
        }
        // Handle case where row header is not found
        throw new IllegalArgumentException("Row header not found: " + rowHeader);
    }

    // Method To Get path of the file which is of latest updated date
    // Return values in list in format - [Pathname,filename,file extention]
    public ArrayList fetchFileNameBasedOnLatest(String pathname) throws ParseException {
        File directory = new File(pathname);
        File[] filesList = directory.listFiles();
        Arrays.sort(filesList, Comparator.comparingLong(File::lastModified));
        System.out.println(filesList.length);
        String pathName = null;
        ArrayList List = new ArrayList();
        for (int i = filesList.length - 1; i > 0; i--) {
            //System.out.println(filesList[i].getName());
            if (filesList[i].getName().contains(".xlsx") && filesList[i].getName().contains("-")) {
                String[] fileName = filesList[i].getName().split(".");
                pathName = filesList[i].getPath();
                System.out.println("pathname: " + pathName);
                List.add(pathName);
                List.add(fileName);
                //List.add(fileName[1]);
                break;
            }
        }
        return List;
    }

    // Method to check for File Availability in a location
    public boolean fileAvailabiltyInFolder(String folderpath, String FileName) {
        File dir = new File(folderpath);
        File[] fileList = dir.listFiles();
        for (int i = 0; i < fileList.length; i++) {
            if (fileList[i].getName().equalsIgnoreCase(FileName)) {
                System.out.println("File Exists In Specified Location");
                return true;
            }

        }
        return false;
    }

    // Method to delete File from a path
    public void deleteFileFromFolder(String folderpath, String FileName) {
        File dir = new File(folderpath);
        File[] fileList = dir.listFiles();
        for (int i = 0; i < fileList.length; i++) {
            if (fileList[i].getName().equalsIgnoreCase(FileName)) {
                fileList[i].delete();
                System.out.println("File Existed is deleted");
            }
        }
    }

    // Method to transfer from Source Location to Destination Location
    public Boolean tranferFileToAnotherLocation(String sourceLoc, String destinationLoc, String testCaseName, String fileExtn) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMddhhmmss");
        String timestamp = simpleDateFormat.format(new Date());
        //Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String renameTo = testCaseName + timestamp + "." + fileExtn;
        File sourcefile = new File(sourceLoc);
        String destinationPath = destinationLoc + testCaseName;
        renameddestinationpath = destinationLoc + testCaseName + "\\" + renameTo;
        File des = new File(destinationPath);
        // check Folder availabilty
        if (!des.exists()) {
            des.mkdirs();
            System.out.println("Folder Path is Created");
        }
        Boolean movedstatus = sourcefile.renameTo(new File(renameddestinationpath));
        System.out.println("Renamed to " + testCaseName + " and Moved to " + destinationLoc + "is " + movedstatus + " and Renamed Destinated Path is : " + renameddestinationpath);
        return movedstatus;
    }

    // Method to return WebElement from Locator patter
    @QAFTestStep(description = "I get WebElement from {locator}")
    public static WebElement iGetWebElement(String locator) throws Exception {
        WebElement element = new WebDriverTestBase().getDriver().findElement(locator);
        return element;
    }
}
