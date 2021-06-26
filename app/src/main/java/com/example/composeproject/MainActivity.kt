package com.example.composeproject

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.composeproject.ui.theme.ComposeProjectTheme
import com.example.core.domain.model.Popular
import com.example.core.util.Util
import com.google.accompanist.glide.rememberGlidePainter
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.android.viewmodel.ext.android.viewModel

@InternalCoroutinesApi

class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    DarkItemContent(mainViewModel)
                }
            }
        }
    }
}

@InternalCoroutinesApi
@Composable
fun DarkItemContent(mainViewModel: MainViewModel) {
    val popular: List<Popular> by mainViewModel.uiState.collectAsState()
    LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp)) {
        items(items = popular, itemContent = { popular1 ->
            PopularListItem(popular = popular1, onItemCLicked = {
                Log.e("CLICK", it.title.toString())
            })
        })
    }
}

@Composable
fun PopularListItem(popular: Popular, onItemCLicked: (Popular) -> Unit) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clickable(onClick = { onItemCLicked(popular) }),
        elevation = 2.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ) {
        Row {
            PopularImage(popular = popular)
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = popular.title.toString())
            }

        }
    }

}

@Composable
fun PopularImage(popular: Popular) {
    Image(
        painter = rememberGlidePainter(request = Util.image_base_url + popular.posterPath),
        contentDescription = popular.title,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(8.dp)
            .size(84.dp)
            .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
    )
}


//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    ComposeProjectTheme {
//        DarkItemContent(d)
//    }
//}

