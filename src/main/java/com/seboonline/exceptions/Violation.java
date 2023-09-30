package com.seboonline.exceptions;

import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor
@Value
public class Violation {
    public String name;
    public String message;
}
