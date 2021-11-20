package com.culturaweb.wearefive.unit;

import com.culturaweb.wearefive.dto.QADTO;
import com.culturaweb.wearefive.model.Admin;
import com.culturaweb.wearefive.model.QA;
import com.culturaweb.wearefive.repository.IAdminRepository;
import com.culturaweb.wearefive.repository.IQARepository;
import com.culturaweb.wearefive.service.IQAService;
import com.culturaweb.wearefive.service.QAServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UTQAService {

    @Mock
    IQARepository iqaRepository;

    @Mock
    ModelMapper modelMapper;

    @Mock
    IAdminRepository iAdminRepository;

    @InjectMocks
    private QAServiceImpl tested;

    @Test
    public void testAgregarConExitoQA()
    {
        //arrange
        QADTO input = new QADTO("esto es una pregunta","esto es una respuesta");
        QA r = new QA();
        r.setPregunta("esto es una pregunta");
        r.setRespuesta("esto es una respuesta");

        Admin a = new Admin();
        Optional<Admin> oa = Optional.of(a);

        String expected = "OK";


        when(iqaRepository.save(any(QA.class))).thenReturn(null);
        when(modelMapper.map(any(),any())).thenReturn(r);
        when(iAdminRepository.findById(anyInt())).thenReturn(oa);


        //act
        String result = this.tested.agregarQA(input);
        //assert
        Assertions.assertEquals(expected,result);
    }
}
