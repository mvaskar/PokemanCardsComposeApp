package com.pokemancards.app.pages.detail

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.imageLoader
import coil.request.ImageRequest
import coil.util.DebugLogger
import com.pokemancards.app.model.Ability
import com.pokemancards.app.model.Attack
import com.pokemancards.app.model.Images
import com.pokemancards.app.model.PokemanCard
import com.pokemancards.app.model.Resistance
import com.pokemancards.app.model.Weakness
import com.pokemancards.app.ui.theme.PokemanCardsAppTheme

@Composable
fun PokemanCardDetailScreen(
    item: PokemanCard,
    modifier: Modifier = Modifier
) {
    val imageLoader = LocalContext.current.imageLoader.newBuilder()
        .logger(DebugLogger())
        .build()


    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        pokemanCardDetailView(item, imageLoader)
    }
}

fun LazyListScope.pokemanCardDetailView(item: PokemanCard, imageLoader: ImageLoader) {
    item {
        AsyncImage(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
                .height(250.dp),
            imageLoader = imageLoader,
            model = ImageRequest.Builder(LocalContext.current)
                .data(item.images.large)
                .build(),
            contentScale = ContentScale.Fit,
            contentDescription = "Image"
        )
    }

    item {
        Text(
            modifier = Modifier.padding(10.dp),
            text = item.name,
            fontWeight = FontWeight.Bold
        )
    }

    item {
        Row {
            Text(
                modifier = Modifier.padding(10.dp),
                text = "Type: ",
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier.padding(vertical = 10.dp),
                text = item.types.first()
            )
        }
    }


    if (item.level != null) {
        item {
            Row {
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = "Level: ",
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier.padding(vertical = 10.dp),
                    text = item.level!!
                )
            }
        }
    }

    item {
        Row {
            Text(
                modifier = Modifier.padding(10.dp),
                text = "HP: ",
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier.padding(vertical = 10.dp),
                text = item.hp
            )
        }
    }

    item.subTypes?.let { subTypes ->
        item {
            Row {
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = "Sub Type: ",
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier.padding(vertical = 10.dp),
                    text = item.subTypes.first()
                )
            }
        }
    }

    if (item.attacks?.isEmpty() == false) {
        item {
            Text(
                modifier = Modifier.padding(10.dp),
                text = "Attacks",
                fontWeight = FontWeight.Bold
            )
        }

        attackDetailView(item.attacks)
    }

    if (item.abilities?.isEmpty() == false) {
        item {
            Text(
                modifier = Modifier.padding(10.dp),
                text = "Abilities",
                fontWeight = FontWeight.Bold
            )
        }

        abilitiesDetailView(item.abilities)
    }

    if (item.weaknesses?.isEmpty() == false) {
        item {
            Text(
                modifier = Modifier.padding(10.dp),
                text = "Weaknesses",
                fontWeight = FontWeight.Bold
            )
        }

        weaknessesDetailView(item.weaknesses)
    }

    if (item.resistances?.isEmpty() == false) {
        item {
            Text(
                modifier = Modifier.padding(10.dp),
                text = "Resistances",
                fontWeight = FontWeight.Bold
            )
        }

        resistancesDetailView(item.resistances)
    }
}

fun LazyListScope.attackDetailView(list: List<Attack>) {
    items(items= list) {  item ->

        Text(
            modifier = Modifier.padding(horizontal = 25.dp, vertical = 4.dp),
            text = item.name
        )
        Text(
            modifier = Modifier.padding(horizontal = 25.dp, vertical = 4.dp),
            text = item.text
        )
        Divider(modifier = Modifier.height(1.dp),
            color = Color.Gray)
    }
}

fun LazyListScope.abilitiesDetailView(list: List<Ability>) {
    items(items= list) {  item ->

        Text(
            modifier = Modifier.padding(horizontal = 25.dp, vertical = 4.dp),
            text = item.name
        )
        Text(
            modifier = Modifier.padding(horizontal = 25.dp, vertical = 4.dp),
            text = item.text
        )
        Text(
            modifier = Modifier.padding(horizontal = 25.dp, vertical = 4.dp),
            text = item.type
        )
        Divider(modifier = Modifier.height(1.dp),
            color = Color.Gray)
    }
}

fun LazyListScope.weaknessesDetailView(list: List<Weakness>) {
    items(items= list) {  item ->

        Text(
            modifier = Modifier.padding(horizontal = 25.dp, vertical = 4.dp),
            text = item.type + " " + item.value
        )
        Divider(modifier = Modifier.height(1.dp),
            color = Color.Gray)
    }
}

fun LazyListScope.resistancesDetailView(list: List<Resistance>) {
    items(items= list) {  item ->

        Text(
            modifier = Modifier.padding(horizontal = 25.dp, vertical = 4.dp),
            text = item.type + " " + item.value
        )
        Divider(modifier = Modifier.height(1.dp),
            color = Color.Gray)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PokemanCardsAppTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
            PokemanCardDetailScreen(
                PokemanCard(
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
                )
            )
        }
    }
}
