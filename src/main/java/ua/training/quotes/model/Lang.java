package ua.training.quotes.model;

public enum Lang {
    EN, RU;

    public static Lang fromCode(String code) {
        switch (code) {
            case "en":
                return EN;
            case "ru":
                return RU;
            default:
                throw new IllegalArgumentException();
        }
    }
}
