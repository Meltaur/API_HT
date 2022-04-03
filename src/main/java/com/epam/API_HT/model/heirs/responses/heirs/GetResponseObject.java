package com.epam.API_HT.model.heirs.responses.heirs;

import com.epam.API_HT.model.heirs.responses.RsObject;
import io.restassured.response.Response;

import java.util.List;

public class GetResponseObject extends RsObject {
    private static final String RESULT_LOCATOR = "data";


    public GetResponseObject(String rsName, Response response) {
        super(rsName, response);
    }

    public List<String> getAllUsers() {
        return response.jsonPath().getList(RESULT_LOCATOR);
    }


}
