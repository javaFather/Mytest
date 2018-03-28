package service;

import domain.Book;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author wangzx
 */
public interface ShareService {
    /**
     * 查询单个信息
     * @author wangzx
     * @param map
     * @return
     */
    List<Book> findShare(Map<String,Object> map);

}
