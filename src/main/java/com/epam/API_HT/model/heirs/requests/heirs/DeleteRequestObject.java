package com.epam.API_HT.model.heirs.requests.heirs;

import com.epam.API_HT.model.heirs.requests.RqObject;

public class DeleteRequestObject extends RqObject {
    public DeleteRequestObject(String rqName) {
        super(rqName);
    }

    public void createRequestForDeletingUser(String userId) {
        setBaseUri("https://petstore.swagger.io/v2/pet/" + userId);
        setCommonParams();
    }
}
