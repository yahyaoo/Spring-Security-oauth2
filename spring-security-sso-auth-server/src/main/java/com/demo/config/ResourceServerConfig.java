/*
 *  Copyright 2019 JDSchool Ltd.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

/**
 * Title: ResourceServerConfig
 * Description: 资源服务
 *
 * @author yuhong
 * @Company com.jdschool
 * @date 2019/3/21 23:56
 */
@Order(2)
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private final HandlerMappingIntrospector handlerMappingIntrospector;

    @Autowired
    public ResourceServerConfig(HandlerMappingIntrospector handlerMappingIntrospector) {
        this.handlerMappingIntrospector = handlerMappingIntrospector;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .requestMatcher(new MvcRequestMatcher(this.handlerMappingIntrospector, "/**"))
//                .requestMatchers()
//                .antMatchers("/user/me")
//                .and()

                .authorizeRequests()
                .antMatchers("/oauth/token_key")
                .permitAll()
                .anyRequest()
                .authenticated()
        ;
    }

}
