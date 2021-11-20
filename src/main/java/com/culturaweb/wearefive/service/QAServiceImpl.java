package com.culturaweb.wearefive.service;

import com.culturaweb.wearefive.dto.QADTO;
import com.culturaweb.wearefive.dto.QAsDTO;
import com.culturaweb.wearefive.model.Admin;
import com.culturaweb.wearefive.model.QA;
import com.culturaweb.wearefive.repository.IAdminRepository;
import com.culturaweb.wearefive.repository.IQARepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QAServiceImpl implements IQAService {

    @Autowired
    IQARepository iqaRepository;
    @Autowired
    IAdminRepository adminRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public QAsDTO listarQA() {
        List<QA> qa = this.iqaRepository.findAll();
        List<QADTO> qadto = new ArrayList<>();
        for (QA Q : qa) {
            QADTO qadto1 = this.modelMapper.map(Q, QADTO.class);
            qadto.add(qadto1);
        }

        QAsDTO qas = new QAsDTO(qadto);
        return qas;
    }

    @Override
    public void agregarQA(QADTO QAdto) {
        QA qa = this.modelMapper.map(QAdto, QA.class);
        if (qa.getPregunta().isEmpty()||qa.getRespuesta().isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Actor Not Found");
        Optional<Admin> optional = this.adminRepository.findById(1);
        Admin a = optional.get();
        qa.setAdmin(a);
        this.iqaRepository.save(qa);
    }

    @Override
    public void editarQA(int Id, QADTO QAdto) {
        QA qa = this.iqaRepository.getById(Id);
        if (!qa.equals(QAdto))
        {
        if (!qa.getPregunta().isEmpty())
        qa.setPregunta(QAdto.getPregunta());
        if (!qa.getRespuesta().isEmpty())
        qa.setRespuesta(QAdto.getRespuesta());
        this.iqaRepository.save(qa);
        }
    }


    @Override
    public void eliminarQA(int id) {
        Optional<QA> optional = this.iqaRepository.findById(id);
        QA qa = optional.get();
        this.iqaRepository.delete(qa);
    }

}
