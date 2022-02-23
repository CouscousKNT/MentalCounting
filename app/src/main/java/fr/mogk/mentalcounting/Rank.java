package fr.mogk.mentalcounting;

public enum Rank {
    BRONZE, SILVER, GOLD;
    private Rank rank;

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public Rank getRank() {
        return rank;
    }
}
