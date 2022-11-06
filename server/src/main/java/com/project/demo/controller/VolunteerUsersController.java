package com.project.demo.controller;

import com.project.demo.entity.VolunteerUsers;
import com.project.demo.service.VolunteerUsersService;
import com.project.demo.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *志愿者用户：(VolunteerUsers)表控制层
 *
 */
@RestController
@RequestMapping("/volunteer_users")
public class VolunteerUsersController extends BaseController<VolunteerUsers,VolunteerUsersService> {

    /**
     *志愿者用户对象
     */
    @Autowired
    public VolunteerUsersController(VolunteerUsersService service) {
        setService(service);
    }

    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        this.addMap(paramMap);
        return success(1);
    }

}
