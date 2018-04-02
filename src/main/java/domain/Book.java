package domain;

import common.annotation.XlsHeader;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
/**
 * @author wangzx
 */
public class Book {
    @XlsHeader("图书ID")
    private long bookId;
    @XlsHeader("图书名称")
    private String name;
    @XlsHeader("馆藏数量")
    private Integer number;
}
