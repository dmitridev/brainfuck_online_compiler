package com.dmitridev.brainfuck.dto;

public class CharacterResponse {
    String name;
    Character ch;

    public CharacterResponse() {

    }

    public CharacterResponse(String name, Character ch) {
        this.name = name;
        this.ch = ch;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Character getCh() {
        return ch;
    }

    public void setCh(Character ch) {
        this.ch = ch;
    }
}
