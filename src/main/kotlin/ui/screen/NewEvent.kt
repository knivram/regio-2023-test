package ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import service.Event
import service.GameService
import service.User

data class NewEvent(
    val gameService: GameService
) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        // You would replace these with your state management logic
        var round by remember { mutableStateOf(0) }
        var electionTracker by remember { mutableStateOf(0) }
        var blueCardsPlayed by remember { mutableStateOf(0) }
        var redCardsPlayed by remember { mutableStateOf(0) }
        var leader by remember { mutableStateOf("") }
        var assistant by remember { mutableStateOf("") }
        var voteAccepted by remember { mutableStateOf(false) }
        var blueIsPlayedActive by remember { mutableStateOf(false) }
        var leaderDraw by remember { mutableStateOf("") }
        var assistantDraw by remember { mutableStateOf("") }

        Column(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Row {
                NumberInputField(value = round, onValueChange = { round = it }, label = "Round")
                NumberInputField(
                    value = electionTracker,
                    onValueChange = { electionTracker = it },
                    label = "Election Tracker"
                )
            }
            Row {
                NumberInputField(
                    value = blueCardsPlayed,
                    onValueChange = { blueCardsPlayed = it },
                    label = "# Blue cards Played"
                )
                NumberInputField(
                    value = redCardsPlayed,
                    onValueChange = { redCardsPlayed = it },
                    label = "# Red cards Played"
                )
            }
            Row {
                OutlinedTextField(
                    value = leader,
                    onValueChange = { leader = it },
                    label = { Text("Leader") },
                    singleLine = true
                )
                OutlinedTextField(
                    value = assistant,
                    onValueChange = { assistant = it },
                    label = { Text("Assistant") },
                    singleLine = true
                )
            }
            Row {
                ToggleSwitch(
                    checked = voteAccepted,
                    onCheckedChange = { voteAccepted = it },
                    label = "Vote Accepted"
                )
                ToggleSwitch(
                    checked = blueIsPlayedActive,
                    onCheckedChange = { blueIsPlayedActive = it },
                    label = "Blue has played"
                )
            }
            Row {
                OutlinedTextField(
                    value = leaderDraw,
                    onValueChange = { leaderDraw = it },
                    label = { Text("Leader Draw") },
                    singleLine = true
                )
                OutlinedTextField(
                    value = assistantDraw,
                    onValueChange = { assistantDraw = it },
                    label = { Text("Assistant Draw") },
                    singleLine = true
                )
            }
            Row {
                Button(
                    onClick = { navigator.pop() }
                ) {
                    Text("Cancel")
                }
                Button(
                    onClick = {
                        val newEvent = Event(
                            round,
                            0,
                            electionTracker,
                            blueCardsPlayed,
                            redCardsPlayed,
                            User(leader),
                            User(assistant),
                            if (voteAccepted) "yes" else "no",
                            if (blueIsPlayedActive) 'B' else 'R',
                            leaderDraw,
                            assistantDraw
                        )
                        gameService.addEvent(newEvent)
                        navigator.pop()
                    }
                ) {
                    Text("Save Result")
                }
            }
        }
    }
}

@Composable
fun NumberInputField(value: Int, onValueChange: (Int) -> Unit, label: String) {
    var text by remember { mutableStateOf(value.toString()) }

    OutlinedTextField(
        value = text,
        onValueChange = { newText ->
            text = newText.filter { it.isDigit() }
            onValueChange(text.toIntOrNull() ?: value)
        },
        label = { Text(label) },
        singleLine = true
    )
}

@Composable
fun ToggleSwitch(checked: Boolean, onCheckedChange: (Boolean) -> Unit, label: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(label)
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
    }
}