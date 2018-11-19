package test;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.testng.ISuite;
import org.testng.ISuiteListener;

public class SuiteListener implements ISuiteListener{
    @Override
    public void onStart(ISuite iSuite) {
        RestAssured.filters(new AllureRestAssured());
    }

    @Override
    public void onFinish(ISuite iSuite) {

    }
}
