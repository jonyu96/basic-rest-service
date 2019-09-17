package com.tony.test.testservice3;

import com.tony.test.testservice3.configs.ApplicationProperties;
import com.tony.test.testservice3.controllers.TestController;
import com.tony.test.testservice3.entities.requests.TestExternalRequest;
import com.tony.test.testservice3.entities.responses.TestExternalResponse;
import com.tony.test.testservice3.exceptions.PretendExternalApiFailureException;
import com.tony.test.testservice3.services.TestExternalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MockExternalApiCallTest {

    @Autowired
    ApplicationProperties properties;
    TestController testController;
    @Mock
    TestExternalService testExternalService;

    @Before
    public void setup() {
        testController = spy(new TestController(testExternalService));
    }

    @Test
    public void mockExternalApiService() throws PretendExternalApiFailureException {
        TestExternalResponse mockResponse = new TestExternalResponse("Success", true);
        doReturn(mockResponse).when(testExternalService).executeExternalTest(any());


        TestExternalResponse response = testController.executeTestExternalApiCall(new TestExternalRequest(11));
        assertEquals("Success", response.getStatus());
    }
}
