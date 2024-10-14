package com.berkayyaman.footballapp.presentation.searchbar

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.berkayyaman.footballapp.R
import com.berkayyaman.footballapp.util.Dimens

/**
 * Created by berkayyaman on 14,October,2024
 */
@Composable
fun SearchBar(
    event: (SearchEvent) -> Unit,
    hint: String = "Takım Ara",
) {
    MyScreen(hint  = hint, event = event)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MyScreen(hint: String = "Takım Ara", event: (SearchEvent) -> Unit){

    Box(
        modifier = Modifier.padding(Dimens.MediumSpace)
    ){
        var text by remember{
            mutableStateOf("")
        }

        TextField(
            value = text,
            onValueChange = {
                text = it
                event.invoke(SearchEvent.UpdateSearchString(text))
            },
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.5.dp,
                    color = Color.Black,
                    shape = MaterialTheme.shapes.medium
                ),
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.ic_search),
                    contentDescription = null,
                    Modifier.size(20.dp),
                    colorResource(id = R.color.gray)
                )
            },
            placeholder = {
                Text(
                    text = hint,
                    style = MaterialTheme.typography.bodySmall,
                    color = colorResource(R.color.gray)
                )
            },
            shape = MaterialTheme.shapes.medium,
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                disabledTextColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            textStyle = MaterialTheme.typography.bodySmall,
            keyboardActions = KeyboardActions(
                onSearch = {
                    event.invoke(SearchEvent.Search)
                }
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            )

        )
    }

}

@Preview
@Composable
private fun SearchBarPreview(){
    MyScreen(
        event = {}
    )
}