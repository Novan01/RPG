package com.rpg.character_database;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.rpg.character_classes.Character;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.*;


/*
 * 
 * Lets use an ArrayList<Character> to hold each new character as they are added in, this list will also be displayed when Play is pressed so the player can select their character
 * 
 */


public class characterDatabase {
    ArrayList<Character> characterList = new ArrayList<Character>();
    String fileName = "characterDatabase.json";
   
   
    public characterDatabase() {
        characterList = loadCharactersFromFile();
    }
    
    //method to add characters created to the json file
    public void addCharacterToList(Character player) throws JsonProcessingException {
        for(Character c : characterList) {
            if(c.getName().equals(player.getName())) {
                System.out.println("Please chose a different name");
                return;
            }
        }
        characterList.add(player);

        //serialize the character list into json
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String json = objectMapper.writeValueAsString(characterList);
       

        //write json to the file
        try(FileWriter fw = new FileWriter(fileName)) {
            fw.write(json);
        } catch (Exception e) {
            System.out.println("Failed to save character to " + e.getMessage());
        }
    }

    //method to display the characters saved in the json file
    public void displayCharacterList(ArrayList<Character> characterList) {
        
    }


    //method to load all the characters from the json file - will be used with displayCharacterList method
    public ArrayList<Character> loadCharactersFromFile() {
        
        ArrayList<Character> loadedCharacters = new ArrayList<>();
       
        try {
            File file = new File(fileName);
            ObjectMapper om = new ObjectMapper();
            om.registerSubtypes(Character.class);
            loadedCharacters = om.readValue(file, new TypeReference<ArrayList<Character>>(){});
        }
        catch(IOException e) {
            System.out.println("Failed to load characters " + e.getMessage());
        }
        return loadedCharacters;
    }

    //method to delete character based on their name id - will use in character selection screen after hitting play button
    public void deleteCharacter(String name) {
        for(Iterator<Character> iter = characterList.iterator(); iter.hasNext();) {
            Character character = iter.next();
            if(character.getName().equals(name)) {
                iter.remove();
                break;
            }
        }
    }
}