package com.example.weather

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.models.Icon
import com.example.weather.usecases.common.TodayWeather
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private val formatterDate = SimpleDateFormat("dd MMMM, E", Locale("ru"))

@Composable
fun HeaderItemRow(item: TodayWeather, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .paint(
                painter = painterResource(R.drawable.ic_daily_bg),
                contentScale = ContentScale.FillBounds
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.today, formatterDate.format(item.date).toString()),
            fontSize = 14.sp,
            color = Color.White,
            modifier = modifier.padding(top = 16.dp)
        )
        Image(
            painter = painterResource(item.icon.image),
            contentDescription = null,
            modifier = modifier
                .padding(top = 12.dp)
                .width(120.dp)
                .height(120.dp)
        )
        Text(
            text = stringResource(R.string.temp, item.temp),
            fontSize = 48.sp,
            color = Color.White,
            modifier = modifier.padding(top = 6.dp)
        )
        Text(
            text = stringResource(
                id = R.string.feels_temp_and_description,
                item.description,
                item.feelTemp
            ),
            fontSize = 14.sp,
            color = Color.White,
            modifier = modifier.padding(top = 2.dp, bottom = 24.dp)
        )
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
    showBackground = true
)
@Composable
fun previewScreen() {
    HeaderItemRow(item = TodayWeather(Date(), Icon.CLOUD, 32, 30, "Облачно"))
}