public class UnitHolder {

    private Unit unit;

    public Unit getUnit() {
        return unit;
    }

    @DataBoundSetter
    public void setUnit(Unit unit) {
        this.unit = unit
    }

    public enum Unit {
        MILLISECONDS("Milliseconds"),
        SECONDS("Seconds"),
        MINUTES("Minutes"),
        HOURS("Hours"),
        DAYS("Days");

        private final String description;

        Unit(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }
}
