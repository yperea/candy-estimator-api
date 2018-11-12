package com.pkty.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pkty.domain.ChildrenPopulation;
import com.pkty.util.PropertiesLoader;
import lombok.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.internal.util.Base64;
import com.pkty.vendor.pb.geolife.*;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.persistence.Transient;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.StringReader;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Properties;

/**
 * Handles operations with Geo Life API service https://api.pitneybowes.com/location-intelligence.
 */
@Data
public class GeoLifeManager implements PropertiesLoader {

    private static final String ACCESS_TOKEN = "access_token";
    private static final String BEARER = "Bearer ";
    private static final String BASIC = "Basic ";
    private static final String CLIENT_CREDENTIALS = "client_credentials";
    private static final String GRANT_TYPE = "grant_type";
    private static final String AUTH_HEADER = "Authorization";
    private static final String COLON = ":";

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private final Logger logger = LogManager.getLogger(this.getClass());

    private Properties properties;
    private String format;
    private String level;
    private String profile;
    private String filter;
    private String accessToken;

    /**
     * Instantiates a new Geo life manager.
     *
     * @param propertiesFileName the properties file name
     */
    public GeoLifeManager (String propertiesFileName) {
        logger.info("GeoLifeManager(String): Instantiating GeoLifeManager class.");

        properties = loadProperties(propertiesFileName);
        format = "CountOnly"; // It tells the GeoLife API to return values based on the population count, not percentages.
        level = "Key"; //Returns only key variables, not detailed information.
        filter = "populationTheme"; // Reducing the query to population data only.
        acquireAuthToken();
    }

    /**
     * Get Access Token to use pitneybowes API.
     *
     */
    private void acquireAuthToken() {

        logger.info("acquireAuthToken(): Acquiring tocken authentication.");
        logger.trace("acquireAuthToken(): Acquiring tocken authentication from "
                    + properties.getProperty("geolife.oauth2.token.url"));

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

    /**
     * Gets children population object for an address given.
     *
     * @param address the address
     * @param country the country
     * @return the children population by address
     * @throws IOException the io exception
     */
    public ChildrenPopulation getChildrenPopulationByAddress (String address, String country)
            throws IOException {

        logger.info ("getChildrenPopulationByAddress().");
        logger.debug ("getChildrenPopulationByAddress(): Begin.");

        ChildrenPopulation childrenPopulation = new ChildrenPopulation();
        String encodedAddress = URLEncoder.encode(address, StandardCharsets.UTF_8.toString());
        String encodedCountry = URLEncoder.encode(country, StandardCharsets.UTF_8.toString());

        logger.trace("getChildrenPopulationByAddress() :: calling getGeoLifeDemographicsByAddressUrl(): "
                + "address: " + encodedAddress + " | "
                + "country: " + encodedCountry + " | "
                + "profile: " + profile + " | "
                + "filter: " + filter + " | "
                + "format: " + format + " | "
                + "level: " + level);

        String apiUrl = getGeoLifeDemographicsByAddressUrl(encodedAddress, encodedCountry,
                                                           profile, filter, format, level);

        logger.trace("getChildrenPopulationByAddress() : calling getPopulationCountByAgeRange(): "
                + "apiUrl: " + apiUrl);

        RangeVariableItem populationCountByAgeRange = getPopulationCountByAgeRange(apiUrl);

        logger.trace("getChildrenPopulationByAddress(): calling getCountPopulationUnder14():  "
                + "populationCountByAgeRange: " + populationCountByAgeRange);

        int populationUnder14 = getCountPopulationUnder14(populationCountByAgeRange);

        childrenPopulation.setAddress(address);
        childrenPopulation.setCountry(country);
        childrenPopulation.setCount(populationUnder14);

        logger.debug ("getChildrenPopulationByAddress(): End.");
        return childrenPopulation;
    }

    /**
     * Gets a well-formed URL to consume the GeoLife services.
     *
     * @param address the address
     * @param country the country
     * @param profile the sorted demographic data on the basis of pre-defined profiles, the top 3 or top 5 results.
     * @param filter the demographic data based upon a single theme input, for example; populationTheme
     * @param format the value based on the population count or in percentages
     * @param level allowed values - Key, Detailed, Advanced, and All
     * @return well-formed URL including the given arguments
     */
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

    /**
     * Gets a well-formed URL to consume the GeoLife services.
     *
     * @param apiUrl the URL to consume the GeoLife services
     * @return json response
     */
    private String getDemographicsV2JsonResponse(String apiUrl){

        logger.debug ("getDemographicsV2JsonResponse(): Begin.");

        boolean responseTypeIsXml = false;
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
        logger.debug ("getDemographicsV2JsonResponse(): End.");
        return response;
    }

    /**
     * Gets a GeoLife Demographic Object with the encapsulated response.
     *
     * @param apiUrl the URL to consume the GeoLife services
     * @return Demographic object
     */
    private Demographic getDemographicsV2(String apiUrl)
            throws IOException {

        logger.debug ("getDemographicsV2(): Begin.");
        ObjectMapper mapper = new ObjectMapper();
        String response = properties.getProperty("geolife.json.sample");

        if (properties.get("geolife.alive").equals("1")) {
            response = getDemographicsV2JsonResponse(apiUrl);
        }

        Demographic demographic = mapper.readValue(response, Demographic.class);
        logger.debug ("getDemographicsV2(): End.");
        return demographic;
    }

    /**
     * Gets a Population object based on Age ranges.
     *
     * @param apiUrl the URL to consume the GeoLife services
     * @return Population object
     */
    private RangeVariableItem getPopulationCountByAgeRange(String apiUrl)
            throws IOException {

        logger.debug ("getPopulationCountByAgeRange(): Begin.");
        Demographic demographic = getDemographicsV2(apiUrl);

        RangeVariableItem rangeVariableItem = demographic.getThemes()
                .getPopulationTheme()
                .getRangeVariable()
                .stream().filter(d -> d.getAlias().equals("Age"))
                .findFirst().orElse(null);
                //.get();
                //.collect(Collectors.toList());

        logger.debug ("getPopulationCountByAgeRange(): End.");
        return rangeVariableItem;
    }

    /**
     * Gets a Population object based on Age ranges.
     *
     * @param rangeVariableItem Population object
     * @return The number of children under 14 years.
     */
    private int getCountPopulationUnder14 (RangeVariableItem rangeVariableItem) {

        logger.debug ("getCountPopulationUnder14(): Begin.");
        int totalChildrenPopulation = 0;
        List<FieldItem> items = rangeVariableItem.getField();

        /*
        for ( FieldItem item : items) {
            System.out.println(rangeVariableItem.getName());
            if (item.getName().equals("P0004CY")
                    || item.getName().equals("P0509CY")
                    || item.getName().equals("P1014CY")  ) {
                totalChildrenPopulation += Integer.parseInt(item.getValue());
            }
        }
        */

        totalChildrenPopulation = items.stream()
                .filter(i -> i.getName().equals("P0004CY") //P0004CY Population Under 5 Years
                        || i.getName().equals("P0509CY")   //P0509CY Population 5 To 9 Years
                        || i.getName().equals("P1014CY"))  //P1014CY Population 10 To 14 Years
                .mapToInt(i -> Integer.parseInt(i.getValue()))
                .sum();

        logger.debug ("getCountPopulationUnder14(): End.");
        return totalChildrenPopulation;
    }
}
