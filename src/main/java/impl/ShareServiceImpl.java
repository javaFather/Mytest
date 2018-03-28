package impl;

import com.google.common.collect.Maps;
import dao.ShareMapper;
import domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ShareService;

import java.util.List;
import java.util.Map;

/**
 * @author wangzx
 */
@Service
public class ShareServiceImpl implements ShareService {
    @Autowired
    private ShareMapper shareMapper;
@Override
    public List<Book> findShare(Map<String, Object> map) {
        return shareMapper.getOne(map) ;
    }
}
