package pages;
import base.TestBasis;
import helpers.RandomStringGenerator;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeTest;

import java.util.Random;

import static io.restassured.RestAssured.basic;

public class OrderCorrection extends TestBasis{
    public static String URL = "urlToPage";
    private  String correctionComments;
    private  String employeeUsername;
    private  String correctionType;
    private  String holdCode;
    private  String sourceSystem;
    private  String sourceSystemOrderNumber;
    private  String shipAfterDate;
    private  String expediteType;

    public String getExpediteType() {
        return expediteType;
    }

    public void setExpediteType(String expediteType) {
        this.expediteType = expediteType;
    }

    public String getShipAfterDate() {
        return shipAfterDate;
    }

    public void setShipAfterDate(String shipAfterDate) {
        this.shipAfterDate = shipAfterDate;
    }

    public String getHoldCode() {
        return holdCode;
    }

    public void setHoldCode(String holdCode) {
        this.holdCode = holdCode;
    }

    public String getCorrectionComments() {
        return correctionComments;
    }

    public void setCorrectionComments(String correctionComments) {
        this.correctionComments = correctionComments;
    }

    public String getEmployeeUsername() {
        return employeeUsername;
    }

    public void setEmployeeUsername(String employeeUsername) {
        this.employeeUsername = employeeUsername;
    }

    public String getCorrectionType() {
        return correctionType;
    }

    public void setCorrectionType(String correctionType) {
        this.correctionType = correctionType;
    }

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getSourceSystemOrderNumber() {
        return sourceSystemOrderNumber;
    }

    public void setSourceSystemOrderNumber(String sourceSystemOrderNumber) {
        this.sourceSystemOrderNumber = sourceSystemOrderNumber;
    }


    public Object createQOCGeneral(String comments,String type, String name,String exp_type,String hold,String ship, String srcSystem, RandomStringGenerator.Mode orderNum){
        OrderCorrection fields = new OrderCorrection();
        fields.setCorrectionComments(comments);
        fields.setCorrectionType(type);
        fields.setEmployeeUsername(name);
        fields.setExpediteType(exp_type);
        fields.setHoldCode(hold);
        fields.setShipAfterDate(ship);
        fields.setSourceSystem(srcSystem);
        fields.setSourceSystemOrderNumber(RandomStringGenerator.generateRandomString(6, orderNum));
        return fields;
    }

}
