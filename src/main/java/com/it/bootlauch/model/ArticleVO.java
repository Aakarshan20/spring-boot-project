package com.it.bootlauch.model;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonPropertyOrder(value={"content", "title"})//改變回傳的順序

public class ArticleVO {

    @JsonIgnore
    private Long id;

    @JsonProperty("auther")
    private String author;


    private String title;
    private String content;

//    @JsonInclude(JsonInclude.Include.NON_NULL) //過濾為空的值
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")//定義時間格式
//    @JsonIgnore
//    private Date createdTime;

    private List<Reader> reader;

}
