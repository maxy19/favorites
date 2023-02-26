package com.mxy.module.spi;

import com.mxy.module.spi.Search;
import org.springframework.core.annotation.Order;

import java.util.List;
@Order(2)
public class DatabaseSearch implements Search {
    @Override
    public List<String> searchDoc(String keyword) {
        System.out.println("数据搜索 " + keyword);
        return null;
    }
}