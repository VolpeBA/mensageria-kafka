package com.volpe.produtor.dto;

import lombok.Data;

@Data
public class MensagemDTO {

    private String nome;

    private String destinatario;

    private String assunto;

    private TipoMensagem tipoMensagem;

}
