package jva.cloud.jvastore.presentation.view.detailstore.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import jva.cloud.jvastore.domain.model.Product
import jva.cloud.jvastore.presentation.common.MyOutlinedTextField
import jva.cloud.jvastore.util.ConstantApp.STRING_EMPTY

@Composable
internal fun AddProductDetailStore(
    quantity: String,
    product: Product,
    addQuantity: (quantity: String) -> Unit,
    addCar: (product: Product) -> Unit
) {
    MyOutlinedTextField(
        fieldName = "Add product",
        text = quantity.ifBlank { STRING_EMPTY },
        imageVector = Icons.Default.ShoppingCart,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        onValueChange = { addQuantity(it) })
    Button(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 20.dp), onClick = { addCar(product) }) {
        Text(text = "add product")
    }


}
