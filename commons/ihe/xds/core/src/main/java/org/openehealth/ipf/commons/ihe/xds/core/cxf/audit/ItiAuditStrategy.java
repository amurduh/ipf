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
package org.openehealth.ipf.commons.ihe.xds.core.cxf.audit;

import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openehealth.ipf.commons.ihe.xds.core.ebxml.EbXMLRegistryResponse;
import org.openehealth.ipf.commons.ihe.xds.core.responses.ErrorInfo;
import org.openehealth.ipf.commons.ihe.xds.core.responses.Severity;
import org.openehealth.ipf.commons.ihe.xds.core.responses.Status;
import org.openhealthtools.ihe.atna.auditor.codes.rfc3881.RFC3881EventCodes.RFC3881EventOutcomeCodes;


/**
 * Basis for Strategy pattern implementation for ATNA Auditing.
 * 
 * @author Dmytro Rud
 */
public abstract class ItiAuditStrategy {
    
    private static final transient Log LOG = LogFactory.getLog(ItiAuditStrategy.class);
    
    // TODO: externalize constant
    public static final String HOME_COMMUNITY_ID = "";
    
    /**
     * Whether this is a server-side or a client-side strategy. 
     */
    private boolean serverSide;

    /**
     * Whether this strategy should allow incomplete audit records.
     */
    private boolean allowIncompleteAudit;
    

    /**
     * Constructs an audit strategy.
     *   
     * @param serverSide
     *      whether this is a server-side or a client-side strategy.
     * @param allowIncompleteAudit
     *      whether this strategy should allow incomplete audit records
     *      (parameter initially configurable via endpoint URL).
     */
    public ItiAuditStrategy(boolean serverSide, boolean allowIncompleteAudit) {
        this.setServerSide(serverSide);
        this.setAllowIncompleteAudit(allowIncompleteAudit);
    }
    

    /**
     * Creates a new audit dataset audit instance. 
     * 
     * @return
     *      newly created audit dataset
     */
    public ItiAuditDataset createAuditDataset() {
        return new ItiAuditDataset(isServerSide());
    }

    
    /**
     * Returns <code>true</code> when audit item must include message payload dump.
     * <p>
     * Per default returns <code>false</code>, because most of XDS transactions
     * do not need it.  
     * 
     * @return
     *      <code>true</code> when payload should be saved; <code>false</code> otherwise        
     */
    public boolean needSavePayload() {
        return false;
    }
    
    
    /**
     * Enriches the dataset with transaction-specific information from the given POJO.
     *   
     * @param pojo
     *      POJO extracted from the message
     * @param auditDataset
     *      audit dataset to be enriched
     * @throws Exception
     */
    public abstract void enrichDataset(Object pojo, ItiAuditDataset auditDataset) 
        throws Exception;
    
    
    /**
     * Performs transaction-specific auditing using 
     * information containing in the dataset.
     *   
     * @param eventOutcomeCode
     *      event outcome code as defined in RFC 3881
     * @param auditDataset
     *      audit dataset with all the information needed 
     * @throws Exception
     */
    public abstract void doAudit(RFC3881EventOutcomeCodes eventOutcomeCode, ItiAuditDataset auditDataset)
        throws Exception;

    
    /**
     * Checks whether the audit can be performed and calls {@link #doAudit}  
     * if the answer is positive. 
     * <p>
     * Audit can be performed when all necessary data is present or
     * when the user allows us to audit with incomplete data,
     * @see #allowIncompleteAudit.
     * 
     * @param eventOutcomeCode
     *      event outcome code as defined in RFC 3881
     * @param auditDataset
     *      audit dataset  
     * @throws Exception
     */
    public void audit(RFC3881EventOutcomeCodes eventOutcomeCode, ItiAuditDataset auditDataset) throws Exception {
        Set<String> missing = auditDataset.checkFields(getNecessaryAuditFieldNames(), true);
        if(! missing.isEmpty()) {
            StringBuilder sb = new StringBuilder("Missing audit fields: ");
            for(String fieldName : missing) {
                sb.append(fieldName).append(", ");
            }
            sb.append(isAllowIncompleteAudit() ? 
                "but incomplete audit is allowed, so we'll perform it." :
                "auditing not possible.");
            LOG.error(sb.toString());
        }
        if(missing.isEmpty() || isAllowIncompleteAudit()) {
            doAudit(eventOutcomeCode, auditDataset);
        }
    }
        
    
    /**
     * Returns a transaction-specific list of names of fields 
     * a "complete" audit dataset must contain. 
     *  
     * @return
     *      list of field names as a string array
     */
    public abstract String[] getNecessaryAuditFieldNames();
    
    
    /**
     * Determines which RFC 3881 event outcome code corresponds to the
     * given response POJO.  
     * @param pojo
     *      ebXML object.
     * @return
     *      RFC 3881 event outcome code.
     */
    public abstract RFC3881EventOutcomeCodes getEventOutcomeCode(Object pojo);

    
    /**
     * A helper method that analyzes the given registry response and 
     * determines the corresponding RFC 3881 event outcome code. 
     */
    public static RFC3881EventOutcomeCodes getEventOutcomeCodeFromRegistryResponse(EbXMLRegistryResponse response) {
        if(response.getStatus() == Status.SUCCESS) {
            return RFC3881EventOutcomeCodes.SUCCESS; 
        }

        List<ErrorInfo> errors = response.getErrors();
        
        // error list is empty
        if((errors == null) || errors.isEmpty()) {
            return RFC3881EventOutcomeCodes.SERIOUS_FAILURE;
        }
        
        // determine the highest severity 
        for(ErrorInfo error : errors) {
            if(error.getSeverity() == Severity.ERROR) {
                return RFC3881EventOutcomeCodes.SERIOUS_FAILURE;
            }
        }
        return RFC3881EventOutcomeCodes.MINOR_FAILURE;
    }

    

    /* ----- automatically generated getters and setters ----- */

    public void setServerSide(boolean serverSide) {
        this.serverSide = serverSide;
    }

    public boolean isServerSide() {
        return serverSide;
    }

    public void setAllowIncompleteAudit(boolean allowIncompleteAudit) {
        this.allowIncompleteAudit = allowIncompleteAudit;
    }

    public boolean isAllowIncompleteAudit() {
        return allowIncompleteAudit;
    }
}