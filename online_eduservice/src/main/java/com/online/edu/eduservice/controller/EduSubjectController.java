package com.online.edu.eduservice.controller;


import com.online.edu.common.R;
import com.online.edu.eduservice.entity.EduSubject;
import com.online.edu.eduservice.entity.dto.OneSubjectDto;
import com.online.edu.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author Brave
 * @since 2020-02-04
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    private EduSubjectService eduSubjectService;

    /**
     * 通过上次excel文件获取文件内容
     * @param file
     * @return
     */
    @PostMapping("import")
    public R importExcelSubject(@RequestParam("file") MultipartFile file){
        List<String> msg = eduSubjectService.importSubject(file);
        if (msg.size() == 0){
            return R.ok();
        }else {
            return R.error().message("部分数据导入失败!").data("msgList",msg);
        }
    }

    /**
     * 返回所有分类数据，返回要求的json数据格式
     * @return
     */
    @GetMapping
    public R getAllSubjectList(){
        List<OneSubjectDto> list = eduSubjectService.getSubjectList();
        return R.ok().data("OneSubjectDto",list);
    }

    /**
     * 删除分类
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public R deleteSubjectById(@PathVariable String id){
        boolean flag = eduSubjectService.deleteSubjectById(id);
        if(flag){
            return R.ok();
        }else {
            return R.error();
        }
    }

    /**
     * 添加一级分类
     * @param eduSubject
     * @return
     */
    @PostMapping("addOneLevel")
    public R addOneLevel(@RequestBody EduSubject eduSubject){
        boolean flag = eduSubjectService.saveOneLevel(eduSubject);
        if (flag) {
            return R.ok();
        }else {
            return R.error();
        }
    }

    /**
     * 添加二级分类
     * @param eduSubject
     * @return
     */
    @PostMapping("addTwoLevel")
    public R addTwoLevel(@RequestBody EduSubject eduSubject){
        boolean flag = eduSubjectService.saveTwoLevel(eduSubject);
        if (flag) {
            return R.ok();
        }else {
            return R.error();
        }
    }


}

