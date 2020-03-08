package com.thundersdata.backend.basic.service.impl;

import com.thundersdata.backend.basic.dao.WaybillFilesMapper;
import com.thundersdata.backend.basic.model.WaybillFiles;
import com.thundersdata.backend.basic.service.WaybillFilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * @Classname WaybillFilesServiceImpl
 * @Description TODO
 * @Date 2019/12/7 9:17
 * @Created wrc
 */
@Service
public class WaybillFilesServiceImpl implements WaybillFilesService {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private WaybillFilesMapper waybillFilesMapper;


    /**
     * 插入上传文件数据，回显文件数据
     *
     * @param file
     * @return
     */
    @Override
    public WaybillFiles insertSelective(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        Assert.notNull(fileName, "上传文件不存在");
        // 上传
        byte[] bytes = new byte[0];
        String timeFileName= "";
        try {
            bytes = file.getBytes();
            timeFileName= System.currentTimeMillis()+ fileName;
            Path path = Paths.get(uploadPath + timeFileName);
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        WaybillFiles waybillFiles = new WaybillFiles();
        waybillFiles.setUrl("/images/" + timeFileName);
        String suffix = fileName.substring(fileName.indexOf(".") + 1, fileName.length());
        waybillFiles.setSuffix(suffix);
        ArrayList<String> phone = new ArrayList<String>() {
            {
                this.add("JPG");
                this.add("JPEG");
                this.add("PNG");
                this.add("GIF");
            }
        };

        waybillFiles.setType("1");
        for (String phones : phone) {
            if (phones.equalsIgnoreCase(suffix)) {
                waybillFiles.setType("0");
            }
        }

        waybillFilesMapper.insertSelective(waybillFiles);
        return waybillFiles;
    }
}
