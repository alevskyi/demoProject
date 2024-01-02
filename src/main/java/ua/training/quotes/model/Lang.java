package ua.training.quotes.model;

public enum Lang {
    EN, UA;

    public static Lang fromCode(String code) {
        switch (code) {
            case "en":
                return EN;
            case "ua":
                return UA;
            default:
                throw new IllegalArgumentException();
        }
    }
}
