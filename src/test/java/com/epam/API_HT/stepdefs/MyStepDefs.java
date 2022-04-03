package com.epam.API_HT.stepdefs;

import com.epam.API_HT.core.store.RxStore;
import com.epam.API_HT.log.Log;
import com.epam.API_HT.model.heirs.requests.RqObject;
import com.epam.API_HT.model.heirs.requests.heirs.DeleteRequestObject;
import com.epam.API_HT.model.heirs.requests.heirs.GetRequestObject;
import com.epam.API_HT.model.heirs.requests.heirs.PostRequestObject;
import com.epam.API_HT.model.heirs.responses.RsObject;
import com.epam.API_HT.model.heirs.responses.heirs.DeleteResponseObject;
import com.epam.API_HT.model.heirs.responses.heirs.GetResponseObject;
import com.epam.API_HT.model.heirs.responses.heirs.PostResponseObject;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class MyStepDefs {
    private RxStore rxStore = RxStore.getInstance();

    @Given("user sends {string} request with following parameters")
    public void userSendsRequestWithFollowingParameters(String rqName, DataTable petInfo) {

        Integer id = Integer.parseInt(petInfo.cell(1,0));
        Integer idCategory = Integer.parseInt(petInfo.cell(1,1));
        String nameCategory = petInfo.cell(1,2);
        String name = petInfo.cell(1,3);
        List<String> photoUrls = new ArrayList<>();
        photoUrls.add(petInfo.cell(1,4));
        List <Integer> idTags = new ArrayList<>();
        idTags.add(Integer.parseInt(petInfo.cell(1,5)));
        List<String> nameTags = new ArrayList<>();
        nameTags.add((petInfo.cell(1,6)));
        String status = petInfo.cell(1,7);
        PostRequestObject sendPetRq = new PostRequestObject(rqName);
        sendPetRq.createRequestForCreatingPet(id, idCategory, nameCategory, name, photoUrls, idTags, nameTags, status);
        rxStore.putDataIntoStore(sendPetRq.getRxName(), sendPetRq);
        String rsObject = rqName.replace("RQ","RS");
        RsObject receivedResponse = new PostResponseObject(rsObject, sendPetRq.sendPostRequest());
    }

    @Given("user has {string} request with id {string}")
    public void userHasRequestWithId(String rqName, String id) {
        GetRequestObject getRequestObject = new GetRequestObject(rqName);
        getRequestObject.createRequestForGettingPetById(id);
        rxStore.putDataIntoStore(getRequestObject.getRxName(), getRequestObject);
    }

    @Given("user has {string} request and id {string}")
    public void userHasRequestAndId(String rqName, String id) {
        DeleteRequestObject deleteRequestObject = new DeleteRequestObject(rqName);
        deleteRequestObject.createRequestForDeletingUser(id);
        rxStore.putDataIntoStore(deleteRequestObject.getRxName(), deleteRequestObject);
    }


    @When("user sends {string} {string} request with id {string}")
    public void userSendsRequestWithId(String rqMethod, String rqName, String id) {

        RqObject rqObject = (RqObject) rxStore.getDataFromStore(rqName);
        String rsObject = rqName.replace("RQ","RS");
        Log.info("Request type: " + rqMethod);
        RsObject response = switch (rqMethod){
            case "GET" -> new GetResponseObject(rsObject, rqObject.sendGetRequest());
            case "DELETE" -> new DeleteResponseObject(rsObject, rqObject.sendDeleteRequest());
            default -> throw new IllegalStateException("Unexpected value: " + rqMethod);
        };
        rxStore.putDataIntoStore(rsObject, response);
    }

    @When("user sends POST {string} request")
    public void userSendsRequest(String rqName) {
        Log.info("Request type: POST");
        RqObject rqObject = (RqObject) rxStore.getDataFromStore(rqName);
        String rsObject = rqName.replace("RQ","RS");
        RsObject response = new PostResponseObject(rsObject, rqObject.sendPostRequest());
        rxStore.putDataIntoStore(rsObject, response);
    }


    @Then("{string} code is {string}")
    public void codeIs(String rsName, String code) {
        RsObject rsObject = (RsObject) rxStore.getDataFromStore(rsName);
        Assert.assertEquals(String.valueOf(rsObject.getStatusCode()), code,
                "Status code is not as expected");
        Log.info("Response body:" + rsObject.getResponseBody().prettyPrint());
    }

    @And("name of pet in {string} is {string}")
    public void nameOfPetInIs(String rsName, String petName) {
        RsObject rsObject = (RsObject) rxStore.getDataFromStore(rsName);
        Assert.assertEquals(rsObject.getUserName(), petName, "Pet's name is not Murcia");
    }
}
