package common.Redis;

import java.util.List;

/**
 * @author: wangzx
 * @Date: 2018-03-26-16:23
 */
public interface Redis {
    /*** 表头单个插入
     * @param key
     * @param value
     * @return
     */
    Long addHead(String key,String value);

    /**
     * 表头多个插入
     * @param key
     * @param arry
     * @return
     */
    Long addHeads(String key,String[] arry);

    /**
     * 返回list中所有元素
     * @param desList
     * @return
     */
    List<String> list(String desList);

    /**
     * 初始化list
     * @param listName list名称
     * @param str
     * @return
     */
    Long createList (String listName,String ...str);

    /**
     * 根据index获取元素
     * @param listName
     * @param index
     * @return
     */
    String getOne(String listName,long index);
}
