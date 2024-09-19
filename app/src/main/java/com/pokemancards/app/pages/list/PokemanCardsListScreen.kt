package com.pokemancards.app.pages.list

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.imageLoader
import coil.request.ImageRequest
import coil.util.DebugLogger
import com.pokemancards.app.model.Images
import com.pokemancards.app.model.PokemanCard
import com.pokemancards.app.ui.theme.PokemanCardsAppTheme

@Composable
fun PokemanCardsListScreen(
    callbacks: PokemanCardsListCallbacks,
    listData: PokemanCardsListData,
    items: List<PokemanCard>,
    modifier: Modifier = Modifier
) {
    val imageLoader = LocalContext.current.imageLoader.newBuilder()
        .logger(DebugLogger())
        .build()

    var searchText = remember { listData.searchText }
    var sort = remember { listData.sort }

    Log.d("ASKAR", "search "+ searchText.value)
    Log.d("ASKAR", "sort "+ sort.value)

    if (items.isEmpty() && searchText.value.isEmpty()) {
        LinearProgressIndicator(
            modifier = modifier.fillMaxWidth()
        )
    }

    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                SearchText(
                    Modifier
                        .weight(3f)
                        .padding(2.dp)
                        .size(60.dp),
                    searchText,
                    callbacks
                )
                Button(
                    modifier = Modifier
                        .weight(1.2f)
                        .padding(2.dp)
                        .height(50.dp),
                    shape = RectangleShape,
                    onClick = {
                        if (sort.value == "level") {
                            sort.value = ""
                        } else {
                            sort.value = "level"
                        }
                        callbacks.onSortClick.invoke(sort.value)
                    },
                    colors = if (sort.value == "level") ButtonDefaults.buttonColors(containerColor = Color.Blue) else ButtonDefaults.buttonColors(
                        containerColor = Color.Gray
                    )
                ) {
                    Text(text = "Level")
                }
                Button(
                    modifier = Modifier
                        .weight(1f)
                        .padding(2.dp)
                        .height(50.dp),
                    shape = RectangleShape,
                    onClick = {
                        if (sort.value == "hp") {
                            sort.value = ""
                        } else {
                            sort.value = "hp"
                        }
                        callbacks.onSortClick.invoke(sort.value)
                    },
                    colors = if (sort.value == "hp") ButtonDefaults.buttonColors(containerColor = Color.Blue) else ButtonDefaults.buttonColors(
                        containerColor = Color.Gray
                    )
                ) {
                    Text(text = "HP")
                }
            }
        }

        if (items.isEmpty() && searchText.value.isNotEmpty()) {
            item {
                Text(
                    modifier = modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "No data"
                )
            }
        }

        items(items = items) { item ->
            PokemanCardItem(item, imageLoader, callbacks)
        }
    }
}

@Composable
fun SearchText(
    modifier: Modifier,
    search: MutableState<String>,
    callbacks: PokemanCardsListCallbacks
) {
    var searchText by remember { search }
    TextField(
        modifier = modifier.fillMaxWidth(),
        value = searchText,
        onValueChange = {
            searchText = it
            callbacks.onSearchQueryChange.invoke(it)
            Log.d("ASKAR", "SearchText: $it")
        },
        label = { Text("Search") },
    )

    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
fun PokemanCardItem(item: PokemanCard, imageLoader: ImageLoader, callbacks: PokemanCardsListCallbacks) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .clickable {
                callbacks.onItemClick.invoke(item)
            }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            AsyncImage(
                modifier = Modifier
                    .padding(5.dp)
                    .weight(1f)
                    .align(Alignment.CenterVertically),
                imageLoader = imageLoader,
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item.images.small)
                    .build(),
                contentScale = ContentScale.Fit,
                contentDescription = "Image"
            )

            Column(
                modifier = Modifier.weight(2f)
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = item.name
                )
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "Type: " + item.types.first()
                )
                if (item.level != null) {
                    Text(
                        modifier = Modifier.padding(4.dp),
                        text = "Level: " + item.level
                    )
                }
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "HP: " + item.hp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PokemanCardsAppTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
            PokemanCardsListScreen(
                PokemanCardsListCallbacks({}, {}, {}),
                PokemanCardsListData(remember { mutableStateOf("") }, remember { mutableStateOf("")}),
                listOf(PokemanCard(
                    "",
                    Images("", ""),
                    "",
                    listOf(),
                    "",
                    "",
                    listOf(),
                    listOf(),
                    listOf(),
                    listOf(),
                    listOf(),
                    ))
            )
        }
    }
}
