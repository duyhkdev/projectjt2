package com.duyhk.apibanhang.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MauSacDTO {
    private Long id;
    @Size(min = 3, message = "ten phai tren 3 ki tu")
    @NotNull
    private String ten;
}
