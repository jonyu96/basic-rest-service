package com.tony.test.testservice3.controllers;

import com.tony.test.testservice3.exceptions.BadRequestException;
import com.tony.test.testservice3.services.TestService;
import generated.XmlTestBaseResponse;
import generated.XmlTestErrorResponse;
import generated.XmlTestRequest;
import generated.XmlTestResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.UUID;

/**
 * The type Test controller.
 */
@Slf4j
@Controller
public class TestController {

    private TestService testService;

    /**
     * Instantiates a new Test controller.
     *
     * @param testService the test service
     */
    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    /**
     * Execute test.
     *
     * @param request the request
     * @return the test response
     * @throws BadRequestException  the bad request exception
     * @throws NullPointerException the null pointer exception
     */
    @PostMapping(value = "/test",
            produces = MediaType.APPLICATION_XML_VALUE,
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Execute basic test api",
            notes = "Send arbitrary msisdn and receive an arbitrary response",
            response = XmlTestBaseResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message =
                    "1. Empty msisdn\n" +
                            "2. Msisdn must be 10 or 11 digits long.",
                    response = XmlTestBaseResponse.class)})
    @ResponseBody
    public XmlTestBaseResponse executeTest(@RequestBody XmlTestRequest request) throws BadRequestException, NullPointerException {
        setupMDC("/test");
        log.info("Initial Request Body: {}", request);

        XmlTestResponse response = testService.executeTest(request);
        XmlTestBaseResponse baseResponse = new XmlTestBaseResponse();
        baseResponse.setXmlTestResponse(response);
        log.info("Returning Successful Response: {}", baseResponse);
        return baseResponse;
    }


    /**
     * Setup MDC variables.
     *
     * @param method the api endpoint that was called
     */
    private void setupMDC(String method) {
        MDC.clear();
        MDC.put("method", method);
        MDC.put("workflow", "Example Flow");
        MDC.put("transactionId", UUID.randomUUID().toString());
    }
}
