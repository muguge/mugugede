package com.project.demo.controller;

import com.project.demo.entity.StrayAnimalNews;
import com.project.demo.service.StrayAnimalNewsService;
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
 *流浪动物消息：(StrayAnimalNews)表控制层
 *
 */
@RestController
@RequestMapping("/stray_animal_news")
public class StrayAnimalNewsController extends BaseController<StrayAnimalNews,StrayAnimalNewsService> {

    /**
     *流浪动物消息对象
     */
    @Autowired
    public StrayAnimalNewsController(StrayAnimalNewsService service) {
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
