package com.example.movieapp.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.movieapp.model.Movie

@Composable
fun MovieRow(movie: Movie, onItemClicked: (String) -> Unit = {} ) {
    var expanded = remember {
        mutableStateOf(false) }
    Card(modifier = Modifier
        .padding(4.dp)
        .fillMaxWidth(),
        onClick = {
            onItemClicked(movie.id)
        },
        border = BorderStroke(3.dp, MaterialTheme.colorScheme.secondary),
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)) {
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start) {

            Surface(modifier = Modifier
                .padding(12.dp)
                .size(110.dp), shape = RectangleShape) {
                AsyncImage(
                    model = movie.images[0],
                    contentDescription = null,
                    modifier = Modifier
                        .size(110.dp)
                        .clip(RectangleShape) // Native Compose clipping
                        .background(MaterialTheme.colorScheme.primaryContainer) // Background behind the image
                )

            }
            Column(modifier = Modifier.padding(top= 5.dp, bottom = 5.dp, start = 5.dp, end = 5.dp)) {
                Text(color = MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.ExtraBold, text = movie.title)
                Text(color = MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.titleSmall, text = "Director: ${movie.director}")
                Text(color = MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.titleSmall, text = "Released: ${movie.year}")

                AnimatedVisibility(visible = expanded.value) {
                    Column(modifier = Modifier.padding(4.dp)) {
                        HorizontalDivider(
                            modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                            thickness = 2.dp,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.primary,
                            text = "Genre: ${movie.genre}"
                        )
                        Text(
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.primary,
                            text = "Plot: ${movie.plot}"
                        )
                        Text(
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.primary,
                            text = "Actors: ${movie.actors}"
                        )
                        Text(
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.primary,
                            text = "Rating: ${movie.rating}"
                        )
                    }
                }

                //CardExtension
                Icon(
                    imageVector = if (expanded.value) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    contentDescription = "Down Arrow",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .size(25.dp)
                        .clickable {
                            expanded.value = !expanded.value
                        },
                )
            }
        }
    }
}