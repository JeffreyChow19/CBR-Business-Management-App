package com;
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        // Construct Jimly using @NoArgsConstructor
        Person jimly = new Person();

        // Construct WT using @AllArgsConstructor
        Person wt = new Person(28, "Wilson Tansil");

        // Try to use @Getter
        System.out.println(jimly.getName());
        System.out.println(jimly.getNameAge());
        System.out.println(jimly.getAge());

        // Try to use @Setter
        jimly.setName("Jimly Firdaus");
        jimly.setAge(30);
        System.out.println(jimly.getName());
        System.out.println(jimly.getNameAge());
        System.out.println(jimly.getAge());

        System.out.println(wt.getAge());
        System.out.println(wt.getName());
    }
}

