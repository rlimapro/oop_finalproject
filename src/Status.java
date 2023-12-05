public enum Status {
    PENDENTE,
    COMPLETADO,
    CANCELADO;

    @Override
    public String toString() {
        return this.name();
    }
}
