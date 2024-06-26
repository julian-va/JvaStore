package jva.cloud.jvastore.presentation.view.carview

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jva.cloud.jvastore.presentation.view.carview.component.CartDescription
import jva.cloud.jvastore.presentation.view.carview.component.MinimumOrderView
import jva.cloud.jvastore.presentation.view.carview.component.MyTopAppBarCarView
import jva.cloud.jvastore.presentation.view.carview.component.SubtotalView
import jva.cloud.jvastore.util.ConstantApp
import jva.cloud.jvastore.util.ConstantApp.BOOLEAN_FALSE

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarView(): Unit {
    val pinnedScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(pinnedScrollBehavior.nestedScrollConnection),
        topBar = { MyTopAppBarCarView(pinnedScrollBehavior = pinnedScrollBehavior) }) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues),
        ) {
            MinimumOrderView(
                text = "you need x value to complete the minimum order",
                showIcon = BOOLEAN_FALSE
            )

            CartDescription()
            MinimumOrderView(
                text = "you need x value to complete the minimum order",
                showIcon = ConstantApp.BOOLEAN_TRUE
            )
            Spacer(modifier = Modifier.padding(10.dp))
            SubtotalView()
        }

    }

}


@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
    showBackground = true
)
@Composable
private fun Preview(): Unit {
    CarView()
}