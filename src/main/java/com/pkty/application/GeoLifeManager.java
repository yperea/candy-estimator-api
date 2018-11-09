package com.pkty.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pkty.domain.ChildrenPopulation;
import com.pkty.util.PropertiesLoader;
import lombok.Data;
import org.apache.taglibs.standard.tag.common.core.ForEachSupport;
import org.glassfish.jersey.internal.util.Base64;
import com.pkty.vendor.pb.geolife.*;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Properties;

@Data
public class GeoLifeManager implements PropertiesLoader {

    //private static  String LOCATION_INTELLIGENCE_API_URL_SEGMENTATION = "https://api.pitneybowes.com/location-intelligence/"+API_FRAGMENT_SEGMENTATION;
    private static final String ACCESS_TOKEN = "access_token";
    private static final String BEARER = "Bearer ";
    private static final String BASIC = "Basic ";
    private static final String CLIENT_CREDENTIALS = "client_credentials";
    private static final String GRANT_TYPE = "grant_type";
    private static final String AUTH_HEADER = "Authorization";
    private static final String COLON = ":";

    private Properties properties;
    private String format;
    private String level;
    private String profile;
    private String filter;
    private String accessToken;

    public GeoLifeManager (String propertiesFileName) {
        properties = loadProperties(propertiesFileName);
        format = "Both";
        level = "All";
    }

    private void acquireAuthToken() {

        String authHeader = BASIC
                + Base64.encodeAsString(properties.getProperty("geolife.apikey")
                + COLON + properties.getProperty("geolife.secret"));

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(properties.getProperty("geolife.oauth2.token.url"));

        Invocation.Builder builder = target.request().header(AUTH_HEADER, authHeader);
        Form form = new Form();
        form.param(GRANT_TYPE, CLIENT_CREDENTIALS);
        Response response = builder.post(Entity.entity(form,
                MediaType.APPLICATION_FORM_URLENCODED));
        String jsonResponse = response.readEntity(String.class);

        JsonReader jsonReader = Json.createReader(new StringReader(jsonResponse));
        JsonObject jsonObject = jsonReader.readObject();
        jsonReader.close();
        accessToken = jsonObject.getString(ACCESS_TOKEN);
        accessToken = BEARER + accessToken;
    }

    public ChildrenPopulation getChildrenPopulation (String address, String country)
            throws IOException {

        String addressEncoded = URLEncoder.encode(address, StandardCharsets.UTF_8.toString());
        //String countryEncoded = URLEncoder.encode(country, StandardCharsets.UTF_8.toString());
        ChildrenPopulation childrenPopulation = new ChildrenPopulation();

        String apiUrl = getGeoLifeDemographicsByAddressUrl(addressEncoded, country,
                                                           profile, filter, format, level);

        acquireAuthToken();

        processRequestDemographicsV2(false, apiUrl);

        return childrenPopulation;
    }


    private static String getGeoLifeDemographicsByAddressUrl(String address, String country,
                                                           String profile, String filter,
                                                           String format, String level) {

        String apiUrl = "byaddress?address=" + address;

        if (filter != null && filter != "") {
            apiUrl += "&filter=" + filter;
        }
        if (profile != null && profile != "") {
            apiUrl += "&profile=" + profile;
        }
        if (country != null && country != "") {
            apiUrl += "&country=" + country;
        }
        if (format != null && level != "") {
            apiUrl += "&valueFormat=" + format;
        }
        if (level != null && level  != "") {
            apiUrl += "&variableLevel=" + level ;
        }

        return apiUrl;
    }

    private void processRequestDemographicsV2(boolean responseTypeIsXml, String apiUrl)
            throws IOException {

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(properties.getProperty("geolife.location.intelligence.url")
                                        + properties.getProperty("geolife.urlpart.demographics.v2")
                                        + apiUrl);
        Invocation.Builder builder;

        if (responseTypeIsXml) {
            builder = target.request(MediaType.APPLICATION_XML).header(
                    AUTH_HEADER, accessToken);
        } else {
            builder = target.request(MediaType.APPLICATION_JSON).header(
                    AUTH_HEADER, accessToken);
        }

        String response = builder.get(String.class);

        System.out.println(response);

        ObjectMapper mapper = new ObjectMapper();

        Demographic demographic = mapper.readValue(response, Demographic.class);

        System.out.println(demographic.getThemes().getPopulationTheme().getIndividualValueVariable().get(0).getName());

        List<RangeVariableItem> rangeVariableItems = demographic.getThemes().getPopulationTheme().getRangeVariable();

        int totalChildrenPopulation = 0;

        for ( RangeVariableItem rangeVariableItem : rangeVariableItems) {
            if (rangeVariableItem.getName() ==  "AGEPCY") {

                List<FieldItem> items = rangeVariableItem.getField();

                //P0004CY Population Under 5 Years
                //P0509CY Population 5 To 9 Years
                //P1014CY Population 10 To 14 Years

                for ( FieldItem item : items) {
                    if (item.getName() == "P0004CY" || item.getName() == "P0509CY" || item.getName() == "P1014CY" ) {
                        totalChildrenPopulation += Integer.parseInt(item.getValue());
                    }
                }
            }
        }

        RangeVariableItem rangeVariableItem = rangeVariableItems.stream().filter(e -> e.getName().equals("P0004CY")).findFirst().get();
    }

}
