/**
 *  Copyright 2005-2017 Red Hat, Inc.
 *
 *  Red Hat licenses this file to you under the Apache License, version
 *  2.0 (the "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 *  implied.  See the License for the specific language governing
 *  permissions and limitations under the License.
 */
package com.redhat.refarch.microservices.billing.routes;

import com.redhat.refarch.microservices.billing.model.Transaction;
import com.redhat.refarch.microservices.billing.service.BillingService;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.apache.camel.spi.DataFormat;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/***
 * ====================================================
 *    FORMATTING OF CAMEL DSL/ROUTE FILE DISCOURAGED:
 *    ROUTE CHAIN INDICATED BY ATYPICAL BREAKS/TABBING
 * ====================================================
 *
 * @author jary@redhat.com
 */
@Component
public class BillingRoute extends SpringRouteBuilder {

    @Autowired
    private BillingService billingService;

    @Override
    public void configure() throws Exception {

        DataFormat jaxb = new JaxbDataFormat("com.redhat.refarch.microservices.billing.model");

        from("amq:billing.orders.new?transferException=true")
                .routeId("processNewOrders")
                .unmarshal(jaxb)
                .bean(billingService, "process");

        from("amq:billing.orders.refund?transferException=true")
                .routeId("processRefunds")
                .unmarshal(jaxb)
                .bean(billingService, "refund");
    }
}