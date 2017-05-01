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

import org.apache.camel.Exchange;
import org.apache.camel.Headers;
import org.apache.camel.LoggingLevel;
import org.apache.camel.http.common.HttpMethods;
import org.apache.camel.model.rest.RestBindingMode;
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
public class GatewayRoute extends SpringRouteBuilder {

    @Override
    public void configure() throws Exception {

        from("jetty:http://0.0.0.0:9091/?matchOnUriPrefix=true")
            .to("log:INFO?showBody=true&showHeaders=true")
            .to("amq:deadend");

//        errorHandler(
//                deadLetterChannel("amq:billing.deadLetter")
//                        .maximumRedeliveries(3)
//                        .redeliveryDelay(3000)
//        );
//
//        restConfiguration().component("jetty")
//                .enableCORS(true)
//                .bindingMode(RestBindingMode.json)
//                .dataFormatProperty("prettyPrint", "true")
//                .port(9091);
//
//        rest("/products")
//                .id("products-API-gateway")
//                .produces("application/json")
//                .consumes("application/json")
//
//                .post()
//                    .to("direct:addProduct")
//
//                .get()
//                    .to("direct:getProducts")
//
//                .get("/{sku}")
//                    .to("direct:getProductBySku");
                    
//                .put("/{sku}")
//                    .to("direct:updateProduct")
//
//                .patch("/{sku}")
//                    .to("direct:partialllyUpdateProduct")
//
//                .delete("/{sku}")
//                    .to("direct:deleteProduct")
//
//                .post("/keywords")
//                    .to("direct:addKeywords")
//
//                .post("/classify/{sku}")
//                    .to("direct:classifyProduct")
//
//                .post("/reduce")
//                    .to("direct:reduceProductInInventory");
        
//        from("direct:addProduct")
//                .routeId("addProduct")
//                .log(LoggingLevel.INFO, "addProduct Rest call, forwarding to product-service: " + body())
//                .setHeader(Exchange.HTTP_METHOD, constant(HttpMethods.POST))
//                .to("http4://product-service/products");
//
//        from("direct:getProducts")
//                .routeId("getProducts")
//                .log(LoggingLevel.INFO, "getProducts Rest call, forwarding to product-service: " + body())
//                .setHeader(Exchange.HTTP_METHOD, constant(HttpMethods.GET))
//                .to("http4://product-service/products");
//
//
//        from("direct:getProductBySku")
//                .routeId("getProductBySku")
//                .log(LoggingLevel.INFO, "getProductBySku Rest call, forwarding to product-service: " + body())
//                .setHeader(Exchange.HTTP_METHOD, constant(HttpMethods.GET))
//                .to("http4://product-service/products/" + header("sku"));

//        from("direct:updateProduct")
//
//
//        from("direct:partialllyUpdateProduct")
//
//
//        from("direct:deleteProduct")
//
//
//        from("direct:addKeywords")
//
//
//        from("direct:classifyProduct")
//
//
//        from("direct:reduceProductInInventory");

//        rest("/billing")
//                .id("billing-API-gateway")
//                .produces("application/json")
//                .consumes("application/json")
//
//                .post("/process")
//                .to("direct:newOrders")
//
//                .post("/refund/{transactionNumber}")
//                .to("direct:refunds");
//
//        // dropping down to direct routes so that we can use inOut
//        from("direct:newOrders")
//                .routeId("sendNewOrdersToQueue")
//                .inOut("amq:billing.orders.new?transferException=true");
//
//        from("direct:refunds")
//                .routeId("sendRefundOrdersToQueue")
//                .inOut("amq:billing.orders.refund?transferException=true");
//
//        rest("/customers")
//                .id("sales-API-gateway")
//                .produces("application/json")
//                .consumes("application/json")
//
//                .post()
//                .id("addCustomer")
//                .to("amq:deadend")
//
//                .get()
//                .id("getCustomer")
//                .to("amq:deadend")
//
//                .get("/{id}")
//                .id("getCustomerById")
//                .to("amq:deadend")
//
//                .put("/{id}")
//                .id("updateCustomer")
//                .to("amq:deadend")
//
//                .patch("/{id}")
//                .id("partiallyUpdateCustomer")
//                .to("amq:deadend")
//
//                .delete("/{id}")
//                .id("deleteCustomer")
//                .to("amq:deadend")
//
//                .post("/{customerId}/orders")
//                .id("addOrderToCustomer")
//                .to("amq:deadend")
//
//                .get("/{customerId}/orders")
//                .id("getOrdersForCustomer")
//                .to("amq:deadend")
//
//                .get("/{customerId}/orders/{orderId}")
//                .id("getOrderByOrderId")
//                .to("amq:deadend")
//
//                .put("/{customerId}/orders/{orderId}")
//                .id("updateOrder")
//                .to("amq:deadend")
//
//                .patch("/{customerId}/orders/{orderId}")
//                .id("partiallyUpdateOrder")
//                .to("amq:deadend")
//
//                .delete("/{customerId}/orders/{orderId}")
//                .id("deleteOrder")
//                .to("amq:deadend")
//
//                .post("/{customerId}/orders/{orderId}/orderItems")
//                .id("addOrderItem")
//                .to("amq:deadend")
//
//                .get("/{customerId}/orders/{orderId}/orderItems")
//                .id("listOrderItems")
//                .to("amq:deadend")
//
//                .get("/{customerId}/orders/{orderId}/orderItems/{orderItemId}")
//                .id("getOrderItemByOrderItemId")
//                .to("amq:deadend")
//
//                .put("/{customerId}/orders/{orderId}/orderItems/{orderItemId}")
//                .id("updateOrderItem")
//                .to("amq:deadend")
//
//                .patch("/{customerId}/orders/{orderId}/orderItems/{orderItemId}")
//                .id("partiallyUpdateOrderItem")
//                .to("amq:deadend")
//
//                .delete("/{customerId}/orders/{orderId}/orderItems/{orderItemId}")
//                .id("deleteOrderItem")
//                .to("amq:deadend")
//
//                .post("/authenticate")
//                .id("authenticateCustomer")
//                .to("amq:deadend");
    }
}
