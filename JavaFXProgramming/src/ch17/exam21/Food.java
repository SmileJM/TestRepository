package ch17.exam21;

public class Food {
    private String image;
    private String name;
    private int score;
    private String desciption;

    public Food() {
    }

    public Food(String image, String name, int score, String desciption) {
        this.image = image;
        this.name = name;
        this.score = score;
        this.desciption = desciption;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }
    
    
    
    
}
