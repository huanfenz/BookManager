package com.wangpeng.bms.service;

import com.wangpeng.bms.model.BookInfo;

import java.util.List;
import java.util.Map;

public interface BookInfoService {
    List<BookInfo> queryBookInfosByPage(Integer page, Integer size);

    Integer getCount();

    Integer addBookInfo(BookInfo bookInfo);

    Integer deleteBookInfo(BookInfo bookInfo);

    Integer deleteBookInfos(List<BookInfo> bookInfos);

    Integer updateBookInfo(BookInfo bookInfo);

    Integer getSearchCount(Map<String, Object> searchParam);

    List<BookInfo> searchBookInfosByPage(Integer page, Integer size, Map<String, Object> searchParam);

    List<BookInfo> queryBookInfos();

    Integer getCountByType(Map<String, Object> map);

    List<BookInfo> queryBookInfosByPageByType(Integer page, Integer size, Map<String, Object> map);

    BookInfo queryBookInfoById(Integer bookid);
}
