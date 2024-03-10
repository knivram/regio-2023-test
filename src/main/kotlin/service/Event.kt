package service

import androidx.compose.ui.graphics.Color
import ui.components.CellEntry

data class Event(
    val round: Int,
    val activityDeckCount: Int,
    val electionTracker: Int,
    val playedB: Int,
    val playedR: Int,
    val leader: User,
    val assistant: User,
    val voteAccepted: String,
    val playedActivity: Char,
    val leaderDraw: String,
    val assistantDraw: String
)

fun Event.toCellEntryList(): List<CellEntry> {
    return listOf(
        CellEntry(round),
        CellEntry(activityDeckCount),
        CellEntry(electionTracker),
        CellEntry(playedB),
        CellEntry(playedR),
        CellEntry(leader.name, leader.color),
        CellEntry(assistant.name, assistant.color),
        CellEntry(voteAccepted),
        CellEntry(playedActivity.toString(), if (playedActivity == 'R') Color.Red else Color.Blue),
        CellEntry(leaderDraw),
        CellEntry(assistantDraw),
    )
}
