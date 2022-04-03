package com.epam.API_HT.model.heirs.responses;

import com.epam.API_HT.model.RxObject;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class RsObject extends RxObject {
    protected Response response;
    private static final String RESULT_NAME_LOCATOR = "name";

    public RsObject(String rsName, Response response){
        super(rsName);
        this.response = response;
    }

    public int getStatusCode(){return response.getStatusCode();}

    public ResponseBody getResponseBody(){return response.body();}

    public String getUserName() {
        return response.jsonPath().get(RESULT_NAME_LOCATOR);
    }
}
