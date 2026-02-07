package com.batrits.service;

import com.batrits.pojo.Clazz;
import com.batrits.pojo.ClazzQueryParam;
import com.batrits.pojo.PageResult;

import java.util.List;

public interface ClazzService {

    /**
     * 查询所有班级信息
     * @return
     */
    PageResult<Clazz> page(ClazzQueryParam params);

    void save(Clazz clazz);

    Clazz getClazzById(Integer id);

    void update(Clazz clazz);

    void deleteById(Integer id);

    List<Clazz> list();
}
