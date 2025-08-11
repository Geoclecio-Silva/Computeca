package com.computeca.bncc.util;

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
}