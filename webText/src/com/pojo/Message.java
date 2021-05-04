package com.pojo;

public class Message {
    private String usernameFrom;
    private String UsernameTo;
    private String context;
    private Integer type;
    private Integer checked = 0;

    public Message() {
    }

    public Message(String usernameFrom, String usernameTo, String context, Integer type, Integer checked) {
        this.usernameFrom = usernameFrom;
        UsernameTo = usernameTo;
        this.context = context;
        this.type = type;
        this.checked = checked;
    }

    public String getUsernameFrom() {
        return usernameFrom;
    }

    public void setUsernameFrom(String usernameFrom) {
        this.usernameFrom = usernameFrom;
    }

    public String getUsernameTo() {
        return UsernameTo;
    }

    public void setUsernameTo(String usernameTo) {
        UsernameTo = usernameTo;
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

    public Integer getChecked() {
        return checked;
    }

    public void setChecked(Integer checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        return "Message{" +
                "usernameFrom='" + usernameFrom + '\'' +
                ", UsernameTo='" + UsernameTo + '\'' +
                ", context='" + context + '\'' +
                ", type=" + type +
                ", checked=" + checked +
                '}';
    }
}
