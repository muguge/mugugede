package com.project.demo.controller;

import com.project.demo.entity.CharityFundraising;
import com.project.demo.service.CharityFundraisingService;
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
 *爱心募捐：(CharityFundraising)表控制层
 *
 */
@RestController
@RequestMapping("/charity_fundraising")
public class CharityFundraisingController extends BaseController<CharityFundraising,CharityFundraisingService> {

    /**
     *爱心募捐对象
     */
    @Autowired
    public CharityFundraisingController(CharityFundraisingService service) {
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
