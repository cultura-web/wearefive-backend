package com.culturaweb.wearefive.unit;

import com.culturaweb.wearefive.dto.QADTO;
import com.culturaweb.wearefive.exceptions.QANoExisteException;
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
    IAdminRepository iAdminRepository;

    @InjectMocks
    private QAServiceImpl tested;

    @Test
    public void testAgregarConExitoQA()
    {
        //arrange
        QADTO input = new QADTO("esto es una pregunta","esto es una respuesta");

        String expected = "OK";

        Admin a = new Admin();

        when(iAdminRepository.getById(anyInt())).thenReturn(a);
        when(iqaRepository.save(any(QA.class))).thenReturn(null);

        //act
        String result = this.tested.agregarQA(input);
        //assert
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void testEditarQAQueNoExiste()
    {
        //arrange
        QADTO input = new QADTO("esto es una pregunta","esto es una respuesta");
        int idInput = 1;

        QA consulta = null;
        Optional o = Optional.ofNullable(consulta);

        when(iqaRepository.findById(anyInt())).thenReturn(o);

        //act - assert
        Assertions.assertThrows(QANoExisteException.class, () -> this.tested.editarQA(idInput,input));
    }

    @Test
    public void testEliminarQAQueNoExiste()
    {
        //arrange
        int idInput = 1;

        when(iqaRepository.existsById(anyInt())).thenReturn(false);

        //act - assert
        Assertions.assertThrows(QANoExisteException.class, () -> this.tested.eliminarQA(idInput));
    }
}
