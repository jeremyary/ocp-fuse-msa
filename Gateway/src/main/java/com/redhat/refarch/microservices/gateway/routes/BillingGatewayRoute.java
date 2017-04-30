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
package com.redhat.refarch.microservices.gateway.routes;

import org.apache.camel.spring.SpringRouteBuilder;
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
public class BillingGatewayRoute extends SpringRouteBuilder {

    @Override
    public void configure() throws Exception {

        rest("/billing")

                .post("/process")
                    .id("processOrder")
//                    .to("direct:newOrders")
                    .to("amq:billing.orders.new?transferException=true")

                .post("/refund/{transactionNumber}")
                    .id("refundOrder")
//                    .to("direct:refunds");
                    .to("amq:billing.orders.refund?transferException=true");

        // dropping down to direct routes so that we can use inOut, unavailable on the REST DSL methods
//        from("direct:newOrders")
//                .routeId("sendNewOrdersToQueue")
//                .inOut("amq:billing.orders.new?transferException=true");
//
//        from("direct:refunds")
//                .routeId("sendRefundOrdersToQueue")
//                .inOut("amq:billing.orders.refund?transferException=true");
    }
}