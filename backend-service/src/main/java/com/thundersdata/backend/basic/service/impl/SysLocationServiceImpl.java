package com.thundersdata.backend.basic.service.impl;

import com.thundersdata.backend.basic.dao.SysLocationMapper;
import com.thundersdata.backend.basic.dto.SysLocationDTO;
import com.thundersdata.backend.basic.service.SysLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class SysLocationServiceImpl implements SysLocationService {


    @Autowired
    SysLocationMapper sysLocationMapper;

    @Override
    public List<SysLocationDTO> getSysLocation() {

        List<SysLocationDTO> list = sysLocationMapper.getCityList();

        List<SysLocationDTO> treeList = new ArrayList<>();

        for (SysLocationDTO sysLocationDTO:list) {
            if(sysLocationDTO.getParentCode().equals("0")) {
                convert2Tree(list, sysLocationDTO);
                treeList.add(sysLocationDTO);
            }

        }
        return  treeList ;
    }

    /**
     *
     *
     * @param list
     * @param parentNode
     * @return
     */
    public void convert2Tree(List<SysLocationDTO> list, SysLocationDTO parentNode) {
        parentNode.setChildren(new LinkedList<>());

        for (SysLocationDTO sysLocationDTO : list) {
            if (sysLocationDTO.getParentCode().equals(parentNode.getLocationCode())) {
                parentNode.getChildren().add(sysLocationDTO);

                convert2Tree(list, sysLocationDTO);
            }
        }

    }
}
