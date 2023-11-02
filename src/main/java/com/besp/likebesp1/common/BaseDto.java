package com.besp.likebesp1.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseDto {
    protected int pg;  //페이지번호
    protected int rnum; //seq-게시물식별값, 정리된번호
    protected String searchKey = ""; //검색에 사용
    protected String searchText = "";

}