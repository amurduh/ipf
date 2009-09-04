//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.1.9-03/31/2009 04:14 PM(snajper)-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.05.19 at 10:15:07 AM CEST 
//


package org.openehealth.ipf.commons.ihe.xds.core.stub.ebrs21.query;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.openehealth.ipf.commons.ihe.xds.core.stub.ebrs21.rim.ClassificationSchemeType;
import org.openehealth.ipf.commons.ihe.xds.core.stub.ebrs21.rim.ExtrinsicObjectType;
import org.openehealth.ipf.commons.ihe.xds.core.stub.ebrs21.rim.ObjectRefType;
import org.openehealth.ipf.commons.ihe.xds.core.stub.ebrs21.rim.RegistryEntryType;
import org.openehealth.ipf.commons.ihe.xds.core.stub.ebrs21.rim.RegistryObjectType;
import org.openehealth.ipf.commons.ihe.xds.core.stub.ebrs21.rim.RegistryPackageType;
import org.openehealth.ipf.commons.ihe.xds.core.stub.ebrs21.rim.ServiceType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded" minOccurs="0">
 *         &lt;element ref="{urn:oasis:names:tc:ebxml-regrep:rim:xsd:2.1}ObjectRef"/>
 *         &lt;element ref="{urn:oasis:names:tc:ebxml-regrep:rim:xsd:2.1}ClassificationScheme"/>
 *         &lt;element ref="{urn:oasis:names:tc:ebxml-regrep:rim:xsd:2.1}ExtrinsicObject"/>
 *         &lt;element ref="{urn:oasis:names:tc:ebxml-regrep:rim:xsd:2.1}RegistryEntry"/>
 *         &lt;element ref="{urn:oasis:names:tc:ebxml-regrep:rim:xsd:2.1}RegistryObject"/>
 *         &lt;element ref="{urn:oasis:names:tc:ebxml-regrep:rim:xsd:2.1}RegistryPackage"/>
 *         &lt;element ref="{urn:oasis:names:tc:ebxml-regrep:rim:xsd:2.1}Service"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "objectRefOrClassificationSchemeOrExtrinsicObject"
})
@XmlRootElement(name = "RegistryEntryQueryResult")
public class RegistryEntryQueryResult {

    @XmlElements({
        @XmlElement(name = "Service", namespace = "urn:oasis:names:tc:ebxml-regrep:rim:xsd:2.1", type = ServiceType.class),
        @XmlElement(name = "RegistryPackage", namespace = "urn:oasis:names:tc:ebxml-regrep:rim:xsd:2.1", type = RegistryPackageType.class),
        @XmlElement(name = "RegistryEntry", namespace = "urn:oasis:names:tc:ebxml-regrep:rim:xsd:2.1", type = RegistryEntryType.class),
        @XmlElement(name = "ExtrinsicObject", namespace = "urn:oasis:names:tc:ebxml-regrep:rim:xsd:2.1", type = ExtrinsicObjectType.class),
        @XmlElement(name = "ObjectRef", namespace = "urn:oasis:names:tc:ebxml-regrep:rim:xsd:2.1", type = ObjectRefType.class),
        @XmlElement(name = "ClassificationScheme", namespace = "urn:oasis:names:tc:ebxml-regrep:rim:xsd:2.1", type = ClassificationSchemeType.class),
        @XmlElement(name = "RegistryObject", namespace = "urn:oasis:names:tc:ebxml-regrep:rim:xsd:2.1", type = RegistryObjectType.class)
    })
    protected List<Object> objectRefOrClassificationSchemeOrExtrinsicObject;

    /**
     * Gets the value of the objectRefOrClassificationSchemeOrExtrinsicObject property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the objectRefOrClassificationSchemeOrExtrinsicObject property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getObjectRefOrClassificationSchemeOrExtrinsicObject().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceType }
     * {@link RegistryPackageType }
     * {@link RegistryEntryType }
     * {@link ExtrinsicObjectType }
     * {@link ObjectRefType }
     * {@link ClassificationSchemeType }
     * {@link RegistryObjectType }
     * 
     * 
     */
    public List<Object> getObjectRefOrClassificationSchemeOrExtrinsicObject() {
        if (objectRefOrClassificationSchemeOrExtrinsicObject == null) {
            objectRefOrClassificationSchemeOrExtrinsicObject = new ArrayList<Object>();
        }
        return this.objectRefOrClassificationSchemeOrExtrinsicObject;
    }

}