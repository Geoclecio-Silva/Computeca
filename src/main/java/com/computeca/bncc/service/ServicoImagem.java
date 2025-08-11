package com.computeca.bncc.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ServicoImagem {

    private static final String UPLOAD_DIR = "src/main/resources/static/imagens/";

    public String salvarImagem(MultipartFile arquivo) throws IOException {
        if (arquivo == null || arquivo.isEmpty()) {
            return null;
        }

        // Garante que o diretório de upload exista
        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String nomeOriginal = arquivo.getOriginalFilename();

        if (StringUtils.hasText(nomeOriginal)) {
            // Cria um nome de arquivo único para evitar conflitos
            String extensao = "";
            int lastDotIndex = nomeOriginal.lastIndexOf(".");
            if (lastDotIndex != -1) {
                extensao = nomeOriginal.substring(lastDotIndex);
            }

            String nomeArquivo = UUID.randomUUID().toString() + extensao;

            Path filePath = uploadPath.resolve(nomeArquivo);
            Files.copy(arquivo.getInputStream(), filePath);

            return "/imagens/" + nomeArquivo;
        }

        return null;
    }
}