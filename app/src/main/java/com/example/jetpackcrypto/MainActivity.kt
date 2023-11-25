package com.example.jetpackcrypto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcrypto.model.CoinsData
import com.example.jetpackcrypto.ui.theme.JetpackCryptoTheme
import com.example.jetpackcrypto.ui.theme.components.list_coin.CryptoListItem
import com.example.jetpackcrypto.ui.theme.components.scroll.ScrollToTopButton
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackCryptoTheme {
                // A surface container using the 'background' color from the theme
                JetpackCryptoApp()
            }
        }
    }
}

@Composable
fun JetpackCryptoApp(modifier: Modifier = Modifier) {

        Box(modifier = modifier.fillMaxSize())    {
            val scope = rememberCoroutineScope()
            val listState = rememberLazyListState()
            val showButton: Boolean by remember {
                derivedStateOf { listState.firstVisibleItemIndex > 0 }
            }

    LazyColumn(state = listState, modifier = Modifier.padding(10.dp)){
        items(CoinsData.coins, key = { it.id }) { hero ->
            CryptoListItem(name = hero.name, price = hero.price)
        }
    }
            AnimatedVisibility(
                visible = showButton,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically(),
                modifier = Modifier
                    .padding(bottom = 30.dp)
                    .align(Alignment.BottomCenter)
            ) {
                ScrollToTopButton(
                    onClick = {
                        scope.launch {
                            listState.scrollToItem(index = 0)
                        }
                    }
                )
            }

        }


    }


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackCryptoTheme {
        JetpackCryptoApp()
    }
}