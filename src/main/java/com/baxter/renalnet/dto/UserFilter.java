package com.baxter.renalnet.dto;

import lombok.Data;

@Data
public class UserFilter {
    Integer page;
    Integer size;
    String name;
    String address;
    String email;
    Boolean active;
}
