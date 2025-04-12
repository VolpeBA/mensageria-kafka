package com.volpe.produtor.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.volpe.produtor.dto.MensagemDTO;
import com.volpe.produtor.service.ProdutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/kafka")
@RequiredArgsConstructor
public class EnvioController {

    private final ProdutorService produtorService;

    @PostMapping
    public void sendMessage(@RequestBody final MensagemDTO mensagemDTO) throws JsonProcessingException {
        produtorService.sendMessageToTopic(mensagemDTO, Optional.of(mensagemDTO.getTipoMensagem().ordinal())
                .orElseThrow(() -> new IllegalArgumentException("Tipo de mensagem não pode ser nulo")));
    }
}
