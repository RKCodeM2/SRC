@STANQLIK
Feature: Stan Qlik Test Cases

    @P63-Qlik-002
        @Qlik
        @TestCaseId:P63-Qlik-002
    Scenario Outline: P63-Qlik-002 - Verify the downloaded file results and compare with KPI number according to filter chosen
    Step: LoginPage: I open Stan url "${env.url}" login as userrole "T1 Industry User" with UUID "<T1IndustryUserUUID>"
    Result: "StanHomePage": I verify user login successfully for:"Stan | Dashboard" with username "<Username>"
    Step: "StanHomePage": I hover on "Data Visualisation" and click "Hotel Performance" sub-menu in "DataVisualisation"
    Result: "HotelPerformanceDashboard": I verify dashboard open in another window "Hotel Performance | App overview - Qlik Sense"
    Step: I click on "Industry Performance Analysis" sheet
    Result: "Industry Performance Analysis": I verify dashboard page title as "Hotel Performance - Industry Performance Analysis | Sheet - Qlik Sense"
    Step: I click on "YEAR" and verify "2024" selected by default and "Confirm selection"
    Result: I verify ["2024"] filters selected
    Step: I select filters ["QUARTER:Q1","MONTH:Jan","HOTEL TIER:Economy"] and click "Confirm selection"
    Result: I verify ["Q1","Jan","Economy"] filters selected
    Step:  I mouseover to "Available Room Nights" right click to select "Download as..." and select "Data"
    Result: I verify "Export complete" popup window displayed with message "Your exported data is ready for download."
    Step: "Industry Performance Analysis download file": I click on "Click here to download your data file" after download complete click "Close"

        Examples: {'dataFile':'resources/data/qlik_test_data.csv', 'filter':'_ID==\"P63-Qlik-002\"'}

    @P15-Qlik-004
        @Qlik
        @TestCaseId:P15-Qlik-004
    Scenario Outline: P15-Qlik-004: To ensure that the chart's  data  and the KPI number tally according to the filter chosen
    Step: LoginPage: I open Stan url "${env.url}" login as userrole "STB User" with UUID "<STBUserUUID>"
    Result: "StanHomePage": I verify user login successfully for:"Stan | Dashboard" with username "<Username>"
    Step: "StanHomePage": I hover on "Administration" and click "Central Analytics for Industry and User" sub-menu in "Analytics"
    Result: I switch browser tab to "2"
    And: qlikDashboard: I wait for Qlik Dashboard to load
    Step: Click sheet "Active and Engage User Analysis"
    Step: Right click graph tab "Active User Trend Analysis"
    And: Click context menu option "Bar chart"
    And: Click context menu option "Download as..."
    And: Click context menu option "Data"
    And: I wait for "5" seconds
    Result: I assert text present in page "Click here to download your data file."
    Step: Click link "Click here to download your data file."
    And: I wait for "15" seconds
    And: I assert that the excel file is downloaded


        Examples: {'dataFile':'resources/data/qlik_test_data.csv', 'filter':'_ID==\"P15-Qlik-004\"'}

    @U17-Qlik-003
      @Qlik
    @TestCaseId:U17-Qlik-003
    Scenario Outline: U17-Qlik-003 -Verify the downloaded file results and compare with KPI number.
    Step: LoginPage: I open Stan url "${env.url}" login as userrole "Selenium STB User 4" with UUID "<STBUserUUID>"
    Result: "StanHomePage": I verify user login successfully for:"Stan | Dashboard" with username "<Username>"
    Step: "StanHomePage": I hover on "Data Visualisation" and click "TIH Performance" sub-menu in "DataVisualisation"
    Result: "TIHPerformanceDashboard": I verify "TIH Performance" "public" dashboard open in another window "TIH Performance - STB | App overview - Qlik Sense"
    Step: I click on "Overall Content Contribution and User Profiles" sheet
    Result: "TIHPerformanceDashboard": I verify dashboard page title as "TIH Performance - STB - Overall Content Contribution and User Profiles | Sheet - Qlik Sense"
    Step: I click on "Content Types" and verify "TO" selected by default and "Confirm selection"
    Result: I verify ["TO"] filters selected
    Step: I select filters ["Year:2023","Month:Aug"] and click "Confirm selection"
    Result: I verify ["2023","Aug"] filters selected
    Step: I right click on "Contribution Trend" and navigate "Bar chart" to "Download as..." to "Data" and verify and click link text "Click here to download your data file."
    Result: I assert that the excel file is downloaded
    Step: I verify latest file downloaded in "C:\\Users\\Administrator\\Downloads\\" matches "3,045" KPI

      Examples: {'dataFile':'resources/data/qlik_test_data.csv', 'filter':'_ID==\"U17-Qlik-003\"'}
