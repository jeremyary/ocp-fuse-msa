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
package com.redhat.refarch.microservices.warehouse.routes;

import com.redhat.refarch.microservices.warehouse.model.Result;
import com.redhat.refarch.microservices.warehouse.service.WarehouseService;
import org.apache.camel.LoggingLevel;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/***
 * @author jary@redhat.com
 */
@Component
public class WarehouseRoute extends SpringRouteBuilder {

    @Value("${messaging.warehouse.id}")
    private String warehouseId;

    private WarehouseService warehouseService;

    private DataFormatFactory dataFormatFactory;


    @Autowired
    public WarehouseRoute(WarehouseService warehouseService, DataFormatFactory dataFormatFactory) {
        this.warehouseService = warehouseService;
        this.dataFormatFactory = dataFormatFactory;
    }

    @Override
    public void configure() throws Exception {

        from("amq:topic:warehouse.orders?clientId=" + warehouseId)
                .routeId("fulfillOrder")

                .log(LoggingLevel.INFO, "***** WAREHOUSE ENTRY *****: ")
                .to("log:INFO?showBody=true&showHeaders=true")

                .unmarshal(dataFormatFactory.formatter(Result.class))

                .log(LoggingLevel.INFO, "***** WAREHOUSE UNMARSHALLED *****: ")
                .to("log:INFO?showBody=true&showHeaders=true")

                .bean(warehouseService, "determineOwnership")

                .log(LoggingLevel.INFO, "***** POST OWNERSHIP *****: ")
                .to("log:INFO?showBody=true&showHeaders=true")

                .filter(simple("${headers.ownership} == 'true'"))
                    .log(LoggingLevel.INFO, "***** FILTERED YES *****: ")
                    .to("log:INFO?showBody=true&showHeaders=true")
                    .bean(warehouseService, "fulfillOrder");

    }
}