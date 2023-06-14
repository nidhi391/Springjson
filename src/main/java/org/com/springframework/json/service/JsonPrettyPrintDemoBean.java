package org.com.springframework.json.service;

import org.com.springframework.json.jsonprettyprinting.domain.AddressObject;
import org.com.springframework.json.jsonprettyprinting.domain.InnerObjects;
import org.codehaus.jackson.map.annotate.JsonFilter;

/**
 * Java class of the Address Json
 */
@JsonFilter("myFilter")
public class JsonPrettyPrintDemoBean {


    private int id ;
    private InnerObjects type;

    private AddressObject addressLineDetail;
    private InnerObjects provinceOrState;
    private String cityOrTown;

    private InnerObjects country;
    private String postalCode;

    private String lastUpdated;

    public int getId() {
        return id;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public InnerObjects getType() {
        return type;
    }

    public AddressObject getAddressLineDetail() {
        return addressLineDetail;
    }

    public InnerObjects getProvinceOrState() {
        return provinceOrState;
    }

    public String getCityOrTown() {
        return cityOrTown;
    }

    public void setType(InnerObjects type) {
        this.type = type;
    }

    public void setCountry(InnerObjects country) {
        this.country = country;
    }

    public InnerObjects getCountry() {
        return country;
    }


    public String getPostalCode() {
        return postalCode;
    }


    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }


    @Override
    public String toString() {
        return "JsonPrettyPrintDemoBean{" +
                "id=" + id +
                ", type=" + type +
                ", addressLineDetail=" + addressLineDetail +
                ", provinceOrState=" + provinceOrState +
                ", cityOrTown='" + cityOrTown + '\'' +
                ", country=" + country +
                ", postalCode='" + postalCode + '\'' +
                ", lastUpdated='" + lastUpdated + '\'' +
                '}';
    }


}