package pl.michal.pajak.kalambaGamesTask.model.enums;

public enum ActionType {

    ATTAC("ATTAC", "ATAK"),
    DEFENSE("DEFENSE", "OBRONA"),
    MISSED("MISSED", "PUDŁO"),
    HIT("HIT", "TRAFIENIE");

    String name;
    String translation;

    ActionType(String name, String translation) {
        this.name = name;
        this.translation = translation;
    }

    public String getName() {
        return name;
    }

    public String getTranslation() {
        return translation;
    }

    @Override
    public String toString() {
        return "ActionType{" +
                "name='" + name + '\'' +
                ", translation='" + translation + '\'' +
                '}';
    }
}
