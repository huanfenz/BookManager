package com.wangpeng.bms.service.impl;

import com.wangpeng.bms.mapper.BookTypeMapper;
import com.wangpeng.bms.model.BookType;
import com.wangpeng.bms.service.BookTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class BookTypeServiceImpl implements BookTypeService {

    @Resource
    BookTypeMapper bookTypeMapper;

    @Override
    public List<BookType> queryBookTypesByPage(Integer page, Integer size) {
        return bookTypeMapper.selectAllByLimit((page - 1) * size, size);
    }

    @Override
    public Integer getCount() {
        return bookTypeMapper.selectCount();
    }

    @Override
    public Integer addBookType(BookType bookType) {
        return bookTypeMapper.insertSelective(bookType);
    }

    @Override
    public Integer deleteBookType(BookType bookType) {
        int count = 0;
        try{
            count = bookTypeMapper.deleteByPrimaryKey(bookType.getBooktypeid());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public Integer deleteBookTypes(List<BookType> bookTypes) {
        int count = 0;
        for(BookType bookType : bookTypes) {
            count += deleteBookType(bookType);
        }
        return count;
    }

    @Override
    public Integer updateBookType(BookType bookType) {
        return bookTypeMapper.updateByPrimaryKeySelective(bookType);
    }

    @Override
    public List<BookType> queryBookTypes() {
        return bookTypeMapper.selectAll();
    }

    @Override
    public int getSearchCount(Map<String, Object> searchParam) {
        return bookTypeMapper.selectCountBySearch(searchParam);
    }

    @Override
    public List<BookType> searchBookTypesByPage(Integer page, Integer size, Map<String, Object> searchParam) {
        searchParam.put("begin", (page - 1) * size);
        searchParam.put("size", size);
        return bookTypeMapper.selectBySearch(searchParam);
    }

}
