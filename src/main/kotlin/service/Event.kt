package service

import androidx.compose.ui.graphics.Color
import repository.Entity
import ui.components.CellEntry
import java.util.*

data class Event(
    override val id: UUID,
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
) : Entity {
    constructor(
        round: Int,
        activityDeckCount: Int,
        electionTracker: Int,
        playedB: Int,
        playedR: Int,
        leader: User,
        assistant: User,
        voteAccepted: String,
        playedActivity: Char,
        leaderDraw: String,
        assistantDraw: String
    ) : this(
        UUID.randomUUID(),
        round,
        activityDeckCount,
        electionTracker,
        playedB,
        playedR,
        leader,
        assistant,
        voteAccepted,
        playedActivity,
        leaderDraw,
        assistantDraw
    )

    override fun insert() {

    }
}

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
