package com.berkayyaman.footballapp.presentation.navigator

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.berkayyaman.footballapp.R
import com.berkayyaman.footballapp.util.Dimens

/**
 * Created by berkayyaman on 21,October,2024
 */
@Composable
fun BottomNavigation(
    items: List<BottomNavigationItem>,
    selectedItem: Int,
    onItemClick: (Int) -> Unit
) {

    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.primary,
        tonalElevation = 10.dp
    ) {

        items.forEachIndexed{index, bottomNavigationItem ->

            NavigationBarItem(
                selected = index == selectedItem,
                onClick = {onItemClick(index)},
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primaryContainer,
                    unselectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    unselectedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    selectedTextColor = MaterialTheme.colorScheme.primaryContainer,
                    indicatorColor = MaterialTheme.colorScheme.primary
                ),
                icon = {
                    Column {
                        if (index == selectedItem){
                            Icon(
                                painter = painterResource(id = bottomNavigationItem.icon),
                                contentDescription = bottomNavigationItem.text,
                                modifier = Modifier
                                    .size(Dimens.BottomNavigationItemSelectedSize)
                                    .shadow(24.dp, CircleShape,false, Color.Red)
                            )
                        }else{
                            Icon(
                                painter = painterResource(id = bottomNavigationItem.icon),
                                contentDescription = bottomNavigationItem.text,
                                modifier = Modifier.size(Dimens.BottomNavigationItemSize)
                            )
                        }
                    }
                },
                label = {
                    if (index == selectedItem){
                        Text(
                            text = bottomNavigationItem.text,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                    }else{
                        Text(
                            text = bottomNavigationItem.text,
                            style = MaterialTheme.typography.titleSmall
                        )
                    }
                }
            )

        }

    }

}

class BottomNavigationItem(
    @DrawableRes val icon: Int, val text: String
)

@Preview
@Composable
fun BottomNavigationPreview(){
    BottomNavigation(
        items = arrayListOf(
            BottomNavigationItem(R.drawable.home,"Home"),
            BottomNavigationItem(R.drawable.league,"League"),
            BottomNavigationItem(R.drawable.players,"Players")
        ),
        selectedItem = 0
    ) { }
}
