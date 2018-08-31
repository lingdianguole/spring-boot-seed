package com.company.project.service.impl;

import com.company.project.dao.PhoneMapper;
import com.company.project.model.Phone;
import com.company.project.service.PhoneService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2018/08/31.
 */
@Service
@Transactional
public class PhoneServiceImpl extends AbstractService<Phone> implements PhoneService {
    @Resource
    private PhoneMapper phoneMapper;

}
