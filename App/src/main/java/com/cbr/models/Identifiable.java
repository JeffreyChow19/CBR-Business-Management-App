package com.cbr.models;

import com.cbr.App;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class Identifiable implements Serializable {
    protected String id;
}
