package com.jasmine.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jasmine.entity.Employee;
import com.jasmine.mapper.EmpMapper;
import com.jasmine.service.EmpService;
import org.springframework.stereotype.Service;

@Service
public class EmpServiceImpl extends ServiceImpl<EmpMapper, Employee> implements EmpService {
}
