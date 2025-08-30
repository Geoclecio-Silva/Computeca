package com.computeca.bncc.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

@Service
public class ServicoImagem {

    private final Cloudinary cloudinary;

    public ServicoImagem(
        @Value("${cloudinary.cloud-name}") String cloudName,
        @Value("${cloudinary.api-key}") String apiKey,
        @Value("${cloudinary.api-secret}") String apiSecret) {
        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", cloudName,
            "api_key", apiKey,
            "api_secret", apiSecret));
    }

    public String salvarImagem(MultipartFile arquivo) throws IOException {
        if (arquivo == null || arquivo.isEmpty()) {
            return null;
        }

        // Redimensiona a imagem antes de fazer o upload
        byte[] imagemRedimensionada = redimensionarImagem(arquivo.getBytes());

        // Faz o upload da imagem redimensionada para o Cloudinary
        Map uploadResult = cloudinary.uploader().upload(imagemRedimensionada, ObjectUtils.emptyMap());

        // Retorna a URL segura da imagem
        return (String) uploadResult.get("secure_url");
    }

    private byte[] redimensionarImagem(byte[] imagemOriginal) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        // Configura o redimensionamento para uma largura máxima de 800px e mantem a proporção
        Thumbnails.of(new ByteArrayInputStream(imagemOriginal))
            .width(800)
            .toOutputStream(outputStream);

        return outputStream.toByteArray();
    }
}