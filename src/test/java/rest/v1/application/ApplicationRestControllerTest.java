package rest.v1.application;

import org.apache.log4j.Logger;
import static org.hamcrest.CoreMatchers.is;
import org.json.JSONException;
import org.json.JSONObject;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import ai.elimu.model.enums.Language;
import ai.elimu.rest.v1.VersionRestController;
import ai.elimu.util.JsonLoader;
import selenium.DomainHelper;

public class ApplicationRestControllerTest {
    
    private Logger logger = Logger.getLogger(getClass());

    @Test(expected = JSONException.class)
    public void testList_missingParameters() {
    	String jsonResponse = JsonLoader.loadJson(DomainHelper.getRestUrlV1() + "/application/list");
        logger.info("jsonResponse: " + jsonResponse);
        JSONObject jsonObject = new JSONObject(jsonResponse);
    }
    
    @Test
    public void testList_success() {
        String jsonResponse = JsonLoader.loadJson(DomainHelper.getRestUrlV1() + "/application/list" +
                "?deviceId=abcdef123456" + 
                "&language=" + Language.EN + 
                "&deviceModel=Google+Pixel+C" + 
                "&osVersion=" + VersionRestController.MINIMUM_OS_VERSION + 
                "&applicationId=ai.elimu.appstore" + 
                "&appVersionCode=" + VersionRestController.NEWEST_VERSION_APPSTORE);
        logger.info("jsonResponse: " + jsonResponse);
        JSONObject jsonObject = new JSONObject(jsonResponse);
        assertThat(jsonObject.has("result"), is(true));
        assertThat(jsonObject.get("result"), is("success"));
        assertThat(jsonObject.has("applications"), is(true));
    }
}
