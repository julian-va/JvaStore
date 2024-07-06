package jva.cloud.jvastore.presentation.view.storeview.component


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import jva.cloud.jvastore.domain.model.Category
import jva.cloud.jvastore.presentation.common.MyAsyncImage
import jva.cloud.jvastore.presentation.common.MyDialog
import jva.cloud.jvastore.util.ConstantApp.STRING_EMPTY

@Composable
internal fun MyBottomAppBarStore(
    showDialog: Boolean,
    categories: List<Category>,
    openDialog: () -> Unit,
    onDismiss: () -> Unit,
    reprocessImage: (List<String>) -> String,
    retrieveCategories: (String) -> Unit,
    navigateToCart: () -> Unit
): Unit {

    MyDialog(showDialog = showDialog, onDismiss = { onDismiss() }) {
        CategoryFilter(
            categories = categories,
            reprocessImage = { reprocessImage(it) },
            retrieveCategories = { retrieveCategories(it) })
    }
    BottomAppBar {
        IconButton(modifier = Modifier.weight(1f), onClick = { openDialog() }) {
            Icon(imageVector = Icons.Filled.Menu, contentDescription = "")
        }
        IconButton(modifier = Modifier.weight(1f), onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Filled.Person, contentDescription = "")
        }
        IconButton(modifier = Modifier.weight(1f), onClick = { navigateToCart() }) {
            Icon(imageVector = Icons.Filled.ShoppingCart, contentDescription = "")
        }
    }
}

@Composable
private fun CategoryFilter(
    categories: List<Category>,
    reprocessImage: (List<String>) -> String,
    retrieveCategories: (String) -> Unit,
): Unit {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(color = MaterialTheme.colorScheme.background)

    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            item {
                Text(
                    text = "Filter by category",
                    maxLines = 1,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
            }
            items(categories, key = { category -> category.id }) { category ->
                CardCategory(
                    category = category,
                    reprocessImage = { reprocessImage(it) },
                    retrieveCategories = { retrieveCategories(it) })
            }

            item {
                OutlinedButton(onClick = { retrieveCategories(STRING_EMPTY) }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "")
                    Text(text = "Reset category filter")
                }
            }
        }
    }
}

@Composable
private fun CardCategory(
    category: Category,
    reprocessImage: (List<String>) -> String,
    retrieveCategories: (String) -> Unit,
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .clickable { retrieveCategories(category.name) }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            MyAsyncImage(
                imagePath = reprocessImage(listOf(category.image)), modifier = Modifier
                    .height(50.dp)
                    .width(50.dp)
                    .padding(5.dp)
                    .weight(1f),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = category.name,
                maxLines = 1,
                modifier = Modifier.padding(end = 5.dp),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.secondary,
                fontWeight = FontWeight.Bold
            )
        }
    }
}