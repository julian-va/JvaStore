package jva.cloud.jvastore.presentation.view.createuserview.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import jva.cloud.jvastore.R
import jva.cloud.jvastore.presentation.common.MyOutlinedTextField
import jva.cloud.jvastore.presentation.view.createuserview.viewmodel.CreateUserViewModelState

@Composable
fun BodyCreateUserView(
    modifier: Modifier,
    nameSet: (String) -> Unit,
    emailSet: (String) -> Unit,
    andresSet: (String) -> Unit,
    dniSet: (String) -> Unit,
    createUser: () -> Unit,
    state: CreateUserViewModelState
): Unit {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Icon(
            modifier = Modifier
                .height(100.dp)
                .width(100.dp)
                .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape),
            imageVector = Icons.Default.Person,
            contentDescription = ""
        )
        Spacer(modifier = Modifier.padding(5.dp))
        Text(
            text = stringResource(R.string.create_user),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary,
            // fontSize = TextUnit(value = 37f, TextUnitType.Sp),
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.padding(10.dp))
        MyOutlinedTextField(
            fieldName = stringResource(R.string.user_name),
            text = state.userName,
            imageVector = Icons.Default.Person,
            keyboardOptions = KeyboardOptions.Default,
            onValueChange = { nameSet(it) })
        Spacer(modifier = Modifier.padding(10.dp))
        MyOutlinedTextField(
            fieldName = stringResource(R.string.user_email),
            text = state.email,
            imageVector = Icons.Default.Email,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            onValueChange = { emailSet(it) })
        Spacer(modifier = Modifier.padding(10.dp))
        MyOutlinedTextField(
            fieldName = stringResource(R.string.address),
            text = state.address,
            imageVector = Icons.Default.Home,
            keyboardOptions = KeyboardOptions.Default,
            onValueChange = { andresSet(it) })
        Spacer(modifier = Modifier.padding(10.dp))
        MyOutlinedTextField(
            fieldName = stringResource(R.string.dni),
            text = state.dni,
            imageVector = Icons.Default.AccountBox,
            keyboardOptions = KeyboardOptions.Default,
            onValueChange = { dniSet(it) })
        Spacer(modifier = Modifier.padding(10.dp))
        Button(modifier = Modifier.fillMaxWidth(), onClick = { createUser() }) {
            Text(text = "Save")
        }
    }
}
