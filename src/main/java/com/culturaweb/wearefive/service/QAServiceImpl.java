package com.culturaweb.wearefive.service;

import com.culturaweb.wearefive.dto.QADTO;
import com.culturaweb.wearefive.model.Admin;
import com.culturaweb.wearefive.model.QA;
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
    @Override
    public void editarQA(int Id,QADTO QAdto) {
        QA qa=this.iqaRepository.getById(Id);
        QA qanew = this.modelMapper.map(QAdto,QA.class);
        qa.setPregunta(qanew.getPregunta());
        qa.setRespuesta(qanew.getRespuesta());
        this.iqaRepository.save(qa);
    }
    @Override
    public void eliminarQA(int id) {

    }


//    @Override
//    public void eliminarQA(int id) {
//        Optional<QA> qa= this.iqaRepository.findById(id);
//        Optional<Admin> optional = this.adminRepository.findById(1);
//        this.iqaRepository.delete(qa);
//    }
}
