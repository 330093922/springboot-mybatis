package com.thundersdata.backend.basic.service.impl;

import com.thundersdata.backend.basic.dao.WaybillCameraMapper;
import com.thundersdata.backend.basic.dto.WaybillCameraDTO;
import com.thundersdata.backend.basic.model.WaybillCamera;
import com.thundersdata.backend.basic.service.CameraService;
import com.thundersdata.backend.basic.utils.OrderUtil;
import com.thundersdata.backend.basic.vo.WaybillCameraVO;
import com.thundersdata.backend.common.utils.PageParam;
import com.thundersdata.backend.common.utils.PageUtils;
import com.thundersdata.backend.common.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class CameraServiceImpl implements CameraService {

    @Autowired
    private WaybillCameraMapper waybillCameraMapper;

    /**
     * 列表查询带分页
     * @param queryVO 查询条件
     * @return
     */
    @Override
    public Pagination<WaybillCameraDTO> getListByCondition(WaybillCameraVO queryVO,
                                                           Integer[] ownerIds,
                                                           Boolean isScope) {

        String equipmentNo = queryVO.getEquipmentNo();
        String name = queryVO.getName();
        String installArea = queryVO.getInstallArea();

        Integer page = queryVO.getPage();
        Integer pageSize = queryVO.getPageSize();

        // 校验分页参数，赋默认值
        PageParam pageparam = PageUtils.init(page, pageSize);

        int total = waybillCameraMapper.getCountByCondition(OrderUtil.getLikeStr(equipmentNo),OrderUtil.getLikeStr(name),OrderUtil.getLikeStr(installArea),ownerIds,isScope);
        if (total == 0) {
            return Pagination.nullPage(pageparam.getPage(), pageparam.getPageSize());
        }

        List<WaybillCameraDTO> list = waybillCameraMapper.getListByCondition(OrderUtil.getLikeStr(equipmentNo),OrderUtil.getLikeStr(name),OrderUtil.getLikeStr(installArea),pageparam.getLimit(), pageparam.getOffset(),ownerIds,isScope);

        return Pagination.newInstance(pageparam.getPage(), pageparam.getPageSize(), total, list);
    }

    /**
     * 列表查询无分页
     * @param queryVO 查询条件
     * @return
     */
    @Override
    public List<WaybillCameraDTO> getListWithoutPagination(WaybillCameraVO queryVO,
                                                           Integer[] ownerIds,
                                                           Boolean isScope) {
        String equipmentNo = queryVO.getEquipmentNo();
        String name = queryVO.getName();
        String installArea = queryVO.getInstallArea();

        Integer page = null;
        Integer pageSize = null;

        return waybillCameraMapper.getListByCondition(OrderUtil.getLikeStr(equipmentNo),OrderUtil.getLikeStr(name),OrderUtil.getLikeStr(installArea),page,pageSize,ownerIds,isScope);
    }

    /**
     * 详情查询
     * @param id 摄像头id
     * @return
     */
    @Override
    public WaybillCameraDTO getDetailsByCondition(Integer id) {
        Assert.notNull(id,"摄像头id不能为空");
        return waybillCameraMapper.getDetailsByCondition(id);
    }

    /**
     * 下拉框
     * @param name
     * @return
     */
    @Override
    public List<WaybillCameraDTO> dropList(String name) {
        return waybillCameraMapper.dropList(name);
    }

    /**
     * 修改
     * @param waybillCamera
     * @return
     */
    @Override
    public int updateCamera(WaybillCamera waybillCamera) {
        return waybillCameraMapper.updateByPrimaryKeySelective(waybillCamera);
    }


}
