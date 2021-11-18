package com.culturaweb.wearefive.service;

import com.culturaweb.wearefive.dto.QADTO;

public interface IQAService {
    public void agregarQA(QADTO QAdto);
    public void editarQA(int id,QADTO QAdto);
    public void eliminarQA(int id);
}
