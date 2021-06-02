import java.io.Serializable;

public class Liga implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private int number_of_team;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber_of_team() {
        return number_of_team;
    }

    public void setNumber_of_team(int number_of_team) {
        this.number_of_team = number_of_team;
    }

    public Liga(String name, int number_of_team) {

        this.name = name;
        this.number_of_team = number_of_team;
    }

    @Override
    public String toString(){
        return "Liga " + name +" posiada " + number_of_team + " drużyn.";
    }
}
