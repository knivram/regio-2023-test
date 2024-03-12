package utils

enum class ExceptionCode(val message: String) {
    USER_EXISTS("Dieser User existiert bereits."),
    MAX_USER("Sie haben das Maximum von $MAX_USER erreicht."),
    GAME_ALREADY_STARTED("Das spiel hat bereits gestartet"),
}