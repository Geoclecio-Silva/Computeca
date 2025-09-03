package com.computeca.bncc.util;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component("thymeleafUtil")
public class ThymeleafUtil {

    public String formatarNomeEnum(String nomeEnum) {
        if (nomeEnum == null || nomeEnum.isEmpty()) {
            return "";
        }
        String nomeFormatado = nomeEnum.replace("_", " ");
        nomeFormatado = nomeFormatado.substring(0, 1).toUpperCase() + nomeFormatado.substring(1).toLowerCase();
        return nomeFormatado;
    }

    public String formatarLista(List<String> lista) {
        if (lista == null || lista.isEmpty()) {
            return "Nenhuma habilidade associada";
        }
        return lista.stream().collect(Collectors.joining(", "));
    }
}