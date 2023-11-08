package com.besp.likebesp1.common;

public class Pager {
    public static String makePage( int imgNum, int totalImg, int curPage) {
        //imgNum : 한 페이지 당 보여지는 이미지 수
        //totalImg : 전체 이미지
        //curPage : 전체 페이지 수 (totalImg / imgNum)
        String tag="";
        int totalPage = (int)Math.ceil(totalImg / imgNum);
        int pageGroupNum = 10; //페이지 번호 10개씩
        int pageGroupStart = 0; // 페이지 시작값
        int pageGroupEnd = 0; // 페이지 마지막값

        pageGroupStart = (curPage / pageGroupNum) * pageGroupNum; //0,10,20,30
        pageGroupEnd =  pageGroupStart + pageGroupNum;
        System.out.println(totalImg);

        if(pageGroupEnd > totalPage) {
            pageGroupEnd = totalPage + 1;
        }

        //맨 처음 페이지
        tag = tag + "<li class=\"page-item\"><a class=\"page-link\" href=\"#\" onclick=\"goPage('0')\">&lt;&lt;</a></li>";

        //이전 페이지
        if(curPage > 0) {
            int temp = curPage - 1;
            tag = tag + "<li class=\"page-item\"><a class=\"page-link\" href=\"#\" onclick=\"goPage('"+temp+"')\">&lt;</a></li>";
        }else {
            tag = tag + "<li class=\"page-item\"><a class=\"page-link\" href=\"#\">&lt;</a></li>";
        }

        for(int i = pageGroupStart; i < pageGroupEnd; i++) {
            if(i == curPage) { //선택되었을 때
                tag = tag + "<li class=\"page-item active\"><a class=\"page-link\" href=\"#\" "
                        + "onclick=\"goPage('"+i+"')\">"+(i+1)+"</a></li>";
            }else {
                tag = tag + "<li class=\"page-item\"><a class=\"page-link\" href=\"#\" "
                        + "onclick=\"goPage('"+i+"')\">"+(i+1)+"</a></li>";
            }
        }

        //다음 페이지
        if(curPage < totalPage) {
            int temp = curPage + 1;
            tag = tag + "<li class=\"page-item\"><a class=\"page-link\" href=\"#\" onclick=\"goPage('"+temp+"')\">&gt;</a></li>";
        }else {
            tag = tag + "<li class=\"page-item\"><a class=\"page-link\" href=\"#\">&gt;</a></li>";
        }

        //마지막 페이지
        tag = tag + "<li class=\"page-item\"><a class=\"page-link\" href=\"#\" onclick=\"goPage('"+totalPage+"')\">&gt;&gt;</a></li>";

        return tag;
    }
}
