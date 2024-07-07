package jva.cloud.jvastore.presentation.view.userinformationview.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import jva.cloud.jvastore.R
import jva.cloud.jvastore.domain.model.User
import jva.cloud.jvastore.presentation.common.TextValueCard
import jva.cloud.jvastore.util.ConstantApp

@Composable
fun MyBodyUserInformationView(modifier: Modifier, user: User): Unit {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = "Contact information",
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .padding(20.dp),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.secondary,
            fontWeight = FontWeight.Bold,
            fontSize = TextUnit(value = 30f, TextUnitType.Sp),
        )
        ElevatedCard(
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            modifier = Modifier
                .height(470.dp)
                .fillMaxWidth()
                .padding(20.dp)
                .verticalScroll(rememberScrollState())

        ) {
            Spacer(modifier = Modifier.padding(10.dp))
            Icon(
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)
                    .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
                    .align(Alignment.CenterHorizontally),
                imageVector = Icons.Default.Person,
                contentDescription = ""
            )
            Spacer(modifier = Modifier.padding(10.dp))
            TextValueCard(
                textHeader = stringResource(R.string.user_name),
                text = user.userName,
                maxLines = 0,
                isRow = ConstantApp.BOOLEAN_FALSE
            )
            TextValueCard(
                textHeader = stringResource(R.string.user_email),
                text = user.email,
                maxLines = 0,
                isRow = ConstantApp.BOOLEAN_FALSE
            )
            TextValueCard(
                textHeader = stringResource(R.string.dni),
                text = user.dni,
                maxLines = 0,
                isRow = ConstantApp.BOOLEAN_FALSE
            )
            TextValueCard(
                textHeader = stringResource(R.string.address),
                text = user.address,
                maxLines = 0,
                isRow = ConstantApp.BOOLEAN_FALSE
            )
        }
    }
}
