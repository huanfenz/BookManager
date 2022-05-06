package com.wangpeng.bms.web;

import com.wangpeng.bms.model.BookInfo;
import com.wangpeng.bms.service.BookInfoService;
import com.wangpeng.bms.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/bookInfo")
public class BookInfoController {

    @Autowired
    BookInfoService bookInfoService;

    // 分页查询图书信息
    @GetMapping(value = "/queryBookInfosByPage")
    public Map<String, Object> queryBookInfosByPage(Integer page, Integer limit){
        // 获取数量
        Integer count = bookInfoService.getCount();
        // 获取数据
        List<BookInfo> bookInfos = bookInfoService.queryBookInfosByPage(page, limit);
        // 结果map
        return getStringObjectMap(count, bookInfos);
    }

    private Map<String, Object> getStringObjectMap(Integer count, List<BookInfo> bookInfos) {
        Map<String,Object> res = new HashMap<String,Object>();
        res.put("code", 0);
        res.put("msg", "success");
        res.put("count", count);
        res.put("data", bookInfos);
        return res;
    }

    // 查询所有图书信息
    @GetMapping(value = "/queryBookInfos")
    public List<BookInfo> queryBookInfos(){
        return bookInfoService.queryBookInfos();
    }

    // 添加图书信息
    @PostMapping(value = "/addBookInfo")
    public Integer addBookInfo(@RequestBody BookInfo bookInfo){
        return bookInfoService.addBookInfo(bookInfo);
    }

    // 获取图书数量
    @GetMapping(value = "/getCount")
    public Integer getCount(){
        return bookInfoService.getCount();
    }

    // 删除图书信息
    @DeleteMapping(value = "/deleteBookInfo")
    public Integer deleteBookInfo(@RequestBody BookInfo bookInfo){
        return bookInfoService.deleteBookInfo(bookInfo);
    }

    // 删除一些图书信息
    @DeleteMapping(value = "/deleteBookInfos")
    public Integer deleteBookInfos(@RequestBody List<BookInfo> bookInfos){
        return bookInfoService.deleteBookInfos(bookInfos);
    }

    // 更新图书信息
    @PutMapping(value = "/updateBookInfo")
    public Integer updateBookInfo(@RequestBody BookInfo bookInfo){
        return bookInfoService.updateBookInfo(bookInfo);
    }

    // 查询图书信息通过分页
    @GetMapping("/searchBookInfosByPage")
    public Map<String,Object> searchBookInfosByPage(Integer page, Integer limit, String json){
        //获得搜索的参数
        Map<String, Object> searchParam = JsonUtil.parseMap(json, String.class, Object.class);
        //获取查询个数
        int count = bookInfoService.getSearchCount(searchParam);
        //查询数据
        List<BookInfo> bookInfos = bookInfoService.searchBookInfosByPage(page, limit, searchParam);
        //结果map
        return getStringObjectMap(count, bookInfos);
    }

    // 读者的查询数量
    @GetMapping(value = "/reader/getSearchCount")
    public Integer getSearchCountByReader(String bookname){
        // 字符串处理，去掉两边空格
        bookname = bookname.trim();
        if(bookname.equals("") || bookname.equals("null")) {
            return bookInfoService.getCount();
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("bookname", bookname);
            return bookInfoService.getSearchCount(map);
        }
    }

    // 读者的搜索图书
    @GetMapping("/reader/searchBookInfosByPage")
    public Map<String,Object> searchBookInfosByPageByReader(Integer page, Integer limit, String bookname){
        // 字符串处理，去掉两边空格
        bookname = bookname.trim();
        Map<String,Object> res;
        if (bookname.equals("") || bookname.equals("null")) {  //无参，就是获取全部
            res = queryBookInfosByPage(page, limit);
        } else {    // 有参，搜索图书
            String searchJson = "{\"bookname\":\"" + bookname + "\",\"bookauthor\":\"\",\"booktypeid\":\"\"}";
            res = searchBookInfosByPage(page, limit, searchJson);
        }
        return res;
    }

    // 读者的获取类型
    @GetMapping(value = "/reader/getCountByType")
    public Integer getCountByType(Integer booktypeid){
        if(booktypeid == null) return 0;
        Map<String, Object> map = new HashMap<>();
        map.put("booktypeid", booktypeid);
        return bookInfoService.getCountByType(map);
    }

    // 读者的通过分类查询图书信息
    @GetMapping("/reader/queryBookInfosByPageByType")
    public Map<String,Object> queryBookInfosByPageByType(Integer page, Integer limit, Integer booktypeid){
        if(booktypeid == null) return null;
        // 放参数
        Map<String, Object> map = new HashMap<>();
        map.put("booktypeid", booktypeid);
        //获取查询个数
        int count = bookInfoService.getCountByType(map);
        //查询数据
        List<BookInfo> bookInfos = bookInfoService.queryBookInfosByPageByType(page, limit, map);
        //结果map
        return getStringObjectMap(count, bookInfos);
    }
}
