package com.example.weather

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.models.Icon
import com.example.weather.usecases.common.DailyWeather
import com.example.weather.usecases.common.TodayWeather
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private val formatterDate = SimpleDateFormat("dd MMMM, E", Locale("ru"))

@Composable
fun DailyItemRow(item: DailyWeather, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(colorResource(id = R.color.athens_gray))
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        DailyInfoPanel(item = item, modifier = modifier)

        Divider(color = colorResource(id = R.color.mystic), thickness = 1.dp)

        LazyRow(
            modifier = modifier.padding(top = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            items(item.hoursList) {
                HoursItemRow(item = it)
            }
        }
    }
}

@Composable
fun DailyInfoPanel(item: DailyWeather, modifier: Modifier = Modifier) {
    Row(modifier = modifier.padding(bottom = 16.dp), verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = formatterDate.format(item.date).toString(),
            fontSize = 14.sp,
            color = colorResource(id = R.color.manatee),
            modifier = modifier
                .weight(2F)
        )
        Text(
            text = stringResource(id = R.string.temp, item.minTemp),
            fontSize = 16.sp,
            color = colorResource(id = R.color.shark),
            modifier = modifier.padding(end = 8.dp)
        )
        Text(
            text = stringResource(id = R.string.temp, item.maxTemp),
            fontSize = 16.sp,
            color = colorResource(id = R.color.manatee),
            modifier = modifier.padding(end = 16.dp)
        )
        Image(
            painter = painterResource(item.icon.image),
            contentDescription = null,
            modifier = modifier
                .width(32.dp)
                .height(32.dp)
        )
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
    showBackground = true
)
@Composable
fun previewScreenDaily() {
    DailyInfoPanel(item = DailyWeather(Date(),26, 13, Icon.CLOUD, listOf()))
}