package OrderCorrection;

import org.testng.annotations.Test;
import pages.OrderCorrection;
import ru.yandex.qatools.allure.annotations.Features;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class OrderCorrection_GetTests extends OrderCorrection {

    @Test(groups = {"400Error"})
    @Features("QOC_NegativeCases_400Error")
    public void testStatusCode() {
        given().get(URL).then()
                .statusCode(400).assertThat()
                .body(equalTo("[{\"message\":\"Required  parameter 'orderHeaderId or sourceSystemOrderNumber,sourceSystem' is not present\"}]"));
    }

    @Test(enabled = false)
    public void qocGetReq_sourceSystemOrderNumber() {
        given().get(URL + "?sourceSystemOrderNumber=" + "44444" + "&sourceSystem=" + "ACT").then()
                .statusCode(200).log().body();
    }

    @Test(groups = {"Positive"})
    public void qocGetReq_OrderHeaderId() {
        given().get(URL + "?orderHeaderId=" + 49).then()
                .statusCode(200).log().body();
    }
}
