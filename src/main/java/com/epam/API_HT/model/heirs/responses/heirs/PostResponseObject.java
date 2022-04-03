package com.epam.API_HT.model.heirs.responses.heirs;

import com.epam.API_HT.model.heirs.responses.RsObject;
import io.restassured.response.Response;

public class PostResponseObject extends RsObject {
    private static final String RESULT_ID_LOCATOR = "id";

    public PostResponseObject(String rsName, Response response) {
        super(rsName, response);
    }

    public int getUserId() {
        return response.jsonPath().get(RESULT_ID_LOCATOR);
    }
}
