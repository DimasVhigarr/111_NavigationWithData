@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.praktekappesjumbo.ui.theme

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.praktekappesjumbo.data.SumberData.flavors
import com.example.praktekappesjumbo.R

enum class PengelolaHalaman {
    Home,
    Rasa,
    Form,
    Summary
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EsJumboAppBar(
    bisaNavigasiBack: Boolean,
    navigasiUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(R.string.app_name)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = Modifier,
        navigationIcon = {
            if (bisaNavigasiBack) {
                IconButton(onClick = navigasiUp) {
                    Icon(
                        painterResource(R.drawable.arrow_back), contentDescription = stringResource(
                            R.string.back_button
                        )
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EsJumboApp(
    viewModel: OrderViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        topBar = {
            EsJumboAppBar(bisaNavigasiBack = false, navigasiUp = { /*TODO*/ })
        }
    ) { innerPadding ->
        val uiState by viewModel.stateUI.collectAsState()
        val namaState by viewModel.namaST.collectAsState()
        NavHost(
            navController = navController,
            startDestination = PengelolaHalaman.Home.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = PengelolaHalaman.Home.name) {
                HalamanHome(onNextButtonClicked = {
                    navController.navigate(PengelolaHalaman.Form.name)
                }
                )
            }
            composable(route = PengelolaHalaman.Form.name){
                HalamanSatu(
                    onSubmitButtonClicked = {
                        viewModel.setNama(it)
                        navController.navigate(PengelolaHalaman.Rasa.name)},
                    onBackButtonClicked = {navController.popBackStack()})
            }

            composable(route = PengelolaHalaman.Rasa.name) {
                val context = LocalContext.current
                HalamanDua(
                    pilihanRasa = flavors.map{ id -> context.resources.getString(id) },
                    onSelectionChanged = { viewModel.setRasa(it) },
                    onConfirmButtonClicked = { viewModel.setJumlah(it) },
                    onNextButtonClicked = { navController.navigate(PengelolaHalaman.Summary.name) },
                    onCancelButtonClicked = {
                        cancelOrderAndNavigateToHome(
                            viewModel,
                            navController
                        )
                    })
            }
            composable(route = PengelolaHalaman.Summary.name) {
                HalamanHasil(
                    orderUiState = uiState,
                    contactUiState = namaState,
                    onCancelButtonClicked = { cancelOrderAndNavigateToRasa(navController) })
            }
        }
    }
}

private fun cancelOrderAndNavigateToHome(
    viewModel: OrderViewModel,
    navController: NavHostController
) {
    viewModel.resetOrder()
    navController.popBackStack(PengelolaHalaman.Home.name, inclusive = false)
}

private fun cancelOrderAndNavigateToRasa(
    navController: NavHostController
) {
    navController.popBackStack(PengelolaHalaman.Rasa.name, inclusive = false)
}