package com.wangpeng.bms.web;

import com.wangpeng.bms.model.BookType;
import com.wangpeng.bms.service.BookTypeService;
import com.wangpeng.bms.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/bookType")
public class BookTypeController {

    @Autowired
    BookTypeService bookTypeService;

    // 分页查询类型
    @GetMapping(value = "/queryBookTypesByPage")
    public Map<String, Object> queryBookTypesByPage(Integer page, Integer limit){
        // 获取数量
        Integer count = bookTypeService.getCount();

        // 获取数据
        List<BookType> bookTypes = bookTypeService.queryBookTypesByPage(page, limit);

        // 结果map
        Map<String,Object> res = new HashMap<String,Object>();
        res.put("code", 0);
        res.put("msg", "success");
        res.put("count", count);
        res.put("data", bookTypes);
        return res;
    }

    // 查询所有类型
    @GetMapping(value = {"/queryBookTypes", "/reader/queryBookTypes"})
    public List<BookType> queryBookTypes(){
        return bookTypeService.queryBookTypes();
    }

    // 添加类型
    @PostMapping(value = "/addBookType")
    public Integer addBookType(@RequestBody BookType bookType){
        return bookTypeService.addBookType(bookType);
    }

    // 获得数量
    @GetMapping(value = "/getCount")
    public Integer getCount(){
        return bookTypeService.getCount();
    }

    // 删除类型
    @DeleteMapping(value = "/deleteBookType")
    public Integer deleteBookType(@RequestBody BookType bookType){
        return bookTypeService.deleteBookType(bookType);
    }

    // 删除一些类型
    @DeleteMapping(value = "/deleteBookTypes")
    public Integer deleteBookTypes(@RequestBody List<BookType> bookTypes){
        return bookTypeService.deleteBookTypes(bookTypes);
    }

    // 更新类型
    @PutMapping(value = "/updateBookType")
    public Integer updateBookType(@RequestBody BookType bookType){
        return bookTypeService.updateBookType(bookType);
    }

    // 分页搜索类型
    @GetMapping("/searchBookTypesByPage")
    public Map<String,Object> searchBookTypesByPage(Integer page, Integer limit, String json){
        //获得搜索的参数
        Map<String, Object> searchParam = JsonUtil.parseMap(json, String.class, Object.class);
        //获取查询个数
        int count = bookTypeService.getSearchCount(searchParam);
        //查询数据
        List<BookType> bookTypes = bookTypeService.searchBookTypesByPage(page, limit, searchParam);
        //结果map
        Map<String,Object> res = new HashMap<String,Object>();
        res.put("code", 0);
        res.put("msg", "success");
        res.put("count", count);
        res.put("data", bookTypes);
        return res;
    }
}
