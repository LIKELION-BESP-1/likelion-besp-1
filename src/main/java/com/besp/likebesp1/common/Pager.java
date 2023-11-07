package com.besp.likebesp1.common;

public class Pager {
    public static String makePage( int pagesize, int totalCnt, int curPage) {
        String tag = "";

        //pagesize : 한페이지에 몇개씩 보여줄지
        //totalCnt : 전체 데이터 갯수
        //curPage : totalCnt / pagesize; 전체 페이지 갯수 => 나눠서 나오는 값은 무조건 올림으로
        int pageTotal = (int) Math.ceil(totalCnt / pagesize);
        int pageGroupSize = 10; // 한번에 페이지번호?를 10개 보여준당
        int pageGroupStart = 0; // 시작 페이지 위치값
        int pageGroupEnd = 0; // 페이지 그룹 마지막값

        pageGroupStart = (curPage / pageGroupSize) * pageGroupSize; // 0,10,20,30
        pageGroupEnd = pageGroupStart + pageGroupSize;
        System.out.println(totalCnt);
        if (pageGroupEnd > pageTotal) {
            pageGroupEnd = pageTotal + 1;
        }
        //맨처음페이지
        tag = tag + "<li class=\"page-item\"><a class=\"page-link\" href=\"#\" onclick=\"goPage('0')\">&lt;&lt;</a></li>";
        //이전페이지
        if (curPage > 0) {
            int temp = curPage - 1;
            tag = tag + "<li class=\"page-item\"><a class=\"page-link\" href=\"#\" onclick=\"goPage('" + temp + "')\">&lt;</a></li>";
        } else {
            tag = tag + "<li class=\"page-item\"><a class=\"page-link\" href=\"#\">&lt;</a></li>";
        }

        for (int i = pageGroupStart; i < pageGroupEnd; i++) {
            if (i == curPage) { //선택되었을때
                tag = tag + "<li class=\"page-item active\"><a class=\"page-link\" href=\"#\" "
                        + "onclick=\"goPage('" + i + "')\">" + (i + 1) + "</a></li>";
            } else {
                tag = tag + "<li class=\"page-item\"><a class=\"page-link\" href=\"#\" "
                        + "onclick=\"goPage('" + i + "')\">" + (i + 1) + "</a></li>";
            }
        }
        //다음페이지
        if (curPage < pageTotal) {
            int temp = curPage + 1;
            tag = tag + "<li class=\"page-item\"><a class=\"page-link\" href=\"#\" onclick=\"goPage('" + temp + "')\">&gt;</a></li>";
        } else {
            tag = tag + "<li class=\"page-item\"><a class=\"page-link\" href=\"#\">&gt;</a></li>";
        }

        //마지막페이지
        tag = tag + "<li class=\"page-item\"><a class=\"page-link\" href=\"#\" onclick=\"goPage('" + pageTotal + "')\">&gt;&gt;</a></li>";
        return tag;
    }
}
