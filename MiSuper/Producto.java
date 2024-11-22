package MiSuper;

public enum Producto {
    MANZANA("Manzana"),
    BANANA("Banana"),
    NARANJA("Naranja"),
    FRESA("Fresa"),
    UVA("Uva"),
    PERA("Pera"),
    MELON("Melón"),
    SANDIA("Sandía"),
    LIMON("Limón"),
    KIWI("Kiwi"),
    MANGO("Mango"),
    PAPAYA("Papaya"),
    PIÑA("Piña"),
    FRAMBUESA("Frambuesa"),
    ARANDANO("Arándano"),
    ACEITUNA("Aceituna"),
    TOMATE("Tomate"),
    PIMIENTO("Pimiento"),
    CEBOLLA("Cebolla"),
    AJO("Ajo"),
    LECHUGA("Lechuga"),
    ESPINACA("Espinaca"),
    BRÓCOLI("Brócoli"),
    ZANAHORIA("Zanahoria"),
    PATATA("Patata"),
    BATATA("Batata"),
    REPOLLO("Repollo"),
    COLIFLOR("Coliflor"),
    GENGIBRE("Jengibre"),
    PEREJIL("Perejil"),
    ALBAHACA("Albahaca"),
    PIMIENTA("Pimienta"),
    SAL("Sal"),
    AZUCAR("Azúcar"),
    HARINA("Harina"),
    ARROZ("Arroz"),
    LENTEJAS("Lentejas"),
    GARBANZOS("Garbanzos"),
    PASTA("Pasta"),
    ACEITE("Aceite"),
    VINAGRE("Vinagre"),
    MIEL("Miel"),
    CAFÉ("Café"),
    TE("Té"),
    JUGO("Jugo"),
    BEBIDA("Bebida"),
    AGUA("Agua");

    private final String nombre;

    Producto(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}

