package com.alpaca.lock.demo;

import jdk.nashorn.internal.objects.annotations.Getter;

import java.util.Arrays;

public enum CountryEnum {
    yi(1,"张三"),er(2,"李四"),san(3,"王五"),si(4,"赵六");

    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    CountryEnum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public static CountryEnum foreach(Integer id){
        CountryEnum[] values = CountryEnum.values();
        return Arrays.stream(values)
                .filter((e)-> id == e.getId())
                .findFirst()
                .get();

    }
}
