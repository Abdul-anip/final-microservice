package com.hanif.anggota.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hanif.anggota.model.Anggota;
import com.hanif.anggota.repository.AnggotaRepository;

@Service
public class AnggotaService {

    private static final Logger logger = LoggerFactory.getLogger(AnggotaService.class);

    @Autowired
    private AnggotaRepository anggotaRepository;

    public List<Anggota> getAllAnggotas() {
        return anggotaRepository.findAll();
    }

    public Anggota getAnggotaById(Long id) {
        return anggotaRepository.findById(id).orElse(null);
    }

    public Anggota createAnggota(Anggota anggota) {
        Anggota savedAnggota = anggotaRepository.save(anggota);
        logger.info("Anggota berhasil dubuat INI DIDEIT: {}", savedAnggota);
        return savedAnggota;
    }

    public void deleteAnggota(Long id) {
        anggotaRepository.deleteById(id);
    }
}
