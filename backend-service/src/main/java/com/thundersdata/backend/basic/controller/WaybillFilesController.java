package com.thundersdata.backend.basic.controller;

import com.thundersdata.backend.basic.model.WaybillFiles;
import com.thundersdata.backend.basic.service.impl.WaybillFilesServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Classname WaybillFilesController
 * @Description TODO
 * @Date 2019/12/7 9:11
 * @Created wrc
 */
@Api(tags = "文件上传接口")
@RestController
@RequestMapping("file")
public class WaybillFilesController {

    @Autowired
    private WaybillFilesServiceImpl waybillFilesServiceImpl;
    /**
     * 插入上传文件数据，回显文件数据
     * @param file
     * @return
     */
    @ApiOperation(value = "文件上传", notes = "文件")
    @PostMapping("upload")
    @ResponseBody
    public WaybillFiles insertSelective(@RequestParam("file") MultipartFile file){
        return waybillFilesServiceImpl.insertSelective(file);
    }


}
