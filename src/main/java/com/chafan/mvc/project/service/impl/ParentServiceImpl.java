package com.chafan.mvc.project.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chafan.mvc.project.entity.Parent;
import com.chafan.mvc.project.mapper.ParentMapper;
import com.chafan.mvc.project.service.IParentService;

import org.springframework.stereotype.Service;

import java.util.List;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Chafan
 * @since 2022-05-15
 */
@Service
public class ParentServiceImpl extends ServiceImpl<ParentMapper, Parent> implements IParentService {

    /**
     * 获取所有用户
     * @return
     */
    @Override
    public List getParentList() {
        QueryWrapper query = new QueryWrapper();
        query.select("studentName","departmentId","parentDeptId","personNo");
        return baseMapper.selectList(query);
    }

    /**
     * 添加用户
     * @param studentName
     * @param personNo
     * @return
     */
    @Override
    public int AddParent(String studentName, String personNo){
        Parent  parent = new Parent();
        parent.setStudentName(studentName);
        parent.setPersonNo(personNo);
        parent.setParentDeptId(Integer.parseInt(personNo.substring(0,4)));
        parent.setDepartmentId(Integer.parseInt(personNo.substring(0,6)));
        return baseMapper.insert(parent);
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @Override
    public int deleteParent(String id) {
        return baseMapper.deleteById(id);
    }


}
