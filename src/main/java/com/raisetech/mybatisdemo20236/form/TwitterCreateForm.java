package com.raisetech.mybatisdemo20236.form;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TwitterCreateForm {
    @NotNull
    private Integer likes;

    private String followers;
}
