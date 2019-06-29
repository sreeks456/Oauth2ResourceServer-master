package com.stock.oauth2.resourceserver.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

import com.stock.oauth2.resourceserver.config.YAMLConfig;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {


    @Autowired 
    private ResourceServerTokenServices tokenServices;
    
    @Autowired 
    YAMLConfig config;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(config.getResourceId()).tokenServices(tokenServices);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
                http
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, config.getUrlPattern()).access(config.getAccessPattern())
                .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
                
               
    }
}
