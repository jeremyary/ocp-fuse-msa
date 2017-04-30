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
public class ProductGatewayRoute extends SpringRouteBuilder {

    @Override
    public void configure() throws Exception {

        rest("/products")

                .post()
                    .id("addProduct")
                    .to("")

                .get()
                    .id("getProducts")
                    .to("")

                .get("/{sku}")
                    .id("getProductBySku")
                    .to("")

                .put("/{sku}")
                    .id("updateProduct")
                    .to("")

                .patch("/{sku}")
                    .id("partialllyUpdateProduct")
                    .to("")

                .delete("/{sku}")
                    .id("deleteProduct")
                    .to("")

                .post("/keywords")
                    .id("addKeywords")
                    .to("")

                .post("/classify/{sku}")
                    .id("classifyProduct")
                    .to("")

                .post("/reduce")
                    .id("reduceProductInInventory")
                    .to("");
    }
}
