package com.example.flipkartmachinecoding;

public class Data {

    private int id;
    private String imageUrl;
    private String answer;
    private int difficulty;

    public Data(int id, String imageUrl, String answer, int difficulty) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.answer = answer;
        this.difficulty = difficulty;
    }

    public int getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getAnswer() {
        return answer;
    }

    public int getDifficulty() {
        return difficulty;
    }
}
