/**
 * Copyright 2005-2017 Red Hat, Inc.
 * <p>
 * Red Hat licenses this file to you under the Apache License, version
 * 2.0 (the "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.redhat.refarch.microservices.gateway.routes;

import org.apache.camel.LoggingLevel;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/***
 * @author jary@redhat.com
 */
@Component
public class GatewayRoute extends SpringRouteBuilder {

    @Autowired
    private GatewayUriProcessor uriProcessor;

    @Override
    public void configure() throws Exception {

        String gatewayEndpoint = "restlet:http://0.0.0.0:9091/{endpoint}";
        String newHost = "http://${headers.newHost}:8080${headers.uriPath}";

        // error handler for all following routes
        errorHandler(defaultErrorHandler()
                .allowRedeliveryWhileStopping(false)
                .maximumRedeliveries(3)
                .redeliveryDelay(3000)
                .retryAttemptedLogLevel(LoggingLevel.WARN));

        // establish restlet on 9091 to intercept all rest requests for forwarding to services
        // calls to product-service and sales-service are passed through as we're simply mirroring the provided API
        // that our microservices have already established
        from(gatewayEndpoint + "?restletMethods=post,get,put,proppatch,delete&restletUriPatterns=#uriTemplates")
                .routeId("proxy-api-gateway")
                .process(uriProcessor)
                .choice()
                    .when(simple("${headers.newHost} =~ 'billing-service'"))

                        .to("direct:billingRoute")
                    .otherwise()
                        .recipientList(simple(newHost + "?bridgeEndpoint=true"))
                .end();

        // calls to billing are placed transactionally (InOut) on a msg queue for fault tolerance
        // and multicast to warehouses for fulfillment
        from("direct:billingRoute")
                .streamCaching()
                .routeId("billingMsgGateway")
                .choice()
                    .when(header("uriPath").startsWith("/billing/process"))
                        .to("amq:billing.orders.new?transferException=true&jmsMessageType=Text")
                        .wireTap("direct:warehouse")
                        .endChoice()

                    .when(header("uriPath").startsWith("/billing/refund"))
                        .to("amq:billing.orders.refund?transferException=true&jmsMessageType=Text")

                    .otherwise()
                        .log(LoggingLevel.ERROR, "unknown method received in billingMsgGateway")
                .end();

        // calls to warehouse are placed event-wise (InOnly, don't await reply) on a msg queue for fault tolerance
        // and fanning out to multiple locations. In this example, we don't care which one fulfills the order.
        from("direct:warehouse")
                .routeId("warehouseMsgGateway")
                .log(LoggingLevel.INFO, "RECD IN WAREHOUSE GATEWAY")
                .to("log:INFO?showBody=true&showHeaders=true")
                .choice()
                    .when(simple("${body) contains 'SUCCESS'"))
                            .log(LoggingLevel.INFO, "SUCCESS IN BODY")
                    .when(simple("${body) contains 'FAILURE'"))
                            .log(LoggingLevel.INFO, "FAILURE IN BODY")
                    .otherwise()
                            .log(LoggingLevel.INFO, "NEITHER IN BODY")
                            .log(LoggingLevel.INFO, "BODY: ${body}");
//                .inOnly("amq:warehouse.orders.new?transferException=false&jmsMessageType=Text");
//

    }
}