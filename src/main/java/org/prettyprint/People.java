package org.prettyprint;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class People {
    private String name;
    private int old;
    private int height;
}
