package com.example.usersstorageapp.entities;

import androidx.room.*;

import org.jetbrains.annotations.NotNull;

@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "first_name")
    private String firstName;

    @ColumnInfo(name = "last_name")
    private String lastName;

    @ColumnInfo(name = "birth_year")
    private String birthYear;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

    @NotNull
    @Override
    public String toString() {
        return this.id + ": " + this.firstName + " " + this.lastName + " " + this.birthYear;
    }
}
