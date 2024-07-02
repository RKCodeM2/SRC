@STANPORTAL
Feature: Stan Portal Test Cases

    @PTL-INF-HCen-1
        @Portal
        @TestCaseId:PTL-INF-HCen-1
    Scenario Outline: PTL-INF-HCen-1: Help Centre » User Guides (Tab) - any one STB User
    Step: LoginPage: I open Stan url "${env.url}" login as userrole "STB User" with UUID "<STBUserUUID>"
    Result: stanHomePage: I assert that the user is logged in
    Step: Click Navbar "Help Centre"
    Result: stanHomePage: I verify that "Help Centre" tab is active
    Step: Click accordion "Data Submission and Visualisation"
    Result: stanHelpCentre: I verify that ["Hotel Performance Submission and Delegation", "TXI User Guide", "General Data Visualisation User Guide"] is shown
    Step: stanHelpCentre: I close accordion "Data Submission and Visualisation" and open "Data Sharing and Partner"
    Result: stanHelpCentre: I verify that ["Visitor Arrivals Forecast Analysis Dashboard and Model as a Service User Guide", "Data Marketplace User Guide", "Sandbox User Guide", "Private Space User Guide"] is shown
    Step: stanHelpCentre: I close accordion "Data Sharing and Partner" and open "Video Guides"
    Result: stanHelpCentre: I verify that ["General Data Visualisation and Private Space Videos"] is shown
    Step: Click link "General Data Visualisation and Private Space Videos"
    Result: stanHelpCentre: I verify that "General Data Visualisation" section has ["Getting Started", "Customise Your Analysis"] videos
    And: stanHelpCentre: I verify that "Private Space" section has ["Stan Private Space (1/2)", "Stan Private Space (2/2)"] videos
    And: Click link "Back to User Guides"
    Step: Click accordion "Administration"
    Result: stanHelpCentre: I verify that ["User Access and Administration User Guide"] is shown
    Step: stanHelpCentre: I open accordion "Data Submission and Visualisation" and click on link "Hotel Performance Submission and Delegation User Guide"
    Result: Stan: I wait for page to load
    Step: "HotelPerformanceSubmissionAndDelegationUserGuide": I verify dashboard page title as "Hotel Performance Submission and Delegation User Guide"
    Step: I verify link with text "Download User Guide" is present
    Step: "HotelPerformanceSubmissionAndDelegationUserGuide": click link "Download User Guide" and switch to browser tab "2" and save the PDF file
    Result: I assert that the downloaded file has the extension ".pdf"
    Step: "HotelPerformanceSubmissionAndDelegationUserGuide": close current tab and switch tab to "1" and click link "Back to User Guides"
    Step: stanHelpCentre: I open accordion " Data Sharing and Partner" and click on link "Visitor Arrivals Forecast Analysis Dashboard and Model as a Service User Guide"
    Result: Stan: I wait for page to load
    Step: "VisitorArrivalsForecastDashboardAndModelAsaServiceUserGuide": I verify dashboard page title as "Stan | Visitor Arrivals Forecast Dashboard and Model as a Service User Guide"
    Step: I verify link with text "Download User Guide" is present
    Step: "VisitorArrivalsForecastDashboardAndModelAsaServiceUserGuide": click link "Download User Guide" and switch to browser tab "2" and save the PDF file
    Result: I assert that the downloaded file has the extension ".pdf"
    Step: "VisitorArrivalsForecastDashboardAndModelAsaServiceUserGuide": close current tab and switch tab to "1" and click link "Back to User Guides"
    Step: stanHelpCentre: I open accordion "Administration" and click on link "User Access and Administration User Guide"
    Result: Stan: I wait for page to load
    Step: "UserAccessAndAdministrationUserGuide": I verify dashboard page title as "User Access and Administration User Guide"
    Step: I verify link with text "Download User Guide" is present
    Step: "UserAccessAndAdministrationUserGuide": click link "Download User Guide" and switch to browser tab "2" and save the PDF file
    Result: I assert that the downloaded file has the extension ".pdf"
    Step: "UserAccessAndAdministrationUserGuide": close current tab and switch tab to "1" and click link "Back to User Guides"
    Step: stanHelpCentre: I open accordion "Video Guides" and click on link "General Data Visualisation and Private Space Videos"
    Result: Stan: I wait for page to load
    Step: "GeneralDataVisualisationAndPrivateSpaceVideos": I verify dashboard page title as "General Data Visualisation and Private Space Videos"
    Step: stanHelpCentre: I click on video named "Getting Started" in "General Data Visualisation" section
    And: I switch browser tab to "2"
    Step: I assert that the video can be played in the video player
    Step: I close current window or tab
    And: I switch browser tab to "1"
    Step: "StanHomePage": I go to "profileTab" and "Sign Out" from the system
    Result: "LogoutPage" : I verify user logout and message displayed as "Sign out successful"

        Examples: {'dataFile':'resources/data/portal_test_data.csv', 'filter':'_ID==\"PTL-INF-HCen-1\"'}

    @PTL-INF-HCen-2
        @Portal
        @TestCaseId:PTL-INF-HCen-2
    Scenario Outline:PTL-INF-HCen-2: Help Centre » Search (Tab) - any one Industry User
    Step: LoginPage: I open Stan url "${env.url}" login as userrole "T1 Industry Admin" with UUID "<T1IndustryAdminUUID>"
    Result: "StanHomePage": I verify user login successfully for:"Stan | Dashboard" with username "<Username>"
    Step: Click Navbar "Help Centre"
    Result: stanHomePage: I verify that "Help Centre" tab is active
    Step: Click link "Search"
    Result: Stan: I wait for page to load
    Step: stanHelpCentre: I search for "ab" using the search box with ID "argument"
    Result: I verify error is present in page "Please key in at least 3 alphanumeric characters."
    Step: stanHelpCentre: I search for "abc" using the search box with ID "argument"
    Result: I verify error is present in page "No matches found. Please try again."
    Step: stanHelpCentre: I search for "Data Submission and Visualisation" using the search box with ID "argument"
    Result: stanHelpCentre: I assert that there are search results after executing a search
    Step: stanHelpCentre: I search for "TXI User Guide" using the search box with ID "argument"
    Result: stanHelpCentre: I assert that there are search results after executing a search
    Step: stanHelpCentre: I search for "Data Marketplace" using the search box with ID "argument"
    Result: stanHelpCentre: I assert that there are search results after executing a search
    Step: stanHelpCentre: I search for "What is the Tourism Transformation Index (TXI)" using the search box with ID "argument"
    Result: I verify error is present in page "No matches found. Please try again."
    Step: stanHelpCentre: I search for "ALTER" using the search box with ID "argument"
    Result: I verify error is present in page "Invalid character(s) found. Please refrain from using: ALTER "
    Step: stanHelpCentre: I search for "abc@" using the search box with ID "argument"
    Result: I verify error is present in page "No matches found. Please try again."
    Step: stanHelpCentre: I search for "@@@" using the search box with ID "argument"
    Result: I verify error is present in page "Please key in at least 3 alphanumeric characters."
    Step: stanHelpCentre: I search for "abc%" using the search box with ID "argument"
    Result: I verify error is present in page "Invalid character(s) found. Please refrain from using: %"
    Step: stanHelpCentre: I search for "%" using the search box with ID "argument"
    Result: I verify error is present in page "Invalid character(s) found. Please refrain from using: %"
    Result: I verify error is present in page "Please key in at least 3 alphanumeric characters."
    Step: stanHelpCentre: I search for "Partner" using the search box with ID "argument"
    Result: I verify accordion "Data Sharing and Partner" after searching for "Partner"
    And: stanHelpCentre: I verify that ["Visitor Arrivals Forecast Analysis Dashboard and Model as a Service User Guide", "Data Marketplace User Guide", "Sandbox User Guide", "Private Space User Guide"] is shown
    Step: stanHelpCentre: I search for "Hotel" using the search box with ID "argument"
    Result: I verify accordion "Data Submission and Visualisation" is present
    And: stanHelpCentre: I verify that ["Hotel Performance Submission and Delegation User Guide"] is shown
    Step: stanHelpCentre: I search for "Administration" using the search box with ID "argument"
    Result: I verify accordion "Administration" after searching for "Administration"
    And: stanHelpCentre: I verify that ["User Access and Administration User Guide"] is shown
    Step: stanHelpCentre: I search for "TEST" using the search box with ID "argument"
    Result: I verify error is present in page "No matches found. Please try again."
    Step: "StanHomePage": I go to "profileTab" and "Sign Out" from the system
    Result: "LogoutPage" : I verify user logout and message displayed as "Sign out successful"

        Examples: {'dataFile':'resources/data/portal_test_data.csv', 'filter':'_ID==\"PTL-INF-HCen-2\"'}

    @PTL-INF-HCen-3
        @Portal
        @TestCaseId:PTL-INF-HCen-3
    Scenario Outline:PTL-INF-HCen-3: Help Centre » FAQ (Tab) - any one Industry user
    Step: LoginPage: I open Stan url "${env.url}" login as userrole "T1 Industry Admin" with UUID "<T1IndustryAdminUUID>"
    Result: "StanHomePage": I verify user login successfully for:"Stan | Dashboard" with username "<Username>"
    Step: Click Navbar "Help Centre"
    Result: stanHomePage: I verify that "Help Centre" tab is active
    Step: Click link "FAQ"
    Result: Stan: I wait for page to load
    Step: stanHelpCentre: I verify list of headers ['Manage Users', 'Data Submission', 'Tourism Transformation Index (TXI)', 'Hotel License Delegation', 'Data Marketplace', 'Model as a Service', 'Sandbox'] in page
    Step: "StanHomePage": I go to "profileTab" and "Sign Out" from the system
    Result: "LogoutPage" : I verify user logout and message displayed as "Sign out successful"

        Examples: {'dataFile':'resources/data/portal_test_data.csv', 'filter':'_ID==\"PTL-INF-HCen-3\"'}

    @PTL-INF-TRD-4
        @Portal
        @TestCaseId:PTL-INF-TRD-4
    Scenario Outline: PTL-INF-TRD-4 -Tourism Receipts Dashboard - T3 Industry User
    Step: LoginPage: I open Stan url "${env.url}" login as userrole "T3 Industry User" with UUID "<T3IndustryUserUUID>"
    Result: "StanHomePage": I verify user login successfully for:"Stan | Dashboard" with username "<Username>"
    Step: "StanHomePage": I hover on "Data Visualisation" and click "Tourism Receipts" sub-menu in "DataVisualisation"
    Result: "TourismReceiptsPublicDashboard": I verify dashboard for "Tourism Receipts Public" open in another window "Tourism Receipts | App overview - Qlik Sense" and verify ["<Sheet1>","<Sheet2>","<Sheet3>","<glossary>"] is present
    Step: "TourismReceiptsPublicDashboard": I close Qlik Dashboard and switch to home page
    Step: "StanHomePage":I Click on "Tourism Receipts" Link in my dashboard
    Result: "TourismReceiptsPublicDashboard": I verify dashboard for "Tourism Receipts Public" open in another window "Tourism Receipts | App overview - Qlik Sense" and verify ["<Sheet1>","<Sheet2>","<Sheet3>","<glossary>"] is present
    Step: "TourismReceiptsPublicDashboard": I close Qlik Dashboard and switch to home page
    Step: "StanHomePage": I go to "profileTab" and "Sign Out" from the system
    Result: "LogoutPage" : I verify user logout and message displayed as "Sign out successful"


        Examples: {'dataFile':'resources/data/portal_test_data.csv', 'filter':'_ID==\"PTL-INF-TRD-4\"'}


    @PTL-INF-TRD-5
        @Portal
        @TestCaseId:PTL-INF-TRD-5
    Scenario Outline: PTL-INF-TRD-5 -Tourism Receipts Dashboard - STB Sub-Admin
    Step: LoginPage: I open Stan url "${env.url}" login as userrole "STB Sub-Admin" with UUID "<STBSubAdminUUID>"
    Result: "StanHomePage": I verify user login successfully for:"Stan | Dashboard" with username "<AdminUsername>"
    Step: "StanHomePage": I hover on "Data Visualisation" and click "Tourism Receipts" sub-menu in "DataVisualisation"
    Result: "TourismReceiptsPublicDashboard": I verify dashboard for "Tourism Receipts Public" open in another window "Tourism Receipts | App overview - Qlik Sense" and verify ["<Sheet1>","<Sheet2>","<Sheet3>","<glossary>"] is present
    Step: "TourismReceiptsPublicDashboard": I close Qlik Dashboard and switch to home page
    Step: "StanHomePage":I Click on "Tourism Receipts" Link in my dashboard
    Result: "TourismReceiptsPublicDashboard": I verify dashboard for "Tourism Receipts Public" open in another window "Tourism Receipts | App overview - Qlik Sense" and verify ["<Sheet1>","<Sheet2>","<Sheet3>","<glossary>"] is present
    Step: "TourismReceiptsPublicDashboard": I close Qlik Dashboard and switch to home page
    Step: "StanHomePage": I go to "profileTab" and "Sign Out" from the system
    Result: "LogoutPage" : I verify user logout and message displayed as "Sign out successful"


        Examples: {'dataFile':'resources/data/portal_test_data.csv', 'filter':'_ID==\"PTL-INF-TRD-5\"'}

    @PTL-INF-TAD-6
        @Portal
        @TestCaseId:PTL-INF-TAD-6
    Scenario Outline: PTL-INF-TAD-6 -Travel Agent Dashboard - T1 Industry User of Travel Agents Industry
    Step: LoginPage: I open Stan url "${env.url}" login as userrole "STB Sub-Admin of Travel Agents Industry" with UUID "<T1IndustryUserUUID>"
    Result: "StanHomePage": I verify user login successfully for:"Stan | Dashboard" with username "<Username>"
    Step: "StanHomePage": I hover on "Data Visualisation" and click "Travel Agents" sub-menu in "DataVisualisation"
    Result: "TravelAgentsDashboard": I verify dashboard for "Travel agent" open in another window "Travel Agents - Data Partners | App overview - Qlik Sense" and verify ["<Sheet1>","<Sheet2>","<Sheet3>","<glossary>","<adhocreport>"] is present
    Step: "TravelAgentsDashboard": I close Qlik Dashboard and switch to home page
    Step: "StanHomePage":I Click on "Travel Agents" Link in my dashboard
    Result: "TravelAgentsDashboard": I verify dashboard for "Travel agent" open in another window "Travel Agents - Data Partners | App overview - Qlik Sense" and verify ["<Sheet1>","<Sheet2>","<Sheet3>","<glossary>","<adhocreport>"] is present
    Step: "TravelAgentsDashboard": I close Qlik Dashboard and switch to home page
    Step: "StanHomePage": I go to "profileTab" and "Sign Out" from the system
    Result: "LogoutPage" : I verify user logout and message displayed as "Sign out successful"
        Examples: {'dataFile':'resources/data/portal_test_data.csv', 'filter':'_ID==\"PTL-INF-TAD-6\"'}

    @PTL-INF-TAD-7
        @Portal
        @TestCaseId:PTL-INF-TAD-7
    Scenario Outline: PTL-INF-TAD-7 -Travel Agent Dashboard - T2 Industry User of NON-Travel Agents Industry
    Step: LoginPage: I open Stan url "${env.url}" login as userrole "T2 Industry User" with UUID "<T2IndustryUserUUID>"
    Result: "StanHomePage": I verify user login successfully for:"Stan | Dashboard" with username "<Username>"
    Step: "StanHomePage": I hover on "Data Visualisation" and click "Travel Agents" sub-menu in "DataVisualisation"
    Result: "TravelAgentsPublicDashboard": I verify dashboard for "Travel agent public" open in another window "Travel Agents | App overview - Qlik Sense" and verify ["<Sheet1>","<glossary>"] is present
    Step: "TravelAgentsPublicDashboard": I close Qlik Dashboard and switch to home page
    Step: "StanHomePage":I Click on "Travel Agents" Link in my dashboard
    Result: "TravelAgentsPublicDashboard": I verify dashboard for "Travel agent public" open in another window "Travel Agents | App overview - Qlik Sense" and verify ["<Sheet1>","<glossary>"] is present
    Step: "TravelAgentsPublicDashboard": I close Qlik Dashboard and switch to home page
    Step: "StanHomePage": I go to "profileTab" and "Sign Out" from the system
    Result: "LogoutPage" : I verify user logout and message displayed as "Sign out successful"

        Examples: {'dataFile':'resources/data/portal_test_data.csv', 'filter':'_ID==\"PTL-INF-TAD-7\"'}

    @PTL-INF-TAD-8
        @Portal
        @TestCaseId:PTL-INF-TAD-8
    Scenario Outline: PTL-INF-TAD-8 -Travel Agent Dashboard - STB Sub-Admin of Travel Agents Industry
    Step: LoginPage: I open Stan url "${env.url}" login as userrole "STB Sub-Admin of Travel Agents Industry" with UUID "<STBSubAdminTravelAgentsIndustryUUID>"
    Result: "StanHomePage": I verify user login successfully for:"Stan | Dashboard" with username "<AdminUsername>"
    Step: "StanHomePage": I hover on "Data Visualisation" and click "Travel Agents" sub-menu in "DataVisualisation"
    Result: "TravelAgentsDashboard": I verify dashboard for "Travel agent" open in another window "Travel Agents - STB | App overview - Qlik Sense" and verify ["<Sheet1>","<Sheet2>","<Sheet3>","<glossary>","<adhocreport>"] is present
    Step: "TravelAgentsDashboard": I close Qlik Dashboard and switch to home page
    Step: "StanHomePage":I Click on "Travel Agents" Link in my dashboard
    Result: "TravelAgentsDashboard": I verify dashboard for "Travel agent" open in another window "Travel Agents - STB | App overview - Qlik Sense" and verify ["<Sheet1>","<Sheet2>","<Sheet3>","<glossary>","<adhocreport>"] is present
    Step: "TravelAgentsDashboard": I close Qlik Dashboard and switch to home page
    Step: "StanHomePage": I go to "profileTab" and "Sign Out" from the system
    Result: "LogoutPage" : I verify user logout and message displayed as "Sign out successful"
        Examples: {'dataFile':'resources/data/portal_test_data.csv', 'filter':'_ID==\"PTL-INF-TAD-8\"'}

@PTL-INF-SBX-9
      @Portal
      @TestCaseId:PTL-INF-SBX-9
      Scenario Outline: PTL-INF-SBX-9 Create/ Edit & Delete Sandbox Access
      Step: LoginPage: I open Stan url "${env.url}" login as userrole "STB User Admin" with UUID "<STBAdminUUID>"
      Result: "StanHomePage": I verify user login successfully for:"Stan | Dashboard" with username "<AdminUsername>"
      Step: stanHomePage: I hover over "Administration" on the navbar and click "Manage Industry Users" on the submenu
      Result: "ManageIndustryUsersPage": I verify header title as "Manage Industry Users"
      Step: "ManageIndustryUsersPage": "STB User Admin" inputs "Industry User" email "<T1IndustryAdminEmail>" into "user_search_criteria"
      Result: "ManageIndustryUsersPage": I verify "<T1IndustryAdminEmail>" in "User Listing"
      Step: "ManageSandboxAccessPage": I click on "Manage Sandbox Access" action
      Result: "ManageSandboxAccessPage": I verify "<T1IndustryAdminEmail>" in "Account Information"
      Step: "ManageSandboxAccessPage": I click on "Assign Sandbox" action
      Step: "ManageSandboxAccessPage": I select "sandboxCode" dropdown with value "SANDBOX-2022-DTP2-KPMG"
      Step: "ManageSandboxAccessPage": I "select" Start Date:"ValidDate" on "sandboxStartDate" and End Date:"InvalidDate" on "sandboxEndDate"
      Result: "ManageSandboxAccessPage": I verify "Sandbox Access End Date is before Sandbox Access Start Date" present in "sandboxEndDateAlert"
      Step: "ManageSandboxAccessPage": I "reselect" Start Date:"ValidDate" on "sandboxStartDate" and End Date:"ValidDate" on "sandboxEndDate"
      Step: "ManageSandboxAccessPage": I click on "Create" action
      Result: "ManageSandboxAccessPage": I verify "Sandbox-2022-DTP2-KPMG" in "Sandbox"
      Step: "Sandbox": Click on "Edit Sandbox Access" button of "Sandbox-2022-DTP2-KPMG"
      Result: "EditSandboxAccessPage": I verify "Sandbox-2022-DTP2-KPMG" present in "Sandbox Name"
      Step: "EditSandboxAccessPage": I verify text "StartDate" present in label "startDateLabel"
      Step: "EditSandboxAccessPage": I verify text "EndDate" present in label "endDateLabel"
      Step: "EditSandboxAccessPage": I "select" Start Date:"ValidDate" on "sandboxStartDate" and End Date:"InvalidDate" on "sandboxEndDate"
      Result: "EditSandboxAccessPage": I verify "Sandbox Access End Date is before Sandbox Access Start Date" present in "sandboxEndDateAlert"
      Step: "EditSandboxAccessPage": I "reselect" Start Date:"ValidDate" on "sandboxStartDate" and End Date:"ValidDate" on "sandboxEndDate"
      Step: "ManageSandboxAccessPage": I click on "Save" action
      Step: "Sandbox": Click on "Delete Sandbox Access" button of ["Sandbox-2022-DTP2-KPMG"], verify popup title is "Delete Sandbox Access?" and click on "Cancel" button
      Result: "ManageSandboxAccessPage": I verify "Sandbox-2022-DTP2-KPMG" in "Sandbox"
      Step: "Sandbox": Click on "Delete Sandbox Access" button of ["Sandbox-2022-DTP2-KPMG"], verify popup title is "Delete Sandbox Access?" and click on "Proceed" button
      Step: "stanSandboxAccess": I go to "profileTab" and "Sign Out" from the system
      Result: "LogoutPage" : I verify user logout and message displayed as "Sign out successful"

          Examples: {'dataFile':'resources/data/portal_test_data.csv', 'filter':'_ID==\"PTL-INF-SBX-9\"'}


    @PTL-INF-SBX-10
    @Portal
    @TestCaseId:PTL-INF-SBX-10
    Scenario Outline: PTL-INF-SBX-10 Access "About Sandbox" page after a Sandbox is assigned
    Step: LoginPage: I open Stan url "${env.url}" login as userrole "STB User Admin" with UUID "<STBAdminUUID>"
    Result: "StanHomePage": I verify user login successfully for:"Stan | Dashboard" with username "<AdminUsername>"
    Step: stanHomePage: I hover over "Administration" on the navbar and click "Manage Industry Users" on the submenu
    Result: "ManageIndustryUsersPage": I verify header title as "Manage Industry Users"
    Step: "ManageIndustryUsersPage": "STB User Admin" inputs "Industry User" email "<T1IndustrySubAdminEmail>" into "user_search_criteria"
    Result: "ManageIndustryUsersPage": I verify "<T1IndustrySubAdminEmail>" in "User Listing"
    Step: "ManageSandboxAccessPage": I click on "Manage Sandbox Access" action
    Result: "ManageSandboxAccessPage": I verify "<T1IndustrySubAdminEmail>" in "Account Information"
    Step: "ManageSandboxAccessPage": I click on "Assign Sandbox" action
    Step: "ManageSandboxAccessPage": I select "sandboxCode" dropdown with value "SANDBOX-2022-DTP2-KPMG"
    Step: "ManageSandboxAccessPage": I "select" Start Date:"ValidDate" on "sandboxStartDate" and End Date:"ValidDate" on "sandboxEndDate"
    Step: "ManageSandboxAccessPage": I click on "Create" action
    Result: "ManageSandboxAccessPage": I verify "Sandbox-2022-DTP2-KPMG" in "Sandbox"
    Step: "StanHomePage": I go to "profileTab" and "Sign Out" from the system
    Result: "LogoutPage" : I verify user logout and message displayed as "Sign out successful"
    Step: LoginPage: I open Stan url "${env.url}" login as userrole "STB User Admin" with UUID "<T1IndustrySubAdminUUID>"
    Result: "stanHomePage": I verify user login successfully for:"Stan | Dashboard" with username "<Username>"
    Step: "stanHomePage": I go to "profileTab" and "My Profile" from the system
    Result: "stanMyProfilePage": I verify text "Sandbox-2022-DTP2-KPMG (StartDate to EndDate" present
    Step: stanHomePage: I hover over "Stan Services" on the navbar and click "About Sandbox" on the submenu
    Result: "stanAboutSandbox": I verify headers title as ["Access Sandbox", "Who may access Sandbox?", "Instruction", "Request for a Sandbox", "Extend Sandbox validity or upgrade features", "Modify or terminate Sandbox access", "Granted Access to Sandbox?"]
    Step: "stanAboutSandbox": I click on "Sandbox Onboarding Form." link and verify file downloaded as "STANTI-SBX-REQUEST-Form v1.docx"
    Step: "stanAboutSandbox": I click on "Sandbox Extension/Upgrade Form." link and verify file downloaded as "STANTI-SBX-EXT_UGD-Form v1.docx"
    Step: "stanAboutSandbox": I click on "Sandbox Access/Termination Form." link and verify file downloaded as "STANTI-SBX-MDF-TRM-Form v1.docx"
    Step: "stanAboutSandbox": I click on "Access Sandbox" action
    Result: "stanSandboxAccess": I switch to another tab with index "2" and verify with screenshot
    Step: "stanSandboxAccess": I go to "profileTab" and "Sign Out" from the system
    Result: "LogoutPage" : I verify user logout and message displayed as "Sign out successful"
    Step: LoginPage: I open Stan url "${env.url}" login as userrole "STB User Admin" with UUID "<STBAdminUUID>"
    Result: "StanHomePage": I verify user login successfully for:"Stan | Dashboard" with username "<AdminUsername>"
    Step: stanHomePage: I hover over "Administration" on the navbar and click "Manage Industry Users" on the submenu
    Result: "ManageIndustryUsersPage": I verify header title as "Manage Industry Users"
    Step: "ManageIndustryUsersPage": "STB User Admin" inputs "Industry User" email "<T1IndustrySubAdminEmail>" into "user_search_criteria"
    Result: "ManageIndustryUsersPage": I verify "<T1IndustrySubAdminEmail>" in "User Listing"
    Step: "ManageSandboxAccessPage": I click on "Manage Sandbox Access" action
    Result: "ManageSandboxAccessPage": I verify "<T1IndustrySubAdminEmail>" in "Account Information"
    Step: "Sandbox": Click on "Delete Sandbox Access" button of ["Sandbox-2022-DTP2-KPMG"], verify popup title is "Delete Sandbox Access?" and click on "Proceed" button
    Step: "stanSandboxAccess": I go to "profileTab" and "Sign Out" from the system
    Result: "LogoutPage" : I verify user logout and message displayed as "Sign out successful"

        Examples: {'dataFile':'resources/data/portal_test_data.csv', 'filter':'_ID==\"PTL-INF-SBX-10\"'}


    @PTL-INF-CAP-11
    @Portal
    @TestCaseId:PTL-INF-CAP-11
    Scenario Outline: PTL-INF-CAP-11 Central Analytics Dashboards-STB User
    Step: LoginPage: I open Stan url "${env.url}" login as userrole "STB User Admin" with UUID "<STBAdminUUID>"
    Result: "StanHomePage": I verify user login successfully for:"Stan | Dashboard" with username "<AdminUsername>"
    Step: "stanHomePage": I hover on "Administration" and click "Manage STB Users" sub-menu in "Administration"
    Result: "ManageSTBUsersPage": I verify header title as "Manage STB Users"
    Step: "ManageSTBUsersPage": "STB User Admin" inputs "STB User" email "<STBUserEmail>" into "user_search_criteria"
    Result: "ManageSTBUsersPage": I verify "<STBUserEmail>" in "User Listing"
    Step: "UserSpecificDataDomainAssignmentPage": I click on "Data Domain" action
    Result: "UserSpecificDataDomainAssignmentPage": I verify dashboard page title as "User Specific Data Domain Assignment"
    Step: "AllowAccess": I click on "Allow" action, verify popup title is "Allow User Specific Data Domain Access?" and selects ["CAN_ORG","CAN_INDUSTRY_USER"] in "userDataDomainCodeAllow" dropdown and click on "allowAccessButton"
    Result: "UserSpecificDataDomainAssignmentPage": In "User Specific Data Domain Access:" listing I click "Allow" and verify ["Central Analytics for Organisation","Central Analytics for Industry and User"] not found in "userDataDomainCodeAllow" dropdown
    Step: "StanHomePage": I go to "profileTab" and "Sign Out" from the system
    Result: "LogoutPage" : I verify user logout and message displayed as "Sign out successful"
    Step: LoginPage: I open Stan url "${env.url}" login as userrole "STB User" with UUID "<STBUserUUID>"
    Result: "StanHomePage": I verify user login successfully for:"Stan | Dashboard" with username "<Username>"
    Step: "stanHomePage": I hover on "Administration" and click "Central Analytics for Organisation" sub-menu in "Administration"
    Result: "CentralAnalyticsDashboard": I verify dashboard open in another window "Central Analytics - STB (Restricted) | App overview - Qlik Sense"
    Step: "CentralAnalyticsDashboard": I close Qlik Dashboard and switch to home page
    Step: "stanHomePage": I hover on "Administration" and click "Central Analytics for Industry and User" sub-menu in "Administration"
    Step: "CentralAnalyticsDashboard": I verify dashboard open in another window "Central Analytics - STB | App overview - Qlik Sense"
    Step: "CentralAnalyticsDashboard": I close Qlik Dashboard and switch to home page
    Step: "StanHomePage": I go to "profileTab" and "Sign Out" from the system
    Result: "LogoutPage" : I verify user logout and message displayed as "Sign out successful"
    Step: LoginPage: I open Stan url "${env.url}" login as userrole "STB User Admin" with UUID "<STBAdminUUID>"
    Result: "StanHomePage": I verify user login successfully for:"Stan | Dashboard" with username "<AdminUsername>"
    Step: "stanHomePage": I hover on "Administration" and click "Manage STB Users" sub-menu in "Administration"
    Result: "ManageSTBUsersPage": I verify header title as "Manage STB Users"
    Step: "ManageSTBUsersPage": "STB User Admin" inputs "STB User" email "<STBUserEmail>" into "user_search_criteria"
    Result: "ManageSTBUsersPage": I verify "<STBUserEmail>" in "User Listing"
    Step: "UserSpecificDataDomainAssignmentPage": I click on "Data Domain" action
    Result: "UserSpecificDataDomainAssignmentPage": I verify dashboard page title as "User Specific Data Domain Assignment"
    Step: "User Specific Data Domain Access:": Click on delete button of ["Central Analytics for Organisation","Central Analytics for Industry and User"], verify popup title is "Delete Data Domain Assignment?" and click on "Proceed" button
    Result: "UserSpecificDataDomainAssignmentPage": In "User Specific Data Domain Access:" listing I click "Allow" and verify ["Central Analytics for Organisation","Central Analytics for Industry and User"] found in "userDataDomainCodeAllow" dropdown
    Step: "StanHomePage": I go to "profileTab" and "Sign Out" from the system
    Result: "LogoutPage" : I verify user logout and message displayed as "Sign out successful"

        Examples: {'dataFile':'resources/data/portal_test_data.csv', 'filter':'_ID==\"PTL-INF-CAP-11\"'}


    @PTL-BSE-DDA-12
    @Portal
    @TestCaseId:PTL-BSE-DDA-12
    Scenario Outline: PTL-BSE-DDA-12 Allow / Deny and Delete Data Domain for Attraction Division
    Step: LoginPage: I open Stan url "${env.url}" login as userrole "User Admin" with UUID "<STBAdminUUID>"
    Result: "StanHomePage": I verify user login successfully for:"Stan | Dashboard" with username "<AdminUsername>"
    Step: "Manage STB Users Page": I navigate to "Administration" and click "Manage STB Users"
    Result: "Manage STB Users Page": I verify dashboard page title as "Manage STB Users"
    Step: "Manage STB Users Page": "User Admin" inputs "User" email "<STBUserEmail>" into "user_search_criteria"
    Result: "Manage STB Users Page": I verify "<STBUserEmail>" in "User Listing"
    Step: "User Specific Data Domain Assignment Page": I click on "Data Domain" action
    Result: "User Specific Data Domain Assignment Page": I verify dashboard page title as "User Specific Data Domain Assignment"
    Step: "User Specific Data Domain Assignment Page": I click on "Allow" action
    Result: "User Specific Data Domain Assignment Page": I verify popup with title "Allow User Specific Data Domain Access?"
    Step: "Data Domain Access Popup": I select "userDataDomainCodeAllow" dropdown with value "HOTEL" and click "allowModal" cancel button
    Result: "User Specific Data Domain Assignment Page": In "User Specific Data Domain Access:" listing I click "Allow" and verify ["Hotel"] found in "userDataDomainCodeAllow" dropdown
    Step: "AllowAccess": I click on "Allow" action, verify popup title is "Allow User Specific Data Domain Access?" and selects ["HOTEL"] in "userDataDomainCodeAllow" dropdown and click on "allowAccessButton"
    Result:  "User Specific Data Domain Assignment Page": In "User Specific Data Domain Access:" listing I click "Allow" and verify ["Hotel"] not found in "userDataDomainCodeAllow" dropdown
    Step: "User Specific Data Domain Assignment Page": I click on "Deny" action
    Result: "User Specific Data Domain Assignment Page": I verify popup with title "Deny User Specific Data Domain Access?"
    Step: "Data Domain Access Popup": I select "userDataDomainCodeDeny" dropdown with value "TXI" and click "denyModal" cancel button
    Result: "User Specific Data Domain Assignment Page": In "User Specific Data Domain Access:" listing I click "Deny" and verify ["Tourism Transformation Index (TXI)"] found in "userDataDomainCodeDeny" dropdown
    Step: "DenyAccess": I click on "Deny" action, verify popup title is "Deny User Specific Data Domain Access?" and selects ["TXI"] in "userDataDomainCodeDeny" dropdown and click on "denyAccessButton"
    Result: "User Specific Data Domain Assignment Page": In "User Specific Data Domain Access:" listing I click "Deny" and verify ["Tourism Transformation Index (TXI)"] not found in "userDataDomainCodeDeny" dropdown
    Step:  "User Specific Data Domain Assignment Page": In "User Specific Data Domain Access:" listing I click "Allow" and verify ["Hotel","Tourism Transformation Index (TXI)"] not found in "userDataDomainCodeAllow" dropdown
    Step:  "User Specific Data Domain Assignment Page": In "User Specific Data Domain Access:" listing I click "Deny" and verify ["Hotel","Tourism Transformation Index (TXI)"] not found in "userDataDomainCodeDeny" dropdown
    Step: "StanHomePage": I go to "profileTab" and "Sign Out" from the system
    Result: "LogoutPage" : I verify user logout and message displayed as "Sign out successful"
    Step: LoginPage: I open Stan url "${env.url}" login as userrole "User" with UUID "<STBUserUUID>"
    Result: "StanHomePage": I verify user login successfully for:"Stan | Dashboard" with username "<Username>"
    Step: "Industry Submission": Verify ["Hotel Performance"] sub-menus in "DataSubmission" menu
    Step: "Data Visualisation": Verify ["Hotel Performance"] sub-menus in "DataVisualisation" menu
    Step: "Industry Submission": Verify ["TXI Assessment"] sub-menus not in "DataSubmission" menu
    Step: "Administration": Verify ["Manage TXI Interest"] sub-menus not in "Administration" menu
    Step: "Data Visualisation": Verify ["TXI Assessment"] sub-menus not in "DataVisualisation" menu
    Step: "StanHomePage": I go to "profileTab" and "Sign Out" from the system
    Result: "LogoutPage" : I verify user logout and message displayed as "Sign out successful"
    Step: LoginPage: I open Stan url "${env.url}" login as userrole "User Admin" with UUID "<STBAdminUUID>"
    Result: "StanHomePage": I verify user login successfully for:"Stan | Dashboard" with username "<AdminUsername>"
    Step: "Manage STB Users Page": I navigate to "Administration" and click "Manage STB Users"
    Result: "Manage STB Users Page": I verify dashboard page title as "Manage STB Users"
    Step: "Manage STB Users Page": "User Admin" inputs "User" email "<STBUserEmail>" into "user_search_criteria"
    Result: "Manage STB Users Page": I verify "<STBUserEmail>" in "User Listing"
    Step: "User Specific Data Domain Assignment Page": I click on "Data Domain" action
    Result: "User Specific Data Domain Assignment Page": I verify dashboard page title as "User Specific Data Domain Assignment"
    Step: "User Specific Data Domain Access:": Click on delete button of ["Tourism Transformation Index (TXI)","Hotel"], verify popup title is "Delete Data Domain Assignment?" and click on "Proceed" button
    Result: "User Specific Data Domain Assignment Page": In "User Specific Data Domain Access:" listing I click "Allow" and verify ["Hotel","Tourism Transformation Index (TXI)","Usage Statistics"] found in "userDataDomainCodeAllow" dropdown
    Result: "User Specific Data Domain Assignment Page": In "User Specific Data Domain Access:" listing I click "Deny" and verify ["Hotel","Tourism Transformation Index (TXI)","Usage Statistics"] found in "userDataDomainCodeDeny" dropdown
    Step: "StanHomePage": I go to "profileTab" and "Sign Out" from the system
    Result: "LogoutPage" : I verify user logout and message displayed as "Sign out successful"
    Step: LoginPage: I open Stan url "${env.url}" login as userrole "User" with UUID "<STBUserUUID>"
    Result: "StanHomePage": I verify user login successfully for:"Stan | Dashboard" with username "<Username>"
    Step: "Industry Submission": Verify ["Hotel Performance"] sub-menus not in "DataSubmission" menu
    Step: "Data Visualisation": Verify ["Hotel Performance"] sub-menus in "DataVisualisation" menu
    Step: "Industry Submission": Verify ["TXI Assessment"] sub-menus in "DataSubmission" menu
    Step: "StanHomePage": I go to "profileTab" and "Sign Out" from the system
    Result: "LogoutPage" : I verify user logout and message displayed as "Sign out successful"

        Examples: {'dataFile':'resources/data/portal_test_data.csv', 'filter':'_ID==\"PTL-BSE-DDA-12\"'}


    @PTL-BSE-DDA-13
    @Portal
        @TestCaseId:PTL-BSE-DDA-13
    Scenario Outline: PTL-BSE-DDA-13 -  Allow / Deny Data Domain for Hospitality Division
    Step: LoginPage: I open Stan url "${env.url}" login as userrole "User Admin" with UUID "<STBAdminUUID>"
    Result: "StanHomePage": I verify user login successfully for:"Stan | Dashboard" with username "<AdminUsername>"
    Step: "ManageSTBUsersPage": I navigate to "Administration" and click "Manage STB Users"
    Result: "ManageSTBUsersPage": I verify dashboard page title as "Manage STB Users"
    Step: "DataDomainPage": "User Admin" enters "User" email "<STBUserEmail>" into "user_search_criteria" and click on "Data Domain" action in "User Listing"
    Result: "DataDomainPage": I verify dashboard page title as "User Specific Data Domain Assignment"
    Step: "AllowAccess": I click on "Allow" action, verify popup title is "Allow User Specific Data Domain Access?" and selects ["TXI","HOTEL"] in "userDataDomainCodeAllow" dropdown and click on "allowAccessButton"
    Result: "DataDomainPage": In "User Specific Data Domain Access:" listing I click "Allow" and verify ["Tourism Transformation Index (TXI)","Hotel"] not found in "userDataDomainCodeAllow" dropdown
    Step: "DenyAccess": I click on "Deny" action, verify popup title is "Deny User Specific Data Domain Access?" and selects ["USAGE_STATISTICS"] in "userDataDomainCodeDeny" dropdown and click on "denyAccessButton"
    Result: "DataDomainPage": In "User Specific Data Domain Access:" listing I click "Deny" and verify ["Usage Statistics"] not found in "userDataDomainCodeDeny" dropdown
    Step: "StanHomePage": I go to "profileTab" and "Sign Out" from the system
    Result: "LogoutPage" : I verify user logout and message displayed as "Sign out successful"
    Step: LoginPage: I open Stan url "${env.url}" login as userrole "User" with UUID "<STBUserUUID>"
    Result: "StanHomePage": I verify user login successfully for:"Stan | Dashboard" with username "<Username>"
    Step: "Data Visualisation": Verify ["Hotel Performance", "Tourism Transformation Index (TXI)"] sub-menus in "DataVisualisation" menu
    Step: "Administration": Verify ["Qlik Usage Statistics", "Data Consumption Analytics", "Manage TXI Interest"] sub-menus not in "Administration" menu
    Step: "Industry Submission": Verify ["Hotel Performance", "TXI Assessment"] sub-menus not in "DataSubmission" menu
    Step: "StanHomePage": I go to "profileTab" and "Sign Out" from the system
    Result: "LogoutPage" : I verify user logout and message displayed as "Sign out successful"
    Step: Step: LoginPage: I open Stan url "${env.url}" login as userrole "User Admin" with UUID "<STBAdminUUID>"
    Result: "StanHomePage": I verify user login successfully for:"Stan | Dashboard" with username "<AdminUsername>"
    Step: "ManageSTBUsersPage": I navigate to "Administration" and click "Manage STB Users"
    Step: "DataDomainPage": "User Admin" enters "User" email "<STBUserEmail>" into "user_search_criteria" and click on "Data Domain" action in "User Listing"
    Result: "DataDomainPage": I verify dashboard page title as "User Specific Data Domain Assignment"
    Step: "User Specific Data Domain Access:": Click on delete button of ["Usage Statistics","Tourism Transformation Index (TXI)","Hotel"], verify popup title is "Delete Data Domain Assignment?" and click on "Proceed" button
    Result: "DataDomainPage": In "User Specific Data Domain Access:" listing I click "Allow" and verify ["Tourism Transformation Index (TXI)","Hotel"] found in "userDataDomainCodeAllow" dropdown
    Result: "DataDomainPage": In "User Specific Data Domain Access:" listing I click "Deny" and verify ["Usage Statistics"] found in "userDataDomainCodeDeny" dropdown
    Step: "StanHomePage": I go to "profileTab" and "Sign Out" from the system
    Result: "LogoutPage" : I verify user logout and message displayed as "Sign out successful"
    Step: LoginPage: I open Stan url "${env.url}" login as userrole "User" with UUID "<STBUserUUID>"
    Result: "StanHomePage": I verify user login successfully for:"Stan | Dashboard" with username "<Username>"
    Step: "Administration": Verify ["Manage TXI Interest"] sub-menus not in "Administration" menu
    Step: "Industry Submission": Verify ["Hotel Performance", "TXI Assessment"] sub-menus not in "DataSubmission" menu
    Step: "Administration": Verify ["Qlik Usage Statistics", "Data Consumption Analytics"] sub-menus in "Administration" menu
    Step: "Data Visualisation": Verify ["Hotel Performance", "Tourism Transformation Index (TXI)"] sub-menus in "DataVisualisation" menu
    Step: "StanHomePage": I go to "profileTab" and "Sign Out" from the system
    Result: "LogoutPage" : I verify user logout and message displayed as "Sign out successful"
    Step: LoginPage: I open Stan url "${env.url}" login as userrole "User Admin" with UUID "<STBAdminUUID>"
    Result: "StanHomePage": I verify user login successfully for:"Stan | Dashboard" with username "<AdminUsername>"
    Step: "ManageSTBUsersPage": I navigate to "Administration" and click "Manage STB Users"
    Result: "ManageSTBUsersPage": I verify dashboard page title as "Manage STB Users"
    Step: "DataDomainPage": "User Admin" enters "User" email "<STBUserEmail>" into "user_search_criteria" and click on "Data Domain" action in "User Listing"
    Result: "DataDomainPage": I verify dashboard page title as "User Specific Data Domain Assignment"
    Step: "DenyAccess": I click on "Deny" action, verify popup title is "Deny User Specific Data Domain Access?" and selects ["TXI","HOTEL"] in "userDataDomainCodeDeny" dropdown and click on "denyAccessButton"
    Result: "DataDomainPage": In "User Specific Data Domain Access:" listing I click "Deny" and verify ["Tourism Transformation Index (TXI)","Hotel"] not found in "userDataDomainCodeDeny" dropdown
    Step: "StanHomePage": I go to "profileTab" and "Sign Out" from the system
    Result: "LogoutPage" : I verify user logout and message displayed as "Sign out successful"
    Step: LoginPage: I open Stan url "${env.url}" login as userrole "User" with UUID "<STBUserUUID>"
    Result: "StanHomePage": I verify user login successfully for:"Stan | Dashboard" with username "<Username>"
    Step: "Industry Submission": Verify ["Hotel Performance", "TXI Assessment"] sub-menus not in "DataSubmission" menu
    Step: "Administration": Verify ["Qlik Usage Statistics"] sub-menus not in "Administration" menu
    Step: "Data Visualisation": Verify ["Tourism Transformation Index (TXI)"] sub-menus not in "DataVisualisation" menu
    Step: "Data Visualisation": Verify ["Hotel Performance"] sub-menus in "DataVisualisation" menu
    Step: "StanHomePage": I go to "profileTab" and "Sign Out" from the system
    Result: "LogoutPage" : I verify user logout and message displayed as "Sign out successful"
    Step: LoginPage: I open Stan url "${env.url}" login as userrole "User Admin" with UUID "<STBAdminUUID>"
    Result: "StanHomePage": I verify user login successfully for:"Stan | Dashboard" with username "<AdminUsername>"
    Step: "ManageSTBUsersPage": I navigate to "Administration" and click "Manage STB Users"
    Result: "ManageSTBUsersPage": I verify dashboard page title as "Manage STB Users"
    Step: "DataDomainPage": "User Admin" enters "User" email "<STBUserEmail>" into "user_search_criteria" and click on "Data Domain" action in "User Listing"
    Result: "DataDomainPage": I verify dashboard page title as "User Specific Data Domain Assignment"
    Step: "User Specific Data Domain Access:": Click on delete button of ["Tourism Transformation Index (TXI)","Hotel"], verify popup title is "Delete Data Domain Assignment?" and click on "Proceed" button
    Result: "DataDomainPage": In "User Specific Data Domain Access:" listing I click "Deny" and verify ["Tourism Transformation Index (TXI)","Hotel"] found in "userDataDomainCodeDeny" dropdown
    Step: "StanHomePage": I go to "profileTab" and "Sign Out" from the system
    Result: "LogoutPage" : I verify user logout and message displayed as "Sign out successful"
    Step: LoginPage: I open Stan url "${env.url}" login as userrole "User" with UUID "<STBUserUUID>"
    Result: "StanHomePage": I verify user login successfully for:"Stan | Dashboard" with username "<Username>"
    Step: "Industry Submission": Verify ["Hotel Performance", "TXI Assessment"] sub-menus in "DataSubmission" menu
    Step: "Data Visualisation": Verify ["Hotel Performance","Tourism Transformation Index (TXI)"] sub-menus in "DataVisualisation" menu
    Step: "StanHomePage": I go to "profileTab" and "Sign Out" from the system
    Result: "LogoutPage" : I verify user logout and message displayed as "Sign out successful"

        Examples: {'dataFile':'resources/data/portal_test_data.csv', 'filter':'_ID==\"PTL-BSE-DDA-13\"'}

    @PTL-BSE-DDA-15
    @Portal
    @TestCaseId:PTL-BSE-DDA-15
    Scenario Outline: Promote STB User to STB Admin and Deny Usage Statistics Data Domain
    Step: LoginPage: I open Stan url "${env.url}" login as userrole "User Admin" with UUID "<STBAdminUUID>"
    Result: "StanHomePage": I verify user login successfully for:"Stan | Dashboard" with username "<AdminUsername>"
    Step: "ManageSTBUsersPage": I navigate to "Administration" and click "Manage STB Users"
    Result: "ManageSTBUsersPage": I verify dashboard page title as "Manage STB Users"
    Step: "DataDomainPage": "User Admin" enters "User" email "<STBUserEmail>" into "user_search_criteria" and click on "Data Domain" action in "User Listing"
    Result: "DataDomainPage": I verify dashboard page title as "User Specific Data Domain Assignment"
    Step: "DenyAccess": I click on "Deny" action, verify popup title is "Deny User Specific Data Domain Access?" and selects ["USAGE_STATISTICS"] in "userDataDomainCodeDeny" dropdown and click on "denyAccessButton"
    Result: "DataDomainPage": In "User Specific Data Domain Access:" listing I click "Deny" and verify ["Usage Statistics"] not found in "userDataDomainCodeDeny" dropdown
    Step: "ChangeRole": I verify page title "Stan | Edit STB User Role" and click "Edit" in "User Listing" and change "STB User" to "STB Admin" and click "save-button"
    Result: "ChangeRole": I verify "STB_USER" role changed to "STB_ADMIN" in "User Role" list
    Step: "StanHomePage": I go to "profileTab" and "Sign Out" from the system
    Result: "LogoutPage" : I verify user logout and message displayed as "Sign out successful"
    Step: LoginPage: I open Stan url "${env.url}" login as userrole "User" with UUID "<STBUserUUID>"
    Result: "StanHomePage": I verify user login successfully for:"Stan | Dashboard" with username "<Username>"
    Step: "Data Visualisation": Verify ["Hotel Performance","Tourism Transformation Index (TXI)"] sub-menus in "DataVisualisation" menu
    Step: "Administration": Verify ["Qlik Usage Statistics", "Data Consumption Analytics"] sub-menus not in "Administration" menu
    Step: "StanHomePage": I go to "profileTab" and "Sign Out" from the system
    Result: "LogoutPage" : I verify user logout and message displayed as "Sign out successful"
    Step: LoginPage: I open Stan url "${env.url}" login as userrole "User Admin" with UUID "<STBAdminUUID>"
    Result: "StanHomePage": I verify user login successfully for:"Stan | Dashboard" with username "<AdminUsername>"
    Step: "ManageSTBUsersPage": I navigate to "Administration" and click "Manage STB Users"
    Result: "ManageSTBUsersPage": I verify dashboard page title as "Manage STB Users"
    Step: "DataDomainPage": "User Admin" enters "User" email "<STBUserEmail>" into "user_search_criteria" and click on "Data Domain" action in "User Listing"
    Result: "DataDomainPage": I verify dashboard page title as "User Specific Data Domain Assignment"
    Step: "User Specific Data Domain Access:": Click on delete button of ["Usage Statistics"], verify popup title is "Delete Data Domain Assignment?" and click on "Proceed" button
    Result: "DataDomainPage": In "User Specific Data Domain Access:" listing I click "Deny" and verify ["Usage Statistics"] found in "userDataDomainCodeDeny" dropdown
    Step: "ChangeRole": I verify page title "Stan | Edit STB User Role" and click "Edit" in "User Listing" and change "STB Admin" to "STB User" and click "save-button"
    Result: "ChangeRole": I verify "STB_ADMIN" role changed to "STB_USER" in "User Role" list
    Step: "StanHomePage": I go to "profileTab" and "Sign Out" from the system
    Result: "LogoutPage" : I verify user logout and message displayed as "Sign out successful"
    Step: LoginPage: I open Stan url "${env.url}" login as userrole "User" with UUID "<STBUserUUID>"
    Result: "StanHomePage": I verify user login successfully for:"Stan | Dashboard" with username "<Username>"
    Step: "Data Visualisation": Verify ["Hotel Performance","Tourism Transformation Index (TXI)"] sub-menus in "DataVisualisation" menu
    Step: "Industry Submission": Verify ["Hotel Performance", "TXI Assessment"] sub-menus in "DataSubmission" menu
    Step: "Administration": Verify ["Qlik Usage Statistics", "Data Consumption Analytics"] sub-menus not in "Administration" menu
    Step: "StanHomePage": I go to "profileTab" and "Sign Out" from the system
    Result: "LogoutPage" : I verify user logout and message displayed as "Sign out successful"

        Examples: {'dataFile':'resources/data/portal_test_data.csv', 'filter':'_ID==\"PTL-BSE-DDA-15\"'}

    @PTL-BSE-DDA-16
    @Portal
    @TestCaseId:PTL-BSE-DDA-16
    Scenario Outline: Allow / Deny and Delete Data Domain for Americas Division
    Step: LoginPage: I open Stan url "${env.url}" login as userrole "User Admin" with UUID "<STBAdminUUID>"
    Result: "StanHomePage": I verify user login successfully for:"Stan | Dashboard" with username "<AdminUsername>"
    Step: "ManageSTBUsersPage": I navigate to "Administration" and click "Manage STB Users"
    Result: "ManageSTBUsersPage": I verify dashboard page title as "Manage STB Users"
    Step: "DataDomainPage": "User Admin" enters "User" email "<STBUserEmail>" into "user_search_criteria" and click on "Data Domain" action in "User Listing"
    Result: "DataDomainPage": I verify dashboard page title as "User Specific Data Domain Assignment"
    Step: "AllowAccess": I click on "Allow" action, verify popup title is "Allow User Specific Data Domain Access?" and selects ["TXI","USAGE_STATISTICS","HOTEL"] in "userDataDomainCodeAllow" dropdown and click on "allowAccessButton"
    Result: "DataDomainPage": In "User Specific Data Domain Access:" listing I click "Allow" and verify ["TXI","USAGE_STATISTICS","HOTEL"] not found in "userDataDomainCodeAllow" dropdown
    Step: "StanHomePage": I go to "profileTab" and "Sign Out" from the system
    Result: "LogoutPage" : I verify user logout and message displayed as "Sign out successful"
    Step: LoginPage: I open Stan url "${env.url}" login as userrole "User" with UUID "<STBUserUUID>"
    Result: "StanHomePage": I verify user login successfully for:"Stan | Dashboard" with username "<Username>"
    Step: "Industry Submission": Verify ["Hotel Performance", "TXI Assessment"] sub-menus in "DataSubmission" menu
    Step: "Administration": Verify ["Manage TXI Interest","Data Consumption Analytics","Qlik Usage Statistics"] sub-menus in "Administration" menu
    Step: "Data Visualisation": Verify ["Tourism Transformation Index (TXI)"] sub-menus in "DataVisualisation" menu
    Step: "StanHomePage": I go to "profileTab" and "Sign Out" from the system
    Result: "LogoutPage" : I verify user logout and message displayed as "Sign out successful"
    Step: LoginPage: I open Stan url "${env.url}" login as userrole "User Admin" with UUID "<STBAdminUUID>"
    Result: "StanHomePage": I verify user login successfully for:"Stan | Dashboard" with username "<AdminUsername>"
    Step: "ManageSTBUsersPage": I navigate to "Administration" and click "Manage STB Users"
    Result: "ManageSTBUsersPage": I verify dashboard page title as "Manage STB Users"
    Step: "DataDomainPage": "User Admin" enters "User" email "<STBUserEmail>" into "user_search_criteria" and click on "Data Domain" action in "User Listing"
    Result: "DataDomainPage": I verify dashboard page title as "User Specific Data Domain Assignment"
    Step: "User Specific Data Domain Access:": Click on delete button of ["Usage Statistics","Tourism Transformation Index (TXI)","Hotel"], verify popup title is "Delete Data Domain Assignment?" and click on "Proceed" button
    Result: "DataDomainPage": In "User Specific Data Domain Access:" listing I click "Allow" and verify ["Usage Statistics","Tourism Transformation Index (TXI)","Hotel"] found in "userDataDomainCodeAllow" dropdown
    Step: "StanHomePage": I go to "profileTab" and "Sign Out" from the system
    Result: "LogoutPage" : I verify user logout and message displayed as "Sign out successful"

        Examples: {'dataFile':'resources/data/portal_test_data.csv', 'filter':'_ID==\"PTL-BSE-DDA-16\"'}

    @PTL-INF-TPD-17
    @Portal
        @TestCaseId:PTL-INF-TPD-17
    Scenario Outline: PTL-INF-TPD-17 TIH Performance Dashboard - T2 Industry User (org contribute data to TIH)
    Step: LoginPage: I open Stan url "${env.url}" login as userrole "T2 Industry User" with UUID "<T2IndustryUserUUID>"
    Result: "StanHomePage": I verify user login successfully for:"Stan | Dashboard" with username "<Username>"
    Step: "StanHomePage": I hover on "Data Visualisation" and click "TIH Performance" sub-menu in "DataVisualisation"
    Result: "TIHPerformanceDashboard": I verify dashboard open in another window "TIH Performance - Data Contributors | App overview - Qlik Sense"
    Step: "TravelAgentsDashboard": I close Qlik Dashboard and switch to home page
    Step: "StanHomePage":I Click on "TIH Performance" Link in my dashboard
    Result: "TIHPerformanceDashboard": I verify dashboard open in another window "TIH Performance - Data Contributors | App overview - Qlik Sense"
    Step: "TravelAgentsDashboard": I close Qlik Dashboard and switch to home page
    Step: "StanHomePage": I go to "profileTab" and "Sign Out" from the system
    Result: "LogoutPage" : I verify user logout and message displayed as "Sign out successful"

        Examples: {'dataFile':'resources/data/portal_test_data.csv', 'filter':'_ID==\"PTL-INF-TPD-17\"'}

    @PTL-INF-TPD-18
    @Portal
        @TestCaseId:PTL-INF-TPD-18
    Scenario Outline: PTL-INF-TPD-18 TIH Performance Dashboard - T2 Industry User (org NOT contribute data to TIH)
    Step: LoginPage: I open Stan url "${env.url}" login as userrole "T2 Industry User No data to TIH" with UUID "<T2IndustryUserNOdatacontributedto'TIH'UUID>"
    Result: "StanHomePage": I verify user login successfully for:"Stan | Dashboard" with username "<Username>"
    Step: "StanHomePage": I hover on "Data Visualisation" and click "TIH Performance" sub-menu in "DataVisualisation"
    Result: "TIHPerformanceDashboard": I verify dashboard open in another window "TIH Performance | App overview - Qlik Sense"
    Step: "TravelAgentsDashboard": I close Qlik Dashboard and switch to home page
    Step: "StanHomePage":I Click on "TIH Performance" Link in my dashboard
    Result: "TIHPerformanceDashboard": I verify dashboard open in another window "TIH Performance | App overview - Qlik Sense"
    Step: "TravelAgentsDashboard": I close Qlik Dashboard and switch to home page
    Step: "StanHomePage": I go to "profileTab" and "Sign Out" from the system
    Result: "LogoutPage" : I verify user logout and message displayed as "Sign out successful"

        Examples: {'dataFile':'resources/data/portal_test_data.csv', 'filter':'_ID==\"PTL-INF-TPD-18\"'}


    @PTL-BSE-HLD-2
        @Portal
        @TestCaseId:PTL-BSE-HLD-2
    Scenario Outline: PTL-BSE-HLD-2 - Access Hotel Performance dashboard - Industry Dashboard - Industry Admin Tier 1
    Step: LoginPage: I open Stan url "${env.url}" login as userrole "T1 Industry Admin" with UUID "<T1IndustryAdminUUID>"
    Result: "StanHomePage": I verify user login successfully for:"Stan | Dashboard" with username "<AdminUsername>"
    Step: "StanHomePage": I hover on "Data Visualisation" and click "Hotel Performance" sub-menu in "DataVisualisation"
    Result: "HotelPerformanceDashboard": I verify dashboard open in another window "Hotel Performance | App overview - Qlik Sense"
    Step: "HotelPerformanceDashboard": I close Qlik Dashboard and switch to home page
    Step: "StanHomePage": I go to "profileTab" and "Sign Out" from the system
    Result: "LogoutPage" : I verify user logout and message displayed as "Sign out successful"

        Examples: {'dataFile':'resources/data/portal_test_data.csv', 'filter':'_ID==\"PTL-BSE-HLD-2\"'}

    @PTL-BSE-HLD-3
        @Portal
        @TestCaseId:PTL-BSE-HLD-3
    Scenario Outline: PTL-BSE-HLD-3 - Access Hotel Performance dashboard - Public Dashboard - Industry User Tier 2
    Step: LoginPage: I open Stan url "${env.url}" login as userrole "T2 Industry User" with UUID "<T2IndustryUserUUID>"
    Result: "StanHomePage": I verify user login successfully for:"Stan | Dashboard" with username "<Username>"
    Step: "StanHomePage": I hover on "Data Visualisation" and click "Hotel Performance" sub-menu in "DataVisualisation"
    Result: "HotelPerformanceDashboard": I verify dashboard open in another window "Hotel Performance | App overview - Qlik Sense"
    Step: "HotelPerformanceDashboard": I close Qlik Dashboard and switch to home page
    Step: "StanHomePage": I go to "profileTab" and "Sign Out" from the system
    Result: "LogoutPage" : I verify user logout and message displayed as "Sign out successful"

        Examples: {'dataFile':'resources/data/portal_test_data.csv', 'filter':'_ID==\"PTL-BSE-HLD-3\"'}

    @PTL-BSE-HLD-4
        @Portal
        @TestCaseId:PTL-BSE-HLD-4
    Scenario Outline: PTL-BSE-HLD-4 Access Hotel Performance dashboard - Public Dashboard - Industry Sub-Admin Tier 3
    Step: LoginPage: I open Stan url "${env.url}" login as userrole "Industry T3 Sub-Admin 1" with UUID "<T3IndustrySubAdminHotelUUID>"
    Result: "StanHomePage": I verify user login successfully for:"Stan | Dashboard" with username "<AdminUsername>"
    Step: "StanHomePage": I hover on "Data Visualisation" and click "Hotel Performance" sub-menu in "DataVisualisation"
    Result: "HotelPerformanceDashboard": I verify "Hotel Performance" "industry" dashboard open in another window "Hotel Performance – Tourism Industry | App overview - Qlik Sense"
    Step: "HotelPerformanceDashboard": I close Qlik Dashboard and switch to home page
    Step: "StanHomePage": I go to "profileTab" and "Sign Out" from the system
    Result: "LogoutPage" : I verify user logout and message displayed as "Sign out successful"
    Step: LoginPage: I open Stan url "${env.url}" login as userrole "Industry T3 Sub-Admin 2" with UUID "<T3IndustrySubAdminNonHotelUUID>"
    Result: "StanHomePage": I verify user login successfully for:"Stan | Dashboard" with username "<Username>"
    Step: "StanHomePage": I hover on "Data Visualisation" and click "Hotel Performance" sub-menu in "DataVisualisation"
    Result: "HotelPerformanceDashboard": I verify "Hotel Performance" "public" dashboard open in another window "Hotel Performance | App overview - Qlik Sense"
    Step: "HotelPerformanceDashboard": I close Qlik Dashboard and switch to home page
    Step: "StanHomePage": I go to "profileTab" and "Sign Out" from the system
    Result: "LogoutPage" : I verify user logout and message displayed as "Sign out successful"

        Examples: {'dataFile':'resources/data/portal_test_data.csv', 'filter':'_ID==\"PTL-BSE-HLD-4\"'}

    @PTL-INF-HLD-5
        @Portal
        @TestCaseId:PTL-INF-HLD-5
    Scenario Outline: PTL-BSE-HLD-5 Hotel Landscape Dashboard - T1 Industry Admin
    Step: LoginPage: I open Stan url "${env.url}" login as userrole "Industry T1 Admin 2" with UUID "<T1IndustryAdminUUID>"
    Result: "StanHomePage": I verify user login successfully for:"Stan | Dashboard" with username "<AdminUsername>"
    Step: "StanHomePage": I hover on "Data Visualisation" and click "Hotel Landscape" sub-menu in "DataVisualisation"
    Result: "HotelPerformanceDashboard": I verify "Hotel Landscape" "public" dashboard open in another window "Hotel Landscape | App overview - Qlik Sense"
    Step: "HotelPerformanceDashboard": I close Qlik Dashboard and switch to home page
    Step: "StanHomePage":I Click on "Hotel Landscape" Link in my dashboard
    Result: "HotelPerformanceDashboard": I verify "Hotel Landscape" "public" dashboard open in another window "Hotel Landscape | App overview - Qlik Sense"
    Step: "HotelPerformanceDashboard": I close Qlik Dashboard and switch to home page
    Step: "StanHomePage": I go to "profileTab" and "Sign Out" from the system
    Result: "LogoutPage" : I verify user logout and message displayed as "Sign out successful"

        Examples: {'dataFile':'resources/data/portal_test_data.csv', 'filter':'_ID==\"PTL-BSE-HLD-5\"'}

    @PTL-INF-HLD-6
        @Portal
        @TestCaseId:PTL-INF-HLD-6
    Scenario Outline: PTL-BSE-HLD-6 Access Hotel Landscape Dashboard - STB Management
    Step: LoginPage: I open Stan url "${env.url}" login as userrole "STB Management" with UUID "<STBManagementUUID>"
    Result: "StanHomePage": I verify user login successfully for:"Stan | Dashboard" with username "<AdminUsername>"
    Step: "StanHomePage": I hover on "Data Visualisation" and click "Hotel Landscape" sub-menu in "DataVisualisation"
    Result: "HotelPerformanceDashboard": I verify "Hotel Landscape" "sensitive" dashboard open in another window "Hotel Landscape - STB | App overview - Qlik Sense"
    Step: "HotelPerformanceDashboard": I close Qlik Dashboard and switch to home page
    Step: "StanHomePage":I Click on "Hotel Landscape" Link in my dashboard
    Result: "HotelPerformanceDashboard": I verify "Hotel Landscape" "public" dashboard open in another window "Hotel Landscape - STB | App overview - Qlik Sense"
    Step: "HotelPerformanceDashboard": I close Qlik Dashboard and switch to home page
    Step: "StanHomePage": I go to "profileTab" and "Sign Out" from the system
    Result: "LogoutPage" : I verify user logout and message displayed as "Sign out successful"

        Examples: {'dataFile':'resources/data/portal_test_data.csv', 'filter':'_ID==\"PTL-BSE-HLD-6\"'}