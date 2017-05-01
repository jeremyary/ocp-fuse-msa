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
import org.apache.camel.http.common.HttpMethods;
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
public class ProductGatewayRoute extends SpringRouteBuilder {

    @Override
    public void configure() throws Exception {

        restConfiguration().component("servlet");

        rest("/products")

                .post()
                    .to("direct:addProduct")

                .get()
                    .to("direct:getProducts")
                    
                .get("/{sku}")
                    .to("direct:getProductBySku");
                    
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
        
        from("direct:addProduct")
                .setHeader(Exchange.HTTP_METHOD, constant(HttpMethods.POST))
                .to("http4://product-service/products");

        from("direct:getProducts")
                .setHeader(Exchange.HTTP_METHOD, constant(HttpMethods.GET))
                .to("http4://product-service/products");


        from("direct:getProductBySku")
                .setHeader(Exchange.HTTP_METHOD, constant(HttpMethods.GET))
                .to("http4://product-service/products/" + header("sku"));

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


    }
}
