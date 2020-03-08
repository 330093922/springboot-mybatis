package com.thundersdata.backend.basic.service;

import com.thundersdata.backend.basic.dto.WaybillCameraDTO;
import com.thundersdata.backend.basic.dto.WaybillVehicleDTO;
import com.thundersdata.backend.basic.model.WaybillCamera;
import com.thundersdata.backend.basic.model.WaybillVehicle;
import com.thundersdata.backend.basic.vo.WaybillCameraVO;
import com.thundersdata.backend.basic.vo.WaybillVehicleVO;
import com.thundersdata.backend.common.utils.Pagination;

import java.util.List;

public interface CameraService {

    /**
     * 查询摄像头列表
     *
     * @param queryVO 查询条件
     * @return 列表
     */
    Pagination<WaybillCameraDTO> getListByCondition(WaybillCameraVO queryVO,
                                                    Integer[] ownerIds,
                                                    Boolean isScope);

    /**
     * 列表查询无分页
     * @param queryVO 查询条件
     * @return
     */
    public List<WaybillCameraDTO> getListWithoutPagination(WaybillCameraVO queryVO,
                                                           Integer[] ownerIds,
                                                           Boolean isScope);

    /**
     * 查询摄像头详情
     *
     * @param id 摄像头id
     * @return 列表
     */
    WaybillCameraDTO getDetailsByCondition(Integer id);


    List<WaybillCameraDTO> dropList(String name);

    int updateCamera(WaybillCamera waybillCamera);
}
