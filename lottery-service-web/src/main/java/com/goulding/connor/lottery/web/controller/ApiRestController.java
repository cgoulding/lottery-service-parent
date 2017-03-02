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
 * Created by connor.
 */
@Api("Lottery Service API")
@RestController
@RequestMapping(path = "/")
public class ApiRestController
{
    @ApiOperation("Lottery Service API Documentation")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<ApiResource> api(HttpServletRequest request) {
        ApiResource resource = new ApiResource();
        resource.add(new Link(request.getRequestURL().toString() + "swagger-resources", "documentation"));
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }
}
