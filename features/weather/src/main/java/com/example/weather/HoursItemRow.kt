package com.example.weather

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather.usecases.common.TimeWeather
import java.text.SimpleDateFormat
import java.util.Locale

private val formatterDate = SimpleDateFormat("HH:mm", Locale("ru"))

@Composable
fun HoursItemRow(item: TimeWeather, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(colorResource(id = R.color.athens_gray_dark)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = formatterDate.format(item.timeHours).toString(),
            fontSize = 14.sp,
            color = colorResource(id = R.color.manatee),
            modifier = modifier
                .padding(top = 12.dp)
                .padding(horizontal = 17.dp)
        )
        Image(
            painter = painterResource(item.iconHours.image),
            contentDescription = null,
            modifier = modifier
                .padding(vertical = 5.dp, horizontal = 21.dp)
                .width(32.dp)
                .height(32.dp)
        )
        Text(
            text = stringResource(id = R.string.temp, item.tempHours),
            fontSize = 16.sp,
            color = colorResource(id = R.color.shark),
            modifier = modifier.padding(bottom = 12.dp)
        )
    }
}

