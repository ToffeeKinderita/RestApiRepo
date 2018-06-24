package OrderCorrection;


import org.testng.annotations.Test;
import pages.OrderCorrection;
import ru.yandex.qatools.allure.annotations.Features;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static pages.OrderCorrection.URL;

public class Test_OrderCorrection_Delete extends OrderCorrection {

    @Test(groups = {"Positive"})
    @Features("QOC_DeleteCase")
    public void qocDeletePositive_test() {
        given().delete(URL + "?orderCorrectionIds=" + 328)
                .then()
                .statusCode(200);
    }

    @Test(groups = {"400Error"})
    @Features("QOC_NegativeCases_400Error")
    public void qocDeleteNegative() {
        given().delete(URL + "?orderCorrectionIds=" + "aaa")
                .then()
                .statusCode(400)
                .assertThat().body(equalTo("[{\"message\":\"Failed to convert value of type 'java.lang.String' to required type 'java.util.List'; nested exception is java.lang.NumberFormatException: For input string: \\\"aaa\\\"\"}]"));
    }

    @Test(groups = {"400Error"})
    @Features("QOC_NegativeCases_400Error")
    public void qocDeleteNegativeNUM() {
        given().delete(URL + "?orderCorrectionIds=" + 000)
                .then()
                .statusCode(204)
                .assertThat().body(isEmptyString());
    }
}
