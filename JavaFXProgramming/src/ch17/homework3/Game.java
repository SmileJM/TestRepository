package ch17.homework3;

public class Game {
    private String name;
    private String company;
    private String view;
    private int score;
    private int rank;

    public Game() {
    }

    public Game(int rank, String name, String company, String view, int score) {
        this.rank = rank;
        this.name = name;
        this.company = company;
        this.view = view;
        this.score = score;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
