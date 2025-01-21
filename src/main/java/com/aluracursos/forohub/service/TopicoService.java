package com.aluracursos.forohub.service;

import com.aluracursos.forohub.model.Topico;
import com.aluracursos.forohub.repository.TopicoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Transactional
    public Topico registrarTopico(Topico topico) {
        boolean existe = topicoRepository.existsByTituloAndMensaje(topico.getTitulo(), topico.getMensaje());
        if (existe) {
            throw new IllegalArgumentException("Ya existe un tópico con el mismo título y mensaje.");
        }

        topico.setFechaCreacion(LocalDateTime.now());

        return topicoRepository.save(topico);
    }
}

