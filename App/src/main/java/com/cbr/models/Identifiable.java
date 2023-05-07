package com.cbr.models;

import com.cbr.App;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class Identifiable implements Serializable {
    @NotNull protected String id;
}
