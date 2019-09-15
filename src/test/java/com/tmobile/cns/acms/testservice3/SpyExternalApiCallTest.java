package com.tmobile.cns.acms.testservice3;

import com.tmobile.cns.acms.testservice3.configs.ApplicationProperties;
import com.tmobile.cns.acms.testservice3.controllers.TestController;
import com.tmobile.cns.acms.testservice3.entities.requests.TestExternalRequest;
import com.tmobile.cns.acms.testservice3.entities.responses.TestExternalResponse;
import com.tmobile.cns.acms.testservice3.exceptions.PretendExternalApiFailureException;
import com.tmobile.cns.acms.testservice3.external.api.PretendExternalApi;
import com.tmobile.cns.acms.testservice3.services.TestExternalService;
import com.tmobile.cns.acms.testservice3.services.TestService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpyExternalApiCallTest {

    @Autowired
    ApplicationProperties properties;
    TestController testController;
    TestService testService;
    TestExternalService testExternalService;
    @Mock RestTemplate mockRestTemplate;

    @Before
    public void setup() {
        testService = spy(new TestService());
        testExternalService = spy(new TestExternalService(mockRestTemplate));
        testController = spy(new TestController(testService, testExternalService));
    }

    @Test
    public void spyExternalApiService() throws PretendExternalApiFailureException {
        TestExternalResponse mockResponse = new TestExternalResponse("Success", true);
        doReturn(ResponseEntity.ok(mockResponse)).when(mockRestTemplate).exchange(contains("/external/api"), any(), any(), (Class<Object>) any());

        TestExternalResponse response = testController.executeTestExternalApiCall(new TestExternalRequest(11));
        assertEquals("Success", response.getStatus());
    }
}
