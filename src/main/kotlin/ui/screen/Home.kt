package ui.screen

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

class Home : Screen{

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Text("Home")
    }
}
