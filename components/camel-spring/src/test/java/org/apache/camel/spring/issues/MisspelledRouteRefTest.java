/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.spring.issues;

import junit.framework.TestCase;
import org.apache.camel.CamelException;
import org.apache.camel.spring.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version 
 */
public class MisspelledRouteRefTest extends TestCase {

    private static final transient Logger LOG = LoggerFactory.getLogger(MisspelledRouteRefTest.class);

    public void testApplicationContextFailed() {
        try {
            Main main = new Main();
            main.setApplicationContextUri("org/apache/camel/spring/issues/MisspelledRouteRefTest.xml");
            main.start();
            fail("Should have thrown an exception");
        } catch (Exception e) {
            //expected but want to see what it looks like...
            LOG.debug("Exception message : " + e.getMessage());

            CamelException cause = (CamelException) e.getCause();
            assertEquals("Cannot find any routes with this RouteBuilder reference: RouteBuilderRef[xxxroute]", cause.getMessage());
        }
    }
}

