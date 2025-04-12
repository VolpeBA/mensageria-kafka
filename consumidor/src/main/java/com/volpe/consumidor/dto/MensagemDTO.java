package com.volpe.consumidor.dto;

import lombok.Data;

@Data
public class MensagemDTO {

    private String nome;

    private String destinatario;

    private String assunto;

    private TipoMensagem tipoMensagem;

}
