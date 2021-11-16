package com.culturaweb.wearefive.service;

import com.culturaweb.wearefive.model.dto.QADTO;
import com.culturaweb.wearefive.model.domain.Admin;
import com.culturaweb.wearefive.model.domain.QA;
import com.culturaweb.wearefive.repository.IAdminRepository;
import com.culturaweb.wearefive.repository.IQARepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QAServiceImpl implements IQAService{

    @Autowired
    IQARepository iqaRepository;
    @Autowired
    IAdminRepository adminRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public void agregarQA(QADTO QAdto) {
        QA qa = this.modelMapper.map(QAdto,QA.class);
        Optional<Admin> optional = this.adminRepository.findById(1);
        Admin a = optional.get();
        qa.setAdmin(a);
        this.iqaRepository.save(qa);
    }
}
