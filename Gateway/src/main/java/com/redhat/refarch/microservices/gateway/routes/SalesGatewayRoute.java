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
public class SalesGatewayRoute extends SpringRouteBuilder {

    @Override
    public void configure() throws Exception {

        rest("/customers")

                .post()
                    .id("addCustomer")
                    .to("amq:deadend")

                .get()
                    .id("getCustomer")
                    .to("amq:deadend")

                .get("/{id}")
                    .id("getCustomerById")
                    .to("amq:deadend")

                .put("/{id}")
                    .id("updateCustomer")
                    .to("amq:deadend")

                .patch("/{id}")
                    .id("partiallyUpdateCustomer")
                    .to("amq:deadend")

                .delete("/{id}")
                    .id("deleteCustomer")
                    .to("amq:deadend")

                .post("/{customerId}/orders")
                    .id("addOrderToCustomer")
                    .to("amq:deadend")

                .get("/{customerId}/orders")
                    .id("getOrdersForCustomer")
                    .to("amq:deadend")

                .get("/{customerId}/orders/{orderId}")
                    .id("getOrderByOrderId")
                    .to("amq:deadend")

                .put("/{customerId}/orders/{orderId}")
                    .id("updateOrder")
                    .to("amq:deadend")

                .patch("/{customerId}/orders/{orderId}")
                    .id("partiallyUpdateOrder")
                    .to("amq:deadend")

                .delete("/{customerId}/orders/{orderId}")
                    .id("deleteOrder")
                    .to("amq:deadend")

                .post("/{customerId}/orders/{orderId}/orderItems")
                    .id("addOrderItem")
                    .to("amq:deadend")

                .get("/{customerId}/orders/{orderId}/orderItems")
                    .id("listOrderItems")
                    .to("amq:deadend")

                .get("/{customerId}/orders/{orderId}/orderItems/{orderItemId}")
                    .id("getOrderItemByOrderItemId")
                    .to("amq:deadend")

                .put("/{customerId}/orders/{orderId}/orderItems/{orderItemId}")
                    .id("updateOrderItem")
                    .to("amq:deadend")

                .patch("/{customerId}/orders/{orderId}/orderItems/{orderItemId}")
                    .id("partiallyUpdateOrderItem")
                    .to("amq:deadend")

                .delete("/{customerId}/orders/{orderId}/orderItems/{orderItemId}")
                    .id("deleteOrderItem")
                    .to("amq:deadend")

                .post("/authenticate")
                    .id("authenticateCustomer")
                    .to("amq:deadend");
    }
}