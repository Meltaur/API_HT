package com.epam.API_HT.model.heirs.requests.heirs;

import com.epam.API_HT.model.heirs.requests.RqObject;

public class GetRequestObject extends RqObject {
    public GetRequestObject(String rqName) {super(rqName);}


public void createRequestForGettingPetById(String userId){
        setBaseUri("https://petstore.swagger.io/v2/pet/" + userId);
        setCommonParams();
}

}
