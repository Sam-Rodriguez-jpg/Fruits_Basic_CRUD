package com.example.Module6_Week1_Activity1;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class MessageView {

    // Database
    ArrayList<HashMap<String, String>> fruitsList = new ArrayList<>();

    // GET ALL
    @GetMapping("/fruits")
    public ArrayList<HashMap<String, String>> getAllFruits() {
        return fruitsList;
    }



    // GET BY ID
    @GetMapping("/fruits/{id}")
    public HashMap<String, String> getByIdFruit(@PathVariable String id) {
        for(HashMap<String, String> fruit : fruitsList) {
            if(fruit.get("id").equals(id)) {
                return fruit;
            }
        }
        HashMap<String, String> responseNotContent = new HashMap<>();
        responseNotContent.put("Status_Code", "204");
        responseNotContent.put("Name_Code", "Not Content");
        responseNotContent.put("Message", "No existe ese ID");
        return responseNotContent;
    }



    // POST FOR ONE
    @PostMapping("/fruits")
    public HashMap<String, String> postFruit(@RequestBody HashMap<String, String> inputFruit) {
        HashMap<String, String> newFruit = new HashMap<>();
        newFruit.put("id", String.valueOf(fruitsList.size() + 1));
        newFruit.put("name", inputFruit.get("name"));
        newFruit.put("color", inputFruit.get("color"));
        newFruit.put("price", inputFruit.get("price"));

        fruitsList.add(newFruit);
        return newFruit;
    }



    // POST FOR MORE THAN ONE
    @PostMapping("/fruitsList")
    public ArrayList<HashMap<String, String>> postFruitList(@RequestBody ArrayList<HashMap<String, String>> fruitPostList) {
        for(HashMap<String, String> fruit : fruitPostList) {
            HashMap<String, String> newFruit = new HashMap<>();
            newFruit.put("name", fruit.get("name"));
            newFruit.put("color", fruit.get("color"));
            newFruit.put("price", fruit.get("price"));
            newFruit.put("id", String.valueOf(fruitsList.size() + 1));
            fruitsList.add(newFruit);
        }

        return fruitsList;
    }



    // PUT
    @PutMapping("/fruits/{id}")
    public HashMap<String, String> putFruit(@PathVariable String id, @RequestBody HashMap<String, String> inputFruit) {
        HashMap<String, String> responseOKUpdate = new HashMap<>();
        responseOKUpdate.put("Status_Code", "200");
        responseOKUpdate.put("Name_Code", "OK");
        responseOKUpdate.put("Message", "Fruta actualizada con éxito");

        for(HashMap<String, String> fruit : fruitsList) {
            if(fruit.get("id").equals(id)) {
                fruit.put("name", inputFruit.get("name"));
                fruit.put("color", inputFruit.get("color"));
                fruit.put("price", inputFruit.get("price"));
                return responseOKUpdate;
            }
        }
        HashMap<String, String> responseNotContent = new HashMap<>();
        responseNotContent.put("Status_Code", "204");
        responseNotContent.put("Name_Code", "Not Content");
        responseNotContent.put("Message", "No existe ese ID");
        return responseNotContent;
    }



    // PATCH
    @PatchMapping("/fruits/{id}")
    public HashMap<String, String> patchFruit(@PathVariable String id, @RequestBody HashMap<String, String> inputFruit) {
        HashMap<String, String> responseOKUpdate = new HashMap<>();
        responseOKUpdate.put("Status_Code", "200");
        responseOKUpdate.put("Name_Code", "OK");
        responseOKUpdate.put("Message", "Fruta actualizada parcialmente con éxito");

        for(HashMap<String, String> fruit : fruitsList) {
            if(fruit.get("id").equals(id)) {
                if (inputFruit.containsKey("name")) fruit.put("name", inputFruit.get("name"));
                if (inputFruit.containsKey("color")) fruit.put("color", inputFruit.get("color"));
                if (inputFruit.containsKey("price")) fruit.put("price", inputFruit.get("price"));
                return responseOKUpdate;
            }
        }
        HashMap<String, String> responseNotContent = new HashMap<>();
        responseNotContent.put("Status_Code", "204");
        responseNotContent.put("Name_Code", "Not Content");
        responseNotContent.put("Message", "No existe ese ID");
        return responseNotContent;
    }



    // DELETE
    @DeleteMapping("/fruits/{id}")
    public HashMap<String, String> deleteFruit(@PathVariable String id) {
        HashMap<String, String> responseOKDelete = new HashMap<>();
        responseOKDelete.put("Status_Code", "200");
        responseOKDelete.put("Name_Code", "OK");
        responseOKDelete.put("Message", "Fruta eliminada con éxito");

        for(HashMap<String, String> fruit : fruitsList) {
            if(fruit.get("id").equals(id)) {
                fruitsList.remove(fruit);
                return responseOKDelete;
            }
        }
        HashMap<String, String> responseNotContent = new HashMap<>();
        responseNotContent.put("Status_Code", "204");
        responseNotContent.put("Name_Code", "Not Content");
        responseNotContent.put("Message", "No existe ese ID");
        return responseNotContent;
    }
}
