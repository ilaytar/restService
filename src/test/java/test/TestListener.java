package test;

import com.epam.reportportal.testng.ReportPortalTestNGListener;
import org.apache.log4j.Logger;
import org.testng.ITestResult;

public class TestListener extends ReportPortalTestNGListener {

    private Logger logger = Logger.getLogger(this.getClass());

    @Override
    public void beforeConfiguration(ITestResult testResult) {
        logger.info("beforeConfiguration");
    }

    @Override
    public void onConfigurationFailure(ITestResult testResult) {
        logger.info("onConfigurationFailure");
    }

    @Override
    public void onConfigurationSuccess(ITestResult testResult) {
        logger.info("onConfigurationSuccess");
    }

    @Override
    public void onConfigurationSkip(ITestResult testResult) {
        logger.info("onConfigurationSkip");
    }
}
