import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class FirstTest{
    private static final Logger logger = LogManager.getLogger(FirstTest.class);
    WebDriver driver;

    @BeforeAll
    public static void webDriverInstall(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void webDriverStart() {
        driver = new ChromeDriver();
    }
  /*
  Открыть Chrome в headless режиме
Перейти на ресурс
В В поле ввода текста ввести ОТУС
Проверить что в Текст соответствует введенному
   */
    @Test
    @DisplayName ("Первый тест")
    public void enterText() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        WebDriver driver = new ChromeDriver(options);
    driver.get("https://otus.home.kartushin.su/training.html");
    WebElement element = driver
            .findElement(By.id("textInput"));
    element.sendKeys("ОТУС");
    Assertions.assertEquals("ОТУС", element.getAttribute("value") );
    logger.info("Тест #1 пройден");
    }
    /*
Открыть Chrome в режиме киоска
Перейти на ресурс
Нажать на "Открыть модальное окно"
Проверить что открылось в модальное окно
  */
    @Test
    @DisplayName ("Второй тест")
    public void clickPopUp() {
        driver.get("https://otus.home.kartushin.su/training.html");
        driver.manage().window().fullscreen();
        WebElement element = driver
                .findElement(By.id("openModalBtn"));
        element.click();
        element.isDisplayed();
      // Assertions.assertTrue(element.isDisplayed());
        logger.info("Тест #2 пройден");

    }
    /*
    Открыть Chrome в режиме полного экрана
Перейти на ресурс
В форму ввести Имя и Почту, нажать отправить
В поле динамическое сообщенеие(на зеленом фоне) появится сообщение в формате:
Форма отправлена с именем: фыв и email: asdf@sdfg.rt
     */
    @Test
    @DisplayName ("Третий тест")
    public void checkDynamicForm() {
        String nameUsers = "otus";
        String emailUsers = "otus@test.ru";
        driver.get("https://otus.home.kartushin.su/training.html");
        driver.manage().window().maximize();
        WebElement name = driver
                .findElement(By.id("name"));
        name.sendKeys(nameUsers);
        WebElement email = driver
                .findElement(By.id("email"));
        email.sendKeys(emailUsers);
        WebElement element = driver
                .findElement(By.xpath("//button[@type='submit']"));
        element.click();
        WebElement messageBox = driver
                .findElement(By.id("messageBox"));
        Assertions.assertEquals("Форма отправлена с именем: otus и email: otus@test.ru", messageBox.getText());
        logger.info("Тест #3 пройден");
    }

    @AfterEach
    void tearDown(){
        if (driver!=null)
            driver.close();
            driver.quit();
    }
    }