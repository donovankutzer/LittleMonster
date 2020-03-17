package com.littlemonster.game;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class Pet {

    private String name;
    private int energy = 5;
    private int hunger = 5;
    private int happiness = 5;
    private int hygiene = 5;
    private int age = 0;
    private int weight = 0;
    private PetSprite sprite;

    public Pet() {
        setSprite();
    }

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

    public boolean giveFood(FoodType food) {

        if (hunger >= 10) {
            return false;
        }

        switch (food) {
            case Bread:
                energy += 2;
                weight += 1;
                happiness += 1;
                hunger += 3;
                break;
            case Broccoli:
                energy += 3;
                weight += 1;
                happiness -= 1;
                hunger += 3;
                break;
            case Chicken:
                energy += 3;
                weight += 2;
                happiness += 2;
                hunger += 4;
                break;
            case Eggs:
                energy += 2;
                weight += 1;
                hunger += 4;
                break;
            case Liver:
                energy += 4;
                weight += 1;
                happiness -= 2;
                hunger += 2;
            case Candy:
                energy += 3;
                weight += 5;
                happiness += 5;
                break;
            case Chocolate:
                energy += 5;
                weight += 5;
                happiness += 5;
                hunger += 1;
                break;
        }

        if (hunger > 10) {
            hunger = 10;
        }
        return true;
    }

    public void setSprite() {
        if (age <= 0) sprite = PetSprite.Baby;
        else if (age <= 5) sprite = PetSprite.Kid;
        else if (age <= 10) sprite = PetSprite.Teenager;
        else sprite = PetSprite.Teenager;
    }

    public String getSprite() {
        switch (sprite) {
            case Baby:
                return "baby.png";
            case Kid:
                return "kid.png";
            case Teenager:
                return "teenager.png";
        }
        return "";
    }

    public void giveHappiness(int amount) {
        happiness += amount;
    }

    public void giveWeight(int amount) {
        weight += amount;
    }

    public void loadPet() throws IOException {
        String encrypted = new String(Files.readAllBytes(Paths.get("pet.data")));
        String[] data = encrypted.split("#");
        System.out.println(Arrays.toString(data));
        name = data[0];
        age = Integer.parseInt(data[1]);
        energy = Integer.parseInt(data[2]);
        happiness = Integer.parseInt(data[3]);
        hunger = Integer.parseInt(data[4]);
        hygiene = Integer.parseInt(data[5]);
        weight = Integer.parseInt(data[6]);
        setSprite();
    }

    public void savePet() {
        try {
            File file = new File("pet.data");
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Unable to load/create save file");
            e.printStackTrace();
        }

        String data = "" + name + "#" + age + "#" + energy + "#" + happiness + "#" + hunger + "#" + hygiene + "#" + weight;

        try {
            Path path = Paths.get("pet.data");
            BufferedWriter writer = Files.newBufferedWriter(path);
            writer.write(data);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void updateStats(int time) {
        age += time;
        hunger -= time;
        happiness -= time;
        if (age % 10 == 0) hygiene -= 3 * time;
        setSprite();
    }

}
