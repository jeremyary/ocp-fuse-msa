/*
 * Copyright 2005-2017 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version
 * 2.0 (the "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.redhat.refarch.microservices.gateway;

import org.apache.camel.spring.javaconfig.CamelConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

/***
 * @author jary@redhat.com
 */
@Configuration
@ComponentScan("com.redhat.refarch.microservices.gateway")
public class CamelConfig extends CamelConfiguration {

    @Bean
    public List<String> uriTemplates() {
        return Arrays.asList(
                "/products/classify/{sku}",
                "/products/keywords",
                "/products/reduce",
                "/products/{sku}",
                "/products/",
                "/billing/process",
                "/billing/refund",
                "/customers/{customerId}/orders/{orderId}/orderItems/{orderItemId}",
                "/customers/{customerId}/orders/{orderId}/orderItems",
                "/customers/{customerId}/orders/{orderId}",
                "/customers/{customerId}/orders",
                "/customers/authenticate",
                "/customers/{id}",
                "/customers/"
        );
    }
}
