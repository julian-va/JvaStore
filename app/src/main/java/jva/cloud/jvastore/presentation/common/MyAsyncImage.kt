package jva.cloud.jvastore.presentation.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import jva.cloud.jvastore.R


@Composable
fun MyAsyncImage(
    imagePath: String,
    modifier: Modifier,
    contentScale: ContentScale = ContentScale.Crop
): Unit {

    val image = ImageRequest.Builder(LocalContext.current).data(imagePath)
        .error(drawableResId = R.drawable.ic_broken_image).crossfade(2000).build()

    AsyncImage(
        model = image,
        contentScale = contentScale,
        contentDescription = "",
        modifier = modifier
    )
}