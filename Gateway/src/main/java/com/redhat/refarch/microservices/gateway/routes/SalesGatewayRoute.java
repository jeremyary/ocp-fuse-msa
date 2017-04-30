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
                    .to("")

                .get()
                    .id("getCustomer")
                    .to("")

                .get("/{id}")
                    .id("getCustomerById")
                    .to("")

                .put("/{id}")
                    .id("updateCustomer")
                    .to("")

                .patch("/{id}")
                    .id("partiallyUpdateCustomer")
                    .to("")

                .delete("/{id}")
                    .id("deleteCustomer")
                    .to("")

                .post("/{customerId}/orders")
                    .id("addOrderToCustomer")
                    .to("")

                .get("/{customerId}/orders")
                    .id("getOrdersForCustomer")
                    .to("")

                .get("/{customerId}/orders/{orderId}")
                    .id("getOrderByOrderId")
                    .to("")

                .put("/{customerId}/orders/{orderId}")
                    .id("updateOrder")
                    .to("")

                .patch("/{customerId}/orders/{orderId}")
                    .id("partiallyUpdateOrder")
                    .to("")

                .delete("/{customerId}/orders/{orderId}")
                    .id("deleteOrder")
                    .to("")

                .post("/{customerId}/orders/{orderId}/orderItems")
                    .id("addOrderItem")
                    .to("")

                .get("/{customerId}/orders/{orderId}/orderItems")
                    .id("listOrderItems")
                    .to("")

                .get("/{customerId}/orders/{orderId}/orderItems/{orderItemId}")
                    .id("getOrderItemByOrderItemId")
                    .to("")

                .put("/{customerId}/orders/{orderId}/orderItems/{orderItemId}")
                    .id("updateOrderItem")
                    .to("")

                .patch("/{customerId}/orders/{orderId}/orderItems/{orderItemId}")
                    .id("partiallyUpdateOrderItem")
                    .to("")

                .delete("/{customerId}/orders/{orderId}/orderItems/{orderItemId}")
                    .id("deleteOrderItem")
                    .to("")

                .post("/authenticate")
                    .id("authenticateCustomer")
                    .to("");
    }
}