package com.wangpeng.bms.service;

import com.wangpeng.bms.model.Borrow;

import java.util.List;
import java.util.Map;

public interface BorrowService {
    List<Borrow> queryBorrowsByPage(Integer page, Integer size);

    Integer getCount();

    Integer addBorrow(Borrow borrow);

    Integer addBorrow2(Borrow borrow);

    Integer deleteBorrow(Borrow borrow);

    Integer deleteBorrows(List<Borrow> borrows);

    Integer updateBorrow(Borrow borrow);

    Integer updateBorrow2(Borrow borrow);

    int getSearchCount(Map<String, Object> searchParam);

    List<Borrow> searchBorrowsByPage(Integer page, Integer size, Map<String, Object> searchParam);

    Integer getCountByReader(Integer userid);

    List<Borrow> queryBorrowsByPageByReader(Integer page, Integer size, Integer userid);

    Borrow queryBorrowsById(Integer borrowid);
}
