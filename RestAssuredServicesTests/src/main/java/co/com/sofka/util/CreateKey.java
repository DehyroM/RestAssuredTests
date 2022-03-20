package co.com.sofka.util;

public enum CreateKey {

    NAME("[name]"),
    JOB("[job]"),
    EMAIL("[email]");

    private final String value;

    public String getValue(){
        return value;
    }

    CreateKey(String value){
        this.value = value;
    }

}
