package com.stock.oauth2.resourceserver.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.stock.oauth2.resourceserver.config.YAMLConfig;

import java.security.SecureRandom;

@RestController
public class FooController {
	
	@Autowired
	YAMLConfig config;

    @PreAuthorize("#oauth2.hasScope('read')")
    @RequestMapping(method = RequestMethod.GET, value = "/foo")
    @ResponseBody
    public Integer findById() {
        return new SecureRandom().nextInt();
    }
}
