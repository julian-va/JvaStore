package jva.cloud.jvastore.presentation.view.userinformationview

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.hilt.navigation.compose.hiltViewModel
import jva.cloud.jvastore.presentation.view.userinformationview.component.MyBodyUserInformationView
import jva.cloud.jvastore.presentation.view.userinformationview.component.MyTopAppBarUserInformationView
import jva.cloud.jvastore.presentation.view.userinformationview.viewmodel.UserInformationViewModel
import java.util.Objects

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserInformationView(
    redirectCreateUser: () -> Unit,
    navigateToStore: () -> Unit,
    userInformationViewModel: UserInformationViewModel = hiltViewModel()
): Unit {
    val pinnedScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val state = userInformationViewModel.state.value
    if (state.redirectCreateUser) redirectCreateUser()

    if (Objects.nonNull(state.user)) {
        Scaffold(modifier = Modifier
            .fillMaxSize()
            .nestedScroll(pinnedScrollBehavior.nestedScrollConnection),
            topBar = {
                MyTopAppBarUserInformationView(
                    pinnedScrollBehavior = pinnedScrollBehavior,
                    navigateToStore = { navigateToStore() })
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { userInformationViewModel.deleteUser(user = state.user!!) }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "")
                }
            }) { paddingValues ->
            MyBodyUserInformationView(
                modifier = Modifier.padding(paddingValues),
                user = state.user!!
            )
        }
    }
}
