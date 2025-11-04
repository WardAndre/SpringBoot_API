package br.com.coletaresiduos.coleta.model;

public enum Material {
    PAPEL("papel"),
    PLASTICO("plástico"),
    VIDRO("vidro"), METAL("metal"),
    ELETRONICO("eletrônico"),
    ORGANICO("orgânico");

    private String material;

    Material(String material) {
        this.material = material;
    }

    public String getMaterial() {
        return material;
    }
}
