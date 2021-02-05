package com.student.drop.hefanjiami.beans;

import java.io.Serializable;

public class Region implements Serializable {
    private String code;
    private String en;
    private String letter;
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public String getEn() {
        return this.en;
    }

    public void setEn(String str) {
        this.en = str;
    }

    public String getLetter() {
        return this.letter;
    }

    public void setLetter(String str) {
        this.letter = str;
    }

    public String toString() {
        return "Region{name='" + this.name + '\'' + ", code='" + this.code + '\'' + ", en='" + this.en + '\'' + '}';
    }
}
