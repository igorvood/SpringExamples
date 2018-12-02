package ru.vood.spring.restFullStack.consts;

public enum CommonStatus {
    OK("OK"),
    ERROR("ERROR");

    private String nameStatus;

    CommonStatus(String nameStatus) {
        this.nameStatus = nameStatus;
    }

    @Override
    public String toString() {
        return nameStatus;
    }
}
