package com.thundersdata.backend.basic.service;



import com.thundersdata.backend.basic.dto.SysLocationDTO;

import java.util.List;


/**
 * @author spf
 */
public interface SysLocationService {

    /**
     * 返回全国地市信息
     *
     * @return
     */
    List<SysLocationDTO> getSysLocation();

}
