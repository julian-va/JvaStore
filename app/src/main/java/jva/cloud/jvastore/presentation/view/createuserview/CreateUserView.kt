package jva.cloud.jvastore.presentation.view.createuserview

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import jva.cloud.jvastore.R
import jva.cloud.jvastore.presentation.common.MyAlertDialog
import jva.cloud.jvastore.presentation.view.createuserview.component.BodyCreateUserView
import jva.cloud.jvastore.presentation.view.createuserview.component.MyTopAppBarCreateUserView
import jva.cloud.jvastore.presentation.view.createuserview.viewmodel.CreateUserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateUserView(
    createUserViewModel: CreateUserViewModel = hiltViewModel(),
    redirectUserInformation: () -> Unit,
    navigateToStore: () -> Unit
): Unit {
    val pinnedScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val state = createUserViewModel.state.value
    if (state.redirectUserInformation) redirectUserInformation()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(pinnedScrollBehavior.nestedScrollConnection),
        topBar = {
            MyTopAppBarCreateUserView(
                pinnedScrollBehavior = pinnedScrollBehavior,
                navigateToStore = { navigateToStore() })
        }
    ) { paddingValues ->
        MyAlertDialog(
            showDialog = state.showDialog,
            title = stringResource(id = R.string.error),
            text = stringResource(id = R.string.error_data),
            onDismiss = { createUserViewModel.onDialogDismiss() },
            onConfirm = { createUserViewModel.onDialogDismiss() })
        BodyCreateUserView(
            modifier = Modifier.padding(paddingValues),
            nameSet = { createUserViewModel.updateParameterStatus(userName = it) },
            emailSet = { createUserViewModel.updateParameterStatus(email = it) },
            andresSet = { createUserViewModel.updateParameterStatus(address = it) },
            dniSet = { createUserViewModel.updateParameterStatus(dni = it) },
            createUser = { createUserViewModel.createUser() }, state = state
        )
    }
}

