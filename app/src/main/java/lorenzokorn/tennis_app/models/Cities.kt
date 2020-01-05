package lorenzokorn.tennis_app.models

enum class Cities(private val city: String, val id: Long) {
    NONE("Select city", -11),
    AMSTERDAM("Amsterdam", 2759794),
    ROTTERDAM("Rotterdam", 2747891),
    ZWOLLE("Zwolle", 2743477),
    DEN_HAAG("Den Haag", 2747372),
    GRONINGEN("Groningen", 2755251),
    LELYSTAD("Lelystad", 2751738),
    HAARLEM("Haarlem", 2755003),
    HOORN("Hoorn", 2753638),
    UTRECHT("Utrecht", 2745912);

    override fun toString(): String {
        return city
    }
}