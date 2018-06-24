package base;



import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeMethod;
import io.restassured.RestAssured;
import java.net.MalformedURLException;

import static io.restassured.RestAssured.basic;

public class TestBasis {
    public static String URL_LoginPage = "url";
    public static String username = "user";
    public static String password = "pass";

    @BeforeMethod(alwaysRun = true)
    public void setup() throws MalformedURLException {
        RestAssured.authentication = basic(username, password);
        String log4jConfPath = "D:\\RestApi\\src\\main\\resources\\lod4j.properties";
        PropertyConfigurator.configure(log4jConfPath);
    }
}


