package com.cbr.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Identifiable implements Serializable {
    protected String id;
}
