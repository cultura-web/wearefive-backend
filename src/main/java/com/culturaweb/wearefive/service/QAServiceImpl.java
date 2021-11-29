package com.culturaweb.wearefive.service;

import com.culturaweb.wearefive.dto.QADTO;
import com.culturaweb.wearefive.dto.QAsDTO;
import com.culturaweb.wearefive.exceptions.QANoExisteException;
import com.culturaweb.wearefive.model.Admin;
import com.culturaweb.wearefive.model.QA;
import com.culturaweb.wearefive.repository.IAdminRepository;
import com.culturaweb.wearefive.repository.IQARepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QAServiceImpl implements IQAService {

    @Autowired
    IQARepository iqaRepository;
    @Autowired
    IAdminRepository adminRepository;

    @Override
    public QAsDTO listarQA() {
        List<QA> qa = this.iqaRepository.findAll();
        List<QADTO> qadto = new ArrayList<>();
        for (QA Q : qa)
            qadto.add(new QADTO(Q.getPregunta(),Q.getRespuesta()));
        return new QAsDTO(qadto);
    }

    @Override
    public String agregarQA(QADTO qadto) {
        Admin a = this.adminRepository.getById(1);
        this.iqaRepository.save(new QA(qadto.getPregunta(),qadto.getRespuesta(),a));
        return "OK";
    }

    @Override
    public void editarQA(int id, QADTO qadto) {
        Optional<QA> optional = this.iqaRepository.findById(id);
        if(optional.isEmpty())
            throw new QANoExisteException();
        QA qa = optional.get();
        qa.setPregunta(qadto.getPregunta());
        qa.setRespuesta(qadto.getRespuesta());
        this.iqaRepository.save(qa);
    }

    @Override
    public void eliminarQA(int id) {
        if(!this.iqaRepository.existsById(id))
            throw new QANoExisteException();
        this.iqaRepository.deleteById(id);
    }
}
