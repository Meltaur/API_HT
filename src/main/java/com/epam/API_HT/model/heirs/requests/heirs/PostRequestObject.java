package com.epam.API_HT.model.heirs.requests.heirs;

import com.epam.API_HT.model.heirs.requests.RqObject;
import groovy.json.JsonException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class PostRequestObject extends RqObject {
    public PostRequestObject(String rqName){super(rqName);}

    public void createRequestForCreatingPet(Integer id, Integer idCategory, String nameCategory, String name, List<String> photoUrls, List<Integer> idTags, List<String> nameTags, String status) throws JsonException{
        setBaseUri("https://petstore.swagger.io/v2/pet");
        setCommonParams();
        requestSpecification.body(createPetAsJsonObject(id, idCategory, nameCategory, name, photoUrls, idTags, nameTags, status).toString());
    }

    private JSONObject createPetAsJsonObject(Integer id, Integer idCategory, String nameCategory, String name, List<String> photoUrls, List<Integer> idTags, List<String> nameTags, String status) throws JsonException{
        JSONObject requestBody = new JSONObject();
        requestBody.put("id", id);
            JSONObject categoryObject = new JSONObject();
            categoryObject.put("id", idCategory);
            categoryObject.put("name", nameCategory);
       requestBody.put("category", categoryObject);
       requestBody.put("name", name);
           JSONArray photosUrlsArray = new JSONArray();
           for(String photoUrl: photoUrls) {
                photosUrlsArray.put(photoUrl);
           }
       requestBody.put("photoUrls", photosUrlsArray);
       JSONArray tagsArray = new JSONArray();
       if(idTags.size() == nameTags.size()){
           for(int i =0; i<idTags.size(); i++){
               JSONObject tagObject = new JSONObject();
               tagObject.put("id", idTags.get(i));
               tagObject.put("name", nameTags.get(i));
               tagsArray.put(tagObject);
           }
       }
       requestBody.put("tags", tagsArray);
       requestBody.put("status", status);
       return requestBody;
    }
}
