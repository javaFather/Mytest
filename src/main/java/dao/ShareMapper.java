package dao;

import domain.Book;

import java.util.List;
import java.util.Map;

/**
 * @author: wangzx
 * @Date: 2018-01-29-10:37
 */
public interface ShareMapper {
    /**
     * 查询一个
     * @param map
     * @return
     */
    List<Book> getOne(Map<String,Object> map);
}
