package danielrocha.cinema.enums;

/**
 * Created by danielrocha on 02/08/16.
 */
public enum MovieType {
    POPULAR("popular", 0),
    TOPRATED("toprated", 1);

    private String description;
    private int intValue;

    private MovieType(String toString, int value) {
        description = toString;
        intValue = value;
    }

    public int getIntValue() {
        return intValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }

    public static MovieType getFromString(String text) {
        if (text != null) {
            for (MovieType b : MovieType.values()) {
                if (text.equalsIgnoreCase(b.toString())) {
                    return b;
                }
            }
        }
        return null;
    }
}
