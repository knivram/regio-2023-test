package ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import service.GameService
import service.MAX_CARD_B
import service.MAX_CARD_R
import service.toCellEntryList

@Composable
fun GameStats(
    gameService: GameService
) {
    Column {
        Row {
            Text("Round")
            Spacer(modifier = Modifier.width(16.dp))
            Text(gameService.roundNumer.toString())
            Spacer(modifier = Modifier.width(32.dp))
            Column {
                Text("Blue: ${gameService.cardsB}/$MAX_CARD_B")
                Spacer(modifier = Modifier.height(5.dp))
                Text("Blue: ${gameService.cardsR}/$MAX_CARD_R")
            }
        }

        TableComponent(tableData = gameService.getEvents().map { TableRowData(it.toCellEntryList()) })
    }
}

data class CellEntry(
    val text: String,
    val color: Color? = null
) {
    constructor(text: Int): this(text.toString())
}

data class TableRowData(val cells: List<CellEntry>)

@Composable
fun TableCell(cellEntry: CellEntry) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .border(BorderStroke(1.dp, Color.Black))
            .padding(8.dp)
            .background(cellEntry.color ?: Color.Transparent)
    ) {
        Text(cellEntry.text)
    }
}

@Composable
fun TableRow(rowData: TableRowData) {
    LazyRow {
        items(rowData.cells) { cellText ->
            TableCell(cellText)
        }
    }
}

@Composable
fun TableComponent(tableData: List<TableRowData>) {
    LazyColumn {
        items(tableData) { rowData ->
            TableRow(rowData = rowData)
        }
    }
}