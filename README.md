# Overview
This project contains two JUnit automation test cases using **Java, Selenium WebDriver**, and **Gradle**.

## Task 1: Web Form Automation

**URL:** https://demo.wpeverest.com/user-registration/guest-registration-form/

### Steps Performed:
1. Filled:
   - First Name
   - Last Name
   - Email
   - Gender
   - Date of Birth
   - Nationality
   - Phone
   - Country (Bangladesh)
   - Accepted Terms & Conditions
2. Clicked the submit button
3. Verified successful registration message

### How to Run:
```bash
./gradlew test --tests "RegistrationTest"
```

## Task 2: Table Data Scraping

**URL:** https://dsebd.org/latest_share_price_scroll_by_value.php

### Steps Performed:
1. Located the stock price table
2. Extracted all cell values
3. Printed data in console
4. Saved all table data to:
   - `scrappedData.csv`

### How to Run:
```bash
./gradlew test --tests "ScrappingDataTest"
```


### Both Test Report Screenshots:
![Form Test Report](https://drive.google.com/file/d/1QyKyn1auHRJNDKQHlJHxngUYBwasd_K-/view?usp=drive_link)
![Scrapping Data 1](https://drive.google.com/file/d/168SDp0HKu3Oi3TQ_gzb2L2BuEAg2ZZHo/view?usp=drive_link)
![Scrapping Data 2](https://drive.google.com/file/d/1YctAy8z4KbFxddrpNwP2l-1Qb4aMx_3F/view?usp=drive_link)

### Demo Video:
📹 [Watch Demo](#) *(https://drive.google.com/file/d/1lC_aiDj8G_iiy4bgMjmLqqlT7DkUkihU/view?usp=drive_link)*

---
## .gitignore Contents

```gitignore
.gradle/
.idea/
build/
logs/
*.iml
chromedriver.exe
```

---
