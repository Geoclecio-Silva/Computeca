package com.computeca.bncc.model;

public enum StatusAtividade {
    
    PENDENTE("Pendente de revisão", "Aguardando aprovação do curador"),
    APROVADA("Aprovada e publicada", "Atividade disponível para todos"),
    REJEITADA("Rejeitada", "Atividade não aprovada, verificar motivo"),
    RASCUNHO("Rascunho", "Atividade em edição, não publicada"),
    ARQUIVADA("Arquivada", "Atividade removida do catálogo");
    
    private final String descricao;
    private final String detalhe;
    
    StatusAtividade(String descricao, String detalhe) {
        this.descricao = descricao;
        this.detalhe = detalhe;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public String getDetalhe() {
        return detalhe;
    }
    
    public String getBadgeClass() {
        switch (this) {
            case APROVADA:
                return "badge bg-success";
            case PENDENTE:
                return "badge bg-warning text-dark";
            case REJEITADA:
                return "badge bg-danger";
            case RASCUNHO:
                return "badge bg-secondary";
            case ARQUIVADA:
                return "badge bg-dark";
            default:
                return "badge bg-light text-dark";
        }
    }
    
    public String getIcon() {
        switch (this) {
            case APROVADA:
                return "✓";
            case PENDENTE:
                return "⏳";
            case REJEITADA:
                return "✗";
            case RASCUNHO:
                return "✎";
            case ARQUIVADA:
                return "📁";
            default:
                return "";
        }
    }
    
    public boolean isEditavel() {
        return this == PENDENTE || this == RASCUNHO;
    }
    
    public boolean isPublicavel() {
        return this == APROVADA;
    }
    
    public static StatusAtividade fromString(String texto) {
        for (StatusAtividade status : StatusAtividade.values()) {
            if (status.name().equalsIgnoreCase(texto)) {
                return status;
            }
        }
        return null;
    }
}