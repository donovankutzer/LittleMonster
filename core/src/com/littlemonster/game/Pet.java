package com.littlemonster.game;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class Pet {

    String name;
    int energy = 5;
    int hunger = 5;
    int happiness = 5;
    int hygiene = 5;
    int age = 0;
    int weight = 0;


    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public int getEnergy() {
        return energy;
    }

    public int getHunger() {
        return hunger;
    }

    public int getHappiness() {
        return happiness;
    }

    public int getHygiene() {
        return hygiene;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }

    public void setHygiene(int hygiene) {
        this.hygiene = hygiene;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int giveFood(Food food){

        return 0;
    }

    public void loadPet() throws IOException {
        String encrypted = new String(Files.readAllBytes(Paths.get("pet.data")));
        String[] data = encrypted.split("#");
        System.out.println(Arrays.toString(data));
        setName(data[0]);
        setAge(Integer.parseInt(data[1]));
        setEnergy(Integer.parseInt(data[2]));
        setHappiness(Integer.parseInt(data[3]));
        setHunger(Integer.parseInt(data[4]));
        setHygiene(Integer.parseInt(data[5]));
        setWeight(Integer.parseInt(data[6]));
        return;
    }

    public void savePet(){
        try{
            File file = new File("pet.data");
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Unable to load/create save file");
            e.printStackTrace();
        }

        String data = "" + getName() + "#" + getAge() + "#" + getEnergy() + "#" + getHappiness() + "#" + getHunger() + "#" + getHygiene() + "#" + getWeight();

        try{
            Path path = Paths.get("pet.data");
            BufferedWriter writer = Files.newBufferedWriter(path);
            writer.write(data);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
