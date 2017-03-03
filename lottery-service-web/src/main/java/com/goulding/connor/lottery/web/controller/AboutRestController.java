package com.goulding.connor.lottery.web.controller;

import com.goulding.connor.lottery.web.resource.ApiResource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Connor Goulding
 */
@Api("Lottery Service API")
@RestController
@RequestMapping(path = "/about")
public class AboutRestController {
    @ApiOperation("Lottery Service API About")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<ApiResource> api(HttpServletRequest request) {
        ApiResource resource = new ApiResource();
        resource.add(new Link("/swagger-resources", "documentation"));
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }
}
