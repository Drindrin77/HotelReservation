/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package samples.quickstart.clients;

import samples.quickstart.service.xmlbeans.StockQuoteServiceStub;
import samples.quickstart.service.xmlbeans.xsd.GetPriceDocument;
import samples.quickstart.service.xmlbeans.xsd.GetPriceResponseDocument;
import samples.quickstart.service.xmlbeans.xsd.UpdateDocument;

public class XMLBEANSClient{

    public static void main(java.lang.String args[]){
        try{
            StockQuoteServiceStub stub =
                new StockQuoteServiceStub
                ("http://localhost:8080/axis2/services/StockQuoteService");

            getPrice(stub);
            update(stub);
            getPrice(stub);

        } catch(Exception e){
            e.printStackTrace();
            System.err.println("\n\n\n");
        }
    }

    /* fire and forget */
    public static void update(StockQuoteServiceStub stub){
        try{
            UpdateDocument reqDoc = UpdateDocument.Factory.newInstance();
            UpdateDocument.Update req = reqDoc.addNewUpdate();
            req.setSymbol ("BCD");
            req.setPrice (42.32);

            stub.update(reqDoc);
            System.err.println("price updated");
        } catch(Exception e){
            e.printStackTrace();
            System.err.println("\n\n\n");
        }
    }

    /* two way call/receive */
    public static void getPrice(StockQuoteServiceStub stub){
        try{
            GetPriceDocument reqDoc = GetPriceDocument.Factory.newInstance();
            GetPriceDocument.GetPrice req = reqDoc.addNewGetPrice();
            req.setSymbol("BCD");

            GetPriceResponseDocument res =
                stub.getPrice(reqDoc);

            System.err.println(res.getGetPriceResponse().getReturn());
        } catch(Exception e){
            e.printStackTrace();
            System.err.println("\n\n\n");
        }
    }
}
