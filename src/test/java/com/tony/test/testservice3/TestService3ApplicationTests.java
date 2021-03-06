package com.tony.test.testservice3;

import com.tony.test.testservice3.configs.ApplicationProperties;
import com.tony.test.testservice3.controllers.TestController;
import com.tony.test.testservice3.entities.requests.TestRequest;
import com.tony.test.testservice3.entities.responses.SuccessResponse;
import com.tony.test.testservice3.exceptions.BadRequestException;
import com.tony.test.testservice3.services.TestService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestService3ApplicationTests {

    @Autowired
    ApplicationProperties properties;
    TestController testController;
    TestService testService;

    @Before
    public void setup() {
        testService = spy(new TestService());
        testController = spy(new TestController(testService));

    }

    @Test
    public void testPropertiesLoading() {
        String testConstant = properties.getTestConstant();
        log.info("testConstant: {}", testConstant);
        assertEquals("helloWorld", testConstant);
    }

    @Test
    public void testControllerSuccess10digit() throws BadRequestException {
        TestRequest request = new TestRequest("1234567890");
        SuccessResponse response = testController.executeTest(request);
        assertEquals("Success", response.getBody().getStatus());
    }

    @Test
    public void testControllerSuccess11digit() throws BadRequestException {
        TestRequest request = new TestRequest("11234567890");
        SuccessResponse response = testController.executeTest(request);
        assertEquals("Success", response.getBody().getStatus());
    }

    @Test(expected = NullPointerException.class)
    public void testControllerFailNullRequest() throws BadRequestException {
        TestRequest request = new TestRequest("");
        testController.executeTest(request);
    }

    @Test(expected = BadRequestException.class)
    public void testControllerFailBadRequest() throws BadRequestException {
        TestRequest request = new TestRequest("123");
        testController.executeTest(request);
    }

    @Test
    public void testService() {

    }
}
