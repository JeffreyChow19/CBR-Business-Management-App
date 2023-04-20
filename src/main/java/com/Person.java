package com;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

// Use Lombok
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Person {
    private int age;
    private String name;

    // Use @NotNull from JetBrains Annotation
    public @NotNull String getNameAge(){
        return name + " " + age;
    }
}

