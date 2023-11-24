package com.example.jetpackcrypto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcrypto.model.CoinsData
import com.example.jetpackcrypto.ui.theme.JetpackCryptoTheme
import com.example.jetpackcrypto.ui.theme.components.list_coin.CryptoListItem

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

        Box(modifier = modifier)    {

    LazyColumn{
        items(CoinsData.coins, key = { it.id }) { hero ->
            CryptoListItem(name = hero.name, price = hero.price)
        }
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