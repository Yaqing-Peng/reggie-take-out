package com.jasmine.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jasmine.common.R;
import com.jasmine.entity.Employee;
import com.jasmine.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
@RequestMapping("/employee")
public class EmpController {
    @Autowired
    private EmpService empService;

    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee){
        //encode pass into md5
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        //1.check if user exists
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername,employee.getUsername());
        Employee emp = empService.getOne(queryWrapper);

        if(emp == null){
            return R.error("User not exist...");
        }

        //2.check password
        if(!password.equals(emp.getPassword())){
            return R.error("Password is not matched...");
        }

        //3.check user status
        if(emp.getStatus() == 0){
            return R.error("User is banned from login");
        }

        //4.add to session, login success
        request.getSession().setAttribute("Employee", emp.getId());
        return R.success(emp);
    }

}
