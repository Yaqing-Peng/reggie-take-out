package com.jasmine.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jasmine.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmpMapper extends BaseMapper<Employee> {
}
