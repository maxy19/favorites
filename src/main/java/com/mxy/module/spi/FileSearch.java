package com.mxy.module.spi;

import com.mxy.module.spi.Search;
import org.springframework.core.annotation.Order;

import java.util.List;
@Order(1)
public class FileSearch implements Search {
    @Override
    public List<String> searchDoc(String keyword) {
        System.out.println("文件搜索 " + keyword);
        return null;
    }
}
