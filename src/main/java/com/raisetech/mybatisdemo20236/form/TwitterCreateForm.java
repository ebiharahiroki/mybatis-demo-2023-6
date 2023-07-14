package com.raisetech.mybatisdemo20236.form;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TwitterCreateForm {
    @NotNull
    private Integer likes;
    //    @NotBlank
    @NotNull
    private String followers;
}
