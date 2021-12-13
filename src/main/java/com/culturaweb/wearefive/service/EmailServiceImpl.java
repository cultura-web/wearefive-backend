package com.culturaweb.wearefive.service;

import com.culturaweb.wearefive.dto.DetalleCompraDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl {

    @Autowired
    private JavaMailSender emailSender;

    private final String WEAREFIVEEMAIL = "juanesmoncada123@gmail.com";

    public void enviarEmailDeCompra(String to, String detalleCompra) {
        String mensajeCompra = "" +
                "Compra realizada! \n" +
                "Los datos de la compra son:" + "\n" +
                detalleCompra + "\n"+
                "Ponte en contacto al numero xxxxxxxxxxx o al correo ejemplo@correo.com para terminar de consolidar la compra" + "\n";
        enviarEmail(to, "We are Five - compra realizada", mensajeCompra);
    }

    public void enviarEmailDeVenta(String facturaInfo) {
        String mensajeVenta = "\n" +
                "Venta realizada! \n" +
                "Los datos de la factura son:" + "\n" +
                facturaInfo;
        enviarEmail(WEAREFIVEEMAIL,"We are Five - venta realizada", mensajeVenta);
    }

    private void enviarEmail(String to, String subject, String text) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@wearefive.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }
}
