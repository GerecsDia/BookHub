package com.geolidth.BackEnd.models.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class UpdateBook {

    private Integer id;
    private String title;
    private String author;
    private String publisher;
    private String category;
    private String county;
    private String quality;
    private Integer year;
    private Boolean reserved;
}
