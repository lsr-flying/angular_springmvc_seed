package lsr.springmvc.enums;

/**
 * Created by lsr on 2017/6/29.
 */
public enum DataStatus {

    ENABLE("1","启用"),
    DISABLE("0","停用");

    private final String value;
    private final String description;

    DataStatus(String value,String description){
        this.value = value;
        this.description = description;
    }

    public String getValue(){
        return this.value;
    }

    public String getDescription(){
        return  this.description;
    }
}
