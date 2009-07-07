/*
 * Copyright 2009 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *     
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openehealth.ipf.platform.camel.ihe.xds.commons.transform.requests.ebxml30;

import static org.junit.Assert.*;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.ebxml.EbXMLAdhocQueryRequest;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.ebxml.ebxml30.EbXMLFactory30;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.requests.query.GetSubmissionSetsQuery;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.requests.query.QueryType;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.transform.requests.QueryParameter;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.transform.requests.query.GetSubmissionSetsQueryTransformer;

/**
 * Tests for {@link GetSubmissionSetsQueryTransformer}.
 * @author Jens Riemschneider
 */
public class GetSubmissionSetsQueryTransformerTest {
    private GetSubmissionSetsQueryTransformer transformer;
    private GetSubmissionSetsQuery query;
    private EbXMLAdhocQueryRequest ebXML;
    
    @Before
    public void setUp() {
        transformer = new GetSubmissionSetsQueryTransformer();
        query = new GetSubmissionSetsQuery();

        query.getUUIDs().add("uuid1");
        query.getUUIDs().add("uuid2");
        query.setHomeCommunityID("home");

        ebXML = new EbXMLFactory30().createAdhocQueryRequest();
    }
    
    @Test
    public void testToEbXML() {
        transformer.toEbXML(query, ebXML);
        assertEquals(QueryType.GET_SUBMISSION_SETS.getId(), ebXML.getId());
        
        assertEquals(Arrays.asList("('uuid1')", "('uuid2')"),
                ebXML.getSlotValues(QueryParameter.UUID.getSlotName()));
        
        assertEquals(Arrays.asList("'home'"),
                ebXML.getSlotValues(QueryParameter.HOME.getSlotName()));
        
        assertEquals(2, ebXML.getSlots().size());
    }
    
    @Test
    public void testToEbXMLNull() {
        transformer.toEbXML(null, ebXML);
        assertEquals(0, ebXML.getSlots().size());
    }
    
    @Test
    public void testToEbXMLEmpty() {
        transformer.toEbXML(new GetSubmissionSetsQuery(), ebXML);
        assertEquals(0, ebXML.getSlots().size());
    }

    
    
    @Test
    public void testFromEbXML() {
        transformer.toEbXML(query, ebXML);
        GetSubmissionSetsQuery result = new GetSubmissionSetsQuery();
        transformer.fromEbXML(result, ebXML);
        
        assertEquals(query, result);
    }
    
    @Test
    public void testFromEbXMLNull() {
        GetSubmissionSetsQuery result = new GetSubmissionSetsQuery();
        transformer.fromEbXML(result, null);        
        assertEquals(new GetSubmissionSetsQuery(), result);
    }
        
    @Test
    public void testFromEbXMLEmpty() {
        GetSubmissionSetsQuery result = new GetSubmissionSetsQuery();
        transformer.fromEbXML(result, ebXML);        
        assertEquals(new GetSubmissionSetsQuery(), result);
    }
}