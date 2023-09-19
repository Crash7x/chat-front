package com.example.weather

import android.util.Log
import android.widget.EditText
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.weather.usecases.common.DailyWeather
import com.example.weather.usecases.common.TodayWeather
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun DailyWeatherScreen(viewModel: DailyWeatherViewModel, modifier: Modifier = Modifier) {
    val headerList by viewModel.header.collectAsState()
    val dailyList by viewModel.dailyWeather.collectAsState()

    LazyColumn(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .padding(top = 20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(headerList) {
            HeaderItemRow(item = it, modifier = modifier)
        }

        items(dailyList) {
            DailyItemRow(item = it, modifier = modifier)
        }
    }
}

@Composable
fun ChatScreen(viewModel: DailyWeatherViewModel, modifier: Modifier = Modifier) {
    val headerList by viewModel.chatMessage.collectAsState()

    LazyColumn(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .padding(top = 20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(headerList) {
            ChatItemRow(item = it, modifier = modifier)
        }
    }
}


@Composable
fun ChatItemRow(item: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color.Blue)
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        Text(text = item, color = Color.White)
    }
}