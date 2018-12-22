package com.yeafel.evaluation.service.impl;

import com.yeafel.evaluation.dataobject.IndexRole;
import com.yeafel.evaluation.repository.IndexRoleRepository;
import com.yeafel.evaluation.service.IndexRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by kangyifan on 2018/10/15 15:14
 */
@Service
@Transactional
public class IndexRoleServiceImpl implements IndexRoleService {

    @Autowired
    private IndexRoleRepository indexRoleRepository;

    @Override
    public List<IndexRole> findIndexRolesByRoleId(Long roleId) {
        List<IndexRole> indexRoleList = indexRoleRepository.findIndexRolesByRoleId(roleId);
        return indexRoleList;
    }
}
