package com.tmobile.cns.acms.testservice3.external.api;

import com.tmobile.cns.acms.testservice3.entities.requests.TestExternalRequest;
import com.tmobile.cns.acms.testservice3.entities.responses.TestExternalResponse;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * The type Pretend external api.
 */
@Slf4j
@Controller
public class PretendExternalApi {

    /**
     * This method is the pretend external service being called. It will take the external api request, do some
     * logic and return a response to the TestExternalService.
     *
     * @param request the request
     * @return the test external response
     */
    @PostMapping(value = "/external/api",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "FAKE EXTERNAL API",
            notes = "Do not use this endpoint as an example. The purpose of this endpoint is to replicate an external api.")
    @ResponseBody
    public TestExternalResponse pretendExternalApi(@RequestBody TestExternalRequest request) {
        if (request.getRequestData() > 10) return new TestExternalResponse("Success", true);
        return new TestExternalResponse("Fail", false);
    }
}
