# OrangeHRM Test Automation Framework

A professional UI test automation framework built to validate end-to-end user journeys and critical HR workflows on the OrangeHRM platform. The project is designed with scalability and high maintainability in mind.

## 🚀 Key Features

* **Page Object Model (POM):** Complete decoupling of UI elements and test scripts.
* **Fluent/Chained Architecture:** Enhances test script readability through continuous method invocation.
* **Robust Component Isolation:** Modulated folder distribution for discrete platform views (Admin, PIM, MyInfo, Time).
* **Interactive Reporting:** Native integrations for generating granular execution metrics.

---

## 🛠️ Tech Stack & Prerequisites

* **Language:**  Java 25
* **Core Framework:** Selenium WebDriver (v4.39.0)
* **Test Engine:** TestNG (v7.10.2)
* **Build & Dependency Management:** Maven
* **Reporting:** Allure Reports (v2.35.2)

---

## 📁 Project Structure

```text
OrangeHRM/
├── src/
│   ├── main/java/org/example/         # Page Objects & Operational Elements
│   │   ├── admin/
│   │   ├── myinfo/
│   │   ├── pim/
│   │   ├── time/
│   │   └── pages/
│   └── test/java/                     # Execution Scripts & Testing Assertions
│       ├── base/
│       ├── employeeDependents/
│       ├── employeeProfile/
│       ├── employeeReports/
│       ├── reuse/
│       ├── timeSheet/
│       └── userAssignment/
├── pom.xml                            # Project Object Model dependencies
└── Testing.xml                        # TestNG suite mapping distribution
💻 Local Setup & Execution
1. Clone the Repository
Bash
git clone [https://github.com/andrewhisham2013-jpg/OrangeHrm.git](https://github.com/andrewhisham2013-jpg/OrangeHrm.git)
cd OrangeHrm
2. Execution Methods
Option A: Running via the Command Line (CMD)
Open your terminal in the project root directory and run the sequential test suite layout using Maven:

Bash
mvn clean test -DsuiteXmlFile=Testing.xml
Option B: Running via the Testing.xml File (IDE)
Open the project in IntelliJ IDEA or Eclipse.

Locate the Testing.xml file in the root directory.

Right-click directly on the Testing.xml file.

Select Run '...\Testing.xml' to trigger the sequential TestNG suite execution natively in the editor.

3. Generate Allure Report
Visualize the details of your test suite results locally after execution finishes:

Bash
allure serve allure-results


---

## 🎬 Framework Demo Video

To watch the comprehensive framework walkthrough, execution suites, and see how the Allure report is generated, check out our stable release presentation:

👉 **[Watch the Framework Execution Demo Video Here](https://github.com/andrewhisham2013-jpg/OrangeHrm/releases/tag/v1.0.0)**

---
