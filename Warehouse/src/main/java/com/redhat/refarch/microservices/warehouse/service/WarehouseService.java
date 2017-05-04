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
package com.redhat.refarch.microservices.warehouse.service;

import com.redhat.refarch.microservices.warehouse.model.Result;
import org.apache.camel.Message;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class WarehouseService {

    private Logger logger = Logger.getLogger(getClass().getName());

    public Message determineOwnership(Message message) {

        /*
            In production cases, multiple warehouse instances would be subscribed to the warehouse.orders topic, so
            this method could be used to referenced a shared data grid clustered over all warehouse instances. With
            proper geographical and inventory level information, a decision could be made as to whether this specific
            instance is the optimal warehouse to fulfill the request or not. Note that doing so would require a lock
            mechanism in the shared cache if the choice algorithm could potentially allow duplicate optimal choices.
         */

        // in this demo, only a single warehouse instance will be used, so just claim all messages and return them
        message.setHeader("ownership", "true");
        return message;
    }

    public void fulfillOrder(Result result) {

        logInfo("===============================================");
        logInfo("===          FULFILLING ORDER!!!            ===");
        logInfo("===============================================");
    }

    private void logInfo(String message) {
        logger.log(Level.INFO, message);
    }
}