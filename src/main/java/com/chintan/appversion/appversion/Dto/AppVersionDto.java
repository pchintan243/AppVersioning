package com.chintan.appversion.appversion.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AppVersionDto {

    private Long id;
    private String version;
    private String environment;
    private Date updatedAt;
}
