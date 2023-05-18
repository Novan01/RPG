package com.rpg.character_database;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.rpg.character_classes.Character;
import java.util.ArrayList;

import org.bson.Document;
import org.bson.conversions.Bson;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Filters;



/*
 * 
 * Lets use an ArrayList<Character> to hold each new character as they are added in, this list will also be displayed when Play is pressed so the player can select their character
 * 
 */


public class characterDatabase {
    private static final String DB_NAME = "characterDB";
    private static final String COLLECTION_NAME = "characterCollection";

    private MongoClient mongoClient;
    private MongoDatabase mongoDB;
    private MongoCollection<Document> collection;
   
   
    public characterDatabase() {
        //new MongoClientConnection(password);

        //initialize the mongo client and connect to the database
        mongoClient = MongoClients.create("mongodb+srv://Novan01:HBHUfG8DKKft36uG@clustermain.igb57xa.mongodb.net/?retryWrites=true&w=majority");
        mongoDB = mongoClient.getDatabase(DB_NAME);

        //get or create the characters collection
        collection = mongoDB.getCollection(COLLECTION_NAME);
    }
    
    //method to add characters created to the json file
    public void addCharacterToList(Character player) {
        Bson filter = Filters.eq("name", player.getName());

        if(collection.countDocuments(filter) > 0) {
            System.out.println("Please chose a different name");
            return;

        }

        //increment the id of the character
        Document maxID = collection.find().sort(Sorts.descending("_id")).limit(1).first();
        int nextID = (maxID != null) ? maxID.getInteger("_id") + 1 : 1;
        
        Document characterDoc = new Document("_id", nextID).append("name", player.getName()).append("characterClass", player.getCharClass()).append("health", player.getHealth()).append("strength", player.getStrength()).append("dexterity", player.getDexterity()).append("intelligence", player.getIntelligence());
        collection.insertOne(characterDoc);
    }

    //method to display the characters saved in the json file
    public void displayCharacterList(ArrayList<Character> characterList) {
        
    }


    //method to load all the characters from the json file - will be used with displayCharacterList method
    public ArrayList<Character> loadCharactersFromFile() {
        
        ArrayList<Character> loadedCharacters = new ArrayList<>();
       
        for(Document doc : collection.find()) {
            String name = doc.getString("name");
            String characterClass = doc.getString("characterClass");
            int health = doc.getInteger("health");
            int strength = doc.getInteger("strength");
            int dexterity = doc.getInteger("dexterity");
            int intelligence = doc.getInteger("intelligence");

            Character character = new Character(name, characterClass, health, strength, dexterity, intelligence);
            loadedCharacters.add(character);
        }
        return loadedCharacters;
    }

    //method to delete character based on their name id - will use in character selection screen after hitting play button
    public void deleteCharacter(String name) {
        Bson filter = Filters.eq("name", name);

        collection.deleteOne(filter);
    }
}