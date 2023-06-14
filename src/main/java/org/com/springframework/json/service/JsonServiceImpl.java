package org.com.springframework.json.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter.serializeAllExcept;
import static java.lang.String.format;
import static java.lang.System.out;

@Service
public class JsonServiceImpl implements JsonService {

    @Override
    public String processJson(String jsonString) throws IOException {

        ObjectMapper objectMapper= new ObjectMapper();
        String addressType = "Physical Address";

        addressOfCertainType( addressType ,
                objectMapper.readValue(jsonString, TypeFactory.defaultInstance().constructCollectionType(List.class, JsonPrettyPrintDemoBean.class)));

        SimpleBeanPropertyFilter theFilter = getTheFilter();

        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("myFilter", theFilter);

        out.println("JSON  mapping to POJO with pretty print");

        for (JsonPrettyPrintDemoBean bean : objectMapper.<List<JsonPrettyPrintDemoBean>>readValue(jsonString, TypeFactory.defaultInstance().constructCollectionType(List.class, JsonPrettyPrintDemoBean.class))) {

            String fieldValue = validateAddress(bean);

            if(StringUtils.isNotEmpty(fieldValue) && StringUtils.equals("Address Validation is successful", fieldValue)) {
                objectMapper.setFilterProvider(filters);
                objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(bean);
            }
            else new Error ( fieldValue ).printStackTrace ( );
        }
        return jsonString;

    } 

    private static void addressOfCertainType(String addressType, List<JsonPrettyPrintDemoBean> beanObject) {
        for (JsonPrettyPrintDemoBean addressVal : beanObject ) {
            if(StringUtils.equals((addressVal.getType().getName()), addressType)){
                out.println("Print an address of a certain type:-" + addressType);
                out.println(addressVal.toString());
            }
        }
    }

    /**
     * Method to DeSerialize specific parameters
     * @return
     */
    private static SimpleBeanPropertyFilter getTheFilter() {
        return serializeAllExcept("id","lastUpdated");
    }

    /**
     * Method to validate Address Json
     * @param bean
     * @return
     */
    public static String validateAddress(JsonPrettyPrintDemoBean bean) {
        /**
         * A valid address must consist of a numeric postal code, a country
         * and at least one address line that is not blank or null.
         */
        if(!((bean.getPostalCode().matches("[0-9+]{4}"))))
            return  format("Validation of Postal Code :%s" , bean.getPostalCode());
        if(!( ( null != bean.getAddressLineDetail ( ) ) && ( StringUtils.isNotEmpty ( bean.getAddressLineDetail ( ).getLine1 ( ) )
                || StringUtils.isNotEmpty ( bean.getAddressLineDetail ( ).getLine2 ( ) ) ) ) )
            return format("Validation of AddressDetail :%s", bean.getAddressLineDetail());
        if (!(StringUtils.isNotEmpty ( bean.getCountry ().getName () )))
            return format ( "Validation of CountryName :%s",
                    bean.getCountry ().getName () );

        if (StringUtils.equals("ZA", bean.getCountry().getCode()))
            if (null == bean.getProvinceOrState())
                return format("Validation of Province Fail for code ZA :%s", bean.getProvinceOrState());

        return "Address Validation is successful";
    }

}
