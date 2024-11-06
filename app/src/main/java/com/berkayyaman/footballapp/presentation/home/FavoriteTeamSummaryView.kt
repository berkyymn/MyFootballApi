package com.berkayyaman.footballapp.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.berkayyaman.footballapp.domain.model.TeamUiModel
import com.berkayyaman.footballapp.ui.theme.FootballAppTheme
import com.berkayyaman.footballapp.util.Dimens
import com.berkayyaman.footballapp.util.MockData

/**
 * Created by berkayyaman on 05,November,2024
 */


@Composable
fun FavoriteTeamSummaryView(
    favoriteTeam: TeamUiModel
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimens.SmallSpace),
        verticalAlignment = Alignment.CenterVertically
    ) {


        AsyncImage(
            modifier = Modifier
                .size(Dimens.TeamLogoRowSize * 2)
                .clip(MaterialTheme.shapes.medium),
            model = ImageRequest.Builder(
                context = LocalContext.current
            ).data(favoriteTeam.teamInfo.logo).build(),
            contentDescription = favoriteTeam.teamInfo.name,
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier.padding(start = Dimens.SmallSpace)
        ){

            Column {
                Text(
                    textAlign = TextAlign.Center,
                    text = favoriteTeam.teamInfo.name,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    textAlign = TextAlign.Center,
                    text = "${favoriteTeam.teamInfo.country} | ${favoriteTeam.venue.city} | ${favoriteTeam.teamInfo.founded}",
                    style = MaterialTheme.typography.bodySmall
                )

                Text(
                    textAlign = TextAlign.Justify,
                    text = "${favoriteTeam.venue.name} | Kapasitesi: ${favoriteTeam.venue.capacity}",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }



    }

}


@Preview
@Composable
private fun favoriteTeamSummaryPreview(){
    FootballAppTheme {
        Surface {
            FavoriteTeamSummaryView(MockData.teamUiModel)

        }
    }
}


