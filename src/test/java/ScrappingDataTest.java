import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ScrappingDataTest {

    WebDriver driver;
    private BufferedWriter writer;

    @BeforeAll
    public void setUp() throws IOException {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://dsebd.org/latest_share_price_scroll_by_value.php");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("table.table-bordered tbody tr td")));

        writer = new BufferedWriter(new FileWriter("C:\\Users\\biswa\\Desktop\\Selenium Project by JUnit\\scrappedData.csv"));
        writer.write("Sl,Trading Code,LTP,High,Low,Close,Change %,Trades,Value (mn),Volume");
        writer.newLine();
    }

    @Test
    public void scrapeTableData() throws IOException {
        System.out.println("Extracting table content using JavaScript");

        String script = """
        let rows = document.querySelectorAll('table.table-bordered tbody');
        let data = [];
        for (let i = 0; i < rows.length; i++) {
            let cells = rows[i].querySelectorAll('td');
            let row = [];
            for (let c of cells) {
                row.push(c.innerText.trim().replaceAll(',', ''));
            }
            if (row.length > 0) data.push(row);
        }
        return data;
    """;

        @SuppressWarnings("unchecked")
        List<List<String>> tableData = (List<List<String>>) ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript(script);

        System.out.println(" Rows extracted: " + tableData.size());

        for (List<String> row : tableData) {
            String line = String.join(",", row);
            writer.write(line);
            writer.newLine();
            System.out.println(" " + line);
        }
    }


//    @AfterAll
//    public void tearDown() throws IOException {
//        if (writer != null) {
//            writer.flush();
//            writer.close();
//        }
//        if (driver != null) {
//            driver.quit();
//        }
//    }
}




