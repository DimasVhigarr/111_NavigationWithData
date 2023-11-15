@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.praktekappesjumbo.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.praktekappesjumbo.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HalamanSatu(
    onSubmitButtonClicked: (MutableList<String>) -> Unit,
    onBackButtonClicked: () -> Unit
){

    var namaTxt by remember{
        mutableStateOf("")
    }

    var alamatTxt by remember {
        mutableStateOf("")
    }

    var tlpTxt by remember {
        mutableStateOf("")
    }
    var listData: MutableList<String> = mutableListOf(namaTxt,alamatTxt,tlpTxt)

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Masukan Data diri", fontWeight = FontWeight.Bold, fontSize = 25.sp)
        Column(
            modifier = Modifier.padding(20.dp)
        ) {

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = namaTxt,
                shape = MaterialTheme.shapes.large,
                label = { Text(text = stringResource(id = R.string.nama)) },
                onValueChange = {namaTxt = it}
            )
            Spacer(modifier = Modifier.padding(10.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = alamatTxt,
                shape = MaterialTheme.shapes.large,
                label = { Text(text = stringResource(id = R.string.alamat)) },
                onValueChange = {alamatTxt = it}
            )
            Spacer(modifier = Modifier.padding(10.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = tlpTxt,
                shape = MaterialTheme.shapes.large,
                label = { Text(text = stringResource(id = R.string.tlp)) },
                onValueChange = {tlpTxt = it}
            )
            Spacer(modifier = Modifier.padding(10.dp))
        }
        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.padding_medium))
            .weight(1f, false),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
            verticalAlignment = Alignment.Bottom
        ){
            OutlinedButton(
                modifier = Modifier.weight(1f),
                onClick = onBackButtonClicked) {
                Text(text = stringResource(id = R.string.back_button))
            }

            OutlinedButton(
                modifier = Modifier.weight(1f),
                onClick = { onSubmitButtonClicked(listData) }) {
                Text(text = stringResource(id = R.string.btn_submit))
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun HalamanHasilPreview() {
    PraktekAppEsJumboTheme {
        HalamanSatu(onSubmitButtonClicked = {}) {
        }
    }
}

