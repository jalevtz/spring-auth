package org.com.singlefile.domain.model;

public enum Gender {
    M ("MASCULINO"),
    F("FEMENINO");

    public final String label;

    private Gender(String label) {
        this.label = label;
    }

    public static Gender valueOfLabel(String label) {
        for (Gender e : values()) {
            if (e.label.equals(label)) {
                return e;
            }
        }
        return null;
    }
}
