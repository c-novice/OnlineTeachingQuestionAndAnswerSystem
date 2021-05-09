package com.pojo;

public class Message {
    private String usernameFrom;
    private String usernameTo;
    private String context;
    private Integer type;
    private String id;
    private Integer checked = 0;

    public Message() {
    }

    public Message(String usernameFrom, String usernameTo, Integer type) {
        this.usernameFrom = usernameFrom;
        this.usernameTo = usernameTo;
        this.type = type;
    }

    public Message(String usernameFrom, String usernameTo, String context, Integer type, String id, Integer checked) {
        this.usernameFrom = usernameFrom;
        this.usernameTo = usernameTo;
        this.context = context;
        this.type = type;
        this.id = id;
        this.checked = checked;
    }

    public String getUsernameFrom() {
        return usernameFrom;
    }

    public void setUsernameFrom(String usernameFrom) {
        this.usernameFrom = usernameFrom;
    }

    public String getUsernameTo() {
        return usernameTo;
    }

    public void setUsernameTo(String usernameTo) {
        this.usernameTo = usernameTo;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getChecked() {
        return checked;
    }

    public void setChecked(Integer checked){
        this.checked=checked;
    }

    @Override
    public String toString() {
        return "Message{" +
                "usernameFrom='" + usernameFrom + '\'' +
                ", UsernameTo='" + usernameTo + '\'' +
                ", context='" + context + '\'' +
                ", type=" + type +
                ", id='" + id + '\'' +
                ", checked=" + checked +
                '}';
    }
}
