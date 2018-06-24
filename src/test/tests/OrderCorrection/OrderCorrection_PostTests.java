package OrderCorrection;


import helpers.RandomStringGenerator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.OrderCorrection;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.TestCaseId;


import static helpers.RandomStringGenerator.Mode.NUMERIC;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isEmptyString;

public class OrderCorrection_PostTests extends OrderCorrection {
    @DataProvider
    public Object[][] testData() {
        return new Object[][]{
                new Object[]{null, "EXPEDITE_ORDER", "application", "EXPEDITE_LEVEL_0", "1", null, "FLOW", NUMERIC},
                new Object[]{null, "EXPEDITE_ORDER", "application", "EXPEDITE_LEVEL_0", null, "2018-02-03", "FLOW", NUMERIC},
                new Object[]{null, "HOLD_ORDER", "application", "EXPEDITE_LEVEL_0", "1", null, "FLOW", NUMERIC},
                new Object[]{null, "HOLD_ORDER", "application", null, "1", "2018-02-03", "FLOW", NUMERIC},
                new Object[]{null, "SHIP_AFTER", "application", "EXPEDITE_LEVEL_0", null, "2018-02-03", "FLOW", NUMERIC},
                new Object[]{null, "SHIP_AFTER", "application", null, "1", "2018-02-03", "FLOW", NUMERIC},
                new Object[]{null, "SHIP_AFTER", "application", "EXPEDITE_LEVEL_0", "1", "2018-02-03", "FLOW", NUMERIC},

        };
    }

    @Test(dataProvider = "testData", groups = {"400Error"})
    @TestCaseId("1.0")
    @Features("QOC_NegativeCases_400Error")
    public void qocWrongFieldForCorrection(String comments, String type, String name, String exp_type, String hold, String ship, String srcSystem, RandomStringGenerator.Mode orderNum) {
        given().contentType("application/json")
                .body(createQOCGeneral(comments, type, name, exp_type, hold, ship, srcSystem, orderNum))
                .when().post(URL).then()
                .statusCode(400)
                .assertThat().body(equalTo("[{\"message\":\"Wrong fields set for selected Correction Type\",\"path\":\"create.orderCorrectionCreateRequest\"}]"));
    }

    @Test(groups = {"400Error"})
    @TestCaseId("1.1")
    @Features("QOC_NegativeCases_400Error")
    public void qocEmptyPostTest() {
        given().contentType("application/json")
                .body("{}")
                .when().post(URL).then()
                .statusCode(400)
                .assertThat().body(contains("[{\"message\":\"may not be empty\",\"path\":\"create.orderCorrectionCreateRequest.sourceSystem\"}," +
                "{\"message\":\"may not be empty\",\"path\":\"create.orderCorrectionCreateRequest.employeeUsername\"}," +
                "{\"message\":\"may not be empty\",\"path\":\"create.orderCorrectionCreateRequest.sourceSystemOrderNumber\"}," +
                "{\"message\":\"Wrong fields set for selected Correction Type\",\"path\":\"create.orderCorrectionCreateRequest\"}," +
                "{\"message\":\"may not be empty\",\"path\":\"create.orderCorrectionCreateRequest.correctionType\"}]"))
                .log().body();
    }

    @Test(groups = {"Positive"})
    @TestCaseId("1.2")
    @Features("QOC_PositiveCases_CreationOfCorrections")
    public void qocHoldPostTest() {
        given().contentType("application/json")
                .body(createQOCGeneral("test abc", "HOLD_ORDER", "application", null, "1", null, "ACT", NUMERIC))
                .when().post(URL).then()
                .statusCode(200)
                .log().body().assertThat().body(isEmptyString());
    }

    @Test(groups = {"Positive"})
    @TestCaseId("1.3")
    @Features("QOC_PositiveCases_CreationOfCorrections")
    public void qocExpPostTest() {
        given().contentType("application/json")
                .body(createQOCGeneral("test abc", "EXPEDITE_ORDER", "application", "EXPEDITE_LEVEL_0", null, null, "ACT", NUMERIC))
                .when().post(URL).then()
                .statusCode(200)
                .log().body().assertThat().body(isEmptyString());
    }

    @Test(groups = {"Positive"})
    @TestCaseId("1.4")
    @Features("QOC_PositiveCases_CreationOfCorrections")
    public void qocShipAfterPostTest() {
        given().contentType("application/json")
                .body(createQOCGeneral("test abc", "SHIP_AFTER", "application", null, null, "2018-02-22", "ACT", NUMERIC))
                .when().post(URL).then()
                .statusCode(200)
                .log().body().assertThat().body(isEmptyString());
    }

    @Test(groups = {"400Error"})
    @TestCaseId("1.5")
    @Features("QOC_NegativeCases_400Error")
    public void qocPostTestNegative() {
        given().contentType("application/json")
                .body(createQOCGeneral("test abc", "HOLD_ORDERttt", "application", null, "1", null, "ACT", NUMERIC))
                .when().post(URL).then()
                .statusCode(400)
                .assertThat().body(equalTo("[{\"message\":\"Unknown type value\",\"path\":\"create.orderCorrectionCreateRequest.correctionType\"},{\"message\":\"Wrong fields set for selected Correction Type\",\"path\":\"create.orderCorrectionCreateRequest\"}]"));
    }
}
