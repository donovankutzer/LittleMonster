package com.littlemonster.game;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class Pet {

    private int energy;
    private int hunger;
    private int happiness;
    private int hygiene;
    private int age;
    private int weight;
    private PetSprite sprite;
    private int numPoos;
    private boolean sleeping;
    private boolean alive;

    public Pet() {
        energy = 5;
        hunger = 5;
        happiness = 5;
        hygiene = 5;
        age = 0;
        weight = 0;
        numPoos = 0;
        sleeping = false;
        alive = true;
        setSprite();
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getEnergy() {
        return energy;
    }

    public int getHunger() {
        return hunger;
    }

    public void setHunger(int x) {
        if (x < 0) x = 0;
        else if (x > 10) x = 10;
        this.hunger = x;
    }

    public int getHappiness() {
        return happiness;
    }

    public void setHappiness(int x) {
        if (x < 0) x = 0;
        else if (x > 10) x = 10;
        this.hunger = x;
    }

    public int getHygiene() {
        return hygiene;
    }

    public void setHygiene(int hygiene) {
        this.hygiene = hygiene;
    }

    public int getPoos() {
        return numPoos;
    }

    public void setPoos(int x) {
        numPoos = Math.min(4, x);
    }

    public int getAge() {
        return age;
    }

    public void giveFood(FoodType food) {

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

        if (hunger > 10) hunger = 10;
        if (energy > 10) energy = 10;
        if (happiness > 10) happiness = 10;
    }

    public void setSprite() {
        if (alive) {
            if (age <= 0) sprite = PetSprite.Baby;
            else if (age <= 5) sprite = PetSprite.Kid;
            else if (age <= 10) sprite = PetSprite.Teenager;
            else sprite = PetSprite.Teenager;
        }
        else sprite = PetSprite.Dead;
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
        return "baby.png";
    }

    public void giveHappiness(int amount) {
        happiness = Math.min(10, happiness + amount);
    }

    public void giveWeight(int amount) {
        weight += amount;
    }

    public void loadPet() throws IOException {
        // Creates new file if none exists
        File file = new File("pet.data");
        if (file.createNewFile()) return;

        // Loads if file exists
        String encrypted = new String(Files.readAllBytes(Paths.get("pet.data")));
        String[] data = encrypted.split("#");
        System.out.println(Arrays.toString(data));
        age = Integer.parseInt(data[0]);
        energy = Integer.parseInt(data[1]);
        happiness = Integer.parseInt(data[2]);
        hunger = Integer.parseInt(data[3]);
        hygiene = Integer.parseInt(data[4]);
        weight = Integer.parseInt(data[5]);
        numPoos = Integer.parseInt(data[6]);
        alive = Boolean.valueOf(data[7]);

        setSprite();
    }

    public void savePet() {

        String data = age + "#" + energy + "#" + happiness + "#" + hunger + "#" + hygiene + "#" + weight + "#" + numPoos + "#" + alive;

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
        if (!alive) {
            System.out.println("ded");
            return;
        }
        if (sleeping) {
            System.out.println("Sleeping stats updating");
            energy = Math.min(energy + 1, 10);
            if (age % 10 == 0) setHunger(hunger - time);
            if (age % 30 == 0) setPoos(numPoos + 1);
            if (energy < 10) giveHappiness(1);
        } else {
            setHunger(hunger - time);
            setHappiness(happiness - time);
            if (age % 10 == 0) setPoos(numPoos + 1);
        }
        setHygiene(hygiene - numPoos);
        age += time;
        if (age >= 100 || weight >= 100) alive = false;
        setSprite();
    }

    public boolean isSleeping() {
        return sleeping;
    }

    public void setSleeping(boolean x) {
        sleeping = x;
    }

    public boolean isAlive() {
        return alive;
    }
}
