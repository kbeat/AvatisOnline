//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.05.29 at 05:38:34 PM ALMT 
//


package kz.avatis.online.models;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the kz.avatis.online.models package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: kz.avatis.online.models
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Records }
     * 
     */
    public Records createRecords() {
        return new Records();
    }

    /**
     * Create an instance of {@link RecordType }
     * 
     */
    public RecordType createRecordType() {
        return new RecordType();
    }

    /**
     * Create an instance of {@link Specializations }
     * 
     */
    public Specializations createSpecializations() {
        return new Specializations();
    }

    /**
     * Create an instance of {@link SpecializationType }
     * 
     */
    public SpecializationType createSpecializationType() {
        return new SpecializationType();
    }

    /**
     * Create an instance of {@link SubspecializationType }
     * 
     */
    public SubspecializationType createSubspecializationType() {
        return new SubspecializationType();
    }

    /**
     * Create an instance of {@link CompanyType }
     * 
     */
    public CompanyType createCompanyType() {
        return new CompanyType();
    }

    /**
     * Create an instance of {@link ServiceType }
     * 
     */
    public ServiceType createServiceType() {
        return new ServiceType();
    }

    /**
     * Create an instance of {@link ExpertType }
     * 
     */
    public ExpertType createExpertType() {
        return new ExpertType();
    }

}
