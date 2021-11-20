package com.culturaweb.wearefive.service;

import com.culturaweb.wearefive.dto.QADTO;
import com.culturaweb.wearefive.dto.QAsDTO;
import com.culturaweb.wearefive.model.QA;

import java.util.List;

public interface IQAService {

    public QAsDTO listarQA();
    public void agregarQA(QADTO QAdto);

    public void editarQA(int id, QADTO QAdto);

    public void eliminarQA(int id);
}
