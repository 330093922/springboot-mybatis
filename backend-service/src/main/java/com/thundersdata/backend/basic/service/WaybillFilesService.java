package com.thundersdata.backend.basic.service;

import com.thundersdata.backend.basic.model.WaybillFiles;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Classname WaybillFilesService
 * @Description TODO
 * @Date 2019/12/7 9:16
 * @Created wrc
 */
public interface WaybillFilesService {


    /**
     * 插入上传文件数据，回显数据
     * @param file
     * @return
     */
    WaybillFiles insertSelective(MultipartFile file);


}
