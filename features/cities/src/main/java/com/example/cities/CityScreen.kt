package com.example.cities

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonDefaults.elevation
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.cities.items.CityItemRow
import com.example.core.models.Icon

@Composable
fun CitiesScreen(viewModel: CityViewModel) {
    val cities by viewModel.citiesList.collectAsState()
    CitiesMain(cities = cities)
}

@Composable
fun CitiesMain(
    cities: List<CityElement>,
    modifier: Modifier = Modifier
) {
    ConstraintLayout {
        val (mainScreen, bottomPanel) = createRefs()

        Column(modifier = modifier.constrainAs(mainScreen) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }) {
            Toolbar(modifier)
            CitiesList(cities = cities, modifier)
        }

        BottomPanel(modifier.constrainAs(bottomPanel) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom)
            width = Dimension.fillToConstraints
        })
    }
}

@Composable
fun Toolbar(modifier: Modifier) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(all = 16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.back),
            contentDescription = "Back",
            modifier = modifier
                .width(32.dp)
                .height(32.dp)
                .clip(shape = RoundedCornerShape(8.dp))
                .background(color = colorResource(id = R.color.athens_gray))
                .padding(all = 5.dp)
        )

        Text(
            text = stringResource(id = R.string.my_city),
            modifier = modifier
                .weight(1F)
                .align(Alignment.CenterVertically),
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Image(
            painter = painterResource(id = R.drawable.theme_switcher),
            contentDescription = "Theme",
            modifier = modifier
                .width(32.dp)
                .height(32.dp)
                .clip(shape = RoundedCornerShape(8.dp))
                .background(color = colorResource(id = R.color.athens_gray))
                .padding(all = 5.dp)
        )
    }
}

private fun listColorGradient() = listOf(Color.Transparent, Color.White)

@Composable
fun BottomPanel(modifier: Modifier) {
    Row(
        modifier = modifier
            .height(193.dp)
            .background(
                Brush.verticalGradient(
                    0F to Color.Transparent,
                    .6F to Color.White.copy(alpha = 1F),
                    1F to Color.White.copy(alpha = 1F)
                )
            ),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center
    ) {

        Row(
            modifier = modifier.padding(bottom = 30.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { /*TODO*/ },
                shape = RoundedCornerShape(12.dp),
                elevation = elevation(
                    defaultElevation = 0.dp,
                    pressedElevation = 0.dp
                ),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(id = R.color.dodger_blue),
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .height(40.dp)
                    .padding(end = 8.dp)
            ) {
                Text(text = stringResource(id = R.string.add_city), fontSize = 14.sp)

            }

            Image(
                painter = painterResource(id = R.drawable.location),
                contentDescription = "Back",
                modifier = modifier
                    .width(40.dp)
                    .height(40.dp)
                    .clip(shape = RoundedCornerShape(12.dp))
                    .background(color = colorResource(id = R.color.athens_gray))
                    .padding(all = 5.dp)
            )
        }
    }
}

@Composable
fun CitiesList(cities: List<CityElement>, modifier: Modifier) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(cities) {
            CityItemRow(it)
        }
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
    showBackground = true
)
@Composable
fun previewScreen() {
    CitiesMain(
        cities = listOf(
            CityElement("Тамбов", 10, 30, Icon.CLOUD),
            CityElement("Москва", 10, 30, Icon.CLOUD),
            CityElement("Тамбов", 10, 30, Icon.CLOUD),
            CityElement("Москва", 10, 30, Icon.CLOUD),
            CityElement("Тамбов", 10, 30, Icon.CLOUD),
            CityElement("Москва", 10, 30, Icon.CLOUD),
            CityElement("Тамбов", 10, 30, Icon.CLOUD),
            CityElement("Москва", 10, 30, Icon.CLOUD),
            CityElement("Тамбов", 10, 30, Icon.CLOUD),
            CityElement("Москва", 10, 30, Icon.CLOUD),
            CityElement("Тамбов", 10, 30, Icon.CLOUD),
            CityElement("Москва", 10, 30, Icon.CLOUD),
            CityElement("Тамбов", 10, 30, Icon.CLOUD),
            CityElement("Москва", 10, 30, Icon.CLOUD),
            CityElement("Тамбов", 10, 30, Icon.CLOUD),
            CityElement("Москва", 10, 30, Icon.CLOUD),
            CityElement("Тамбов", 10, 30, Icon.CLOUD),
            CityElement("Москва", 10, 30, Icon.CLOUD),
            CityElement("Тамбов", 10, 30, Icon.CLOUD),
            CityElement("Москва", 10, 30, Icon.CLOUD),
            CityElement("Тамбов", 10, 30, Icon.CLOUD),
            CityElement("Москва", 10, 30, Icon.CLOUD)
        )
    )
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun previewScreenDark() {
    CitiesMain(
        cities = listOf(
            CityElement("Тамбов", 10, 30, Icon.CLOUD),
            CityElement("Москва", 10, 30, Icon.CLOUD)
        )
    )
}