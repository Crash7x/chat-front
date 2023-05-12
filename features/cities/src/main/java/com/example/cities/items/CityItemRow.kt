package com.example.cities.items

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cities.CityElement
import com.example.cities.R
import com.example.core.models.Icon


@Composable
fun CityItemRow(
    item: CityElement,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clip(shape = RoundedCornerShape(16.dp))
            .background(color = colorResource(id = R.color.athens_gray))
            .padding(horizontal = 20.dp, vertical = 16.dp)

    ) {
        Text(
            modifier = modifier
                .padding(end = 8.dp)
                .weight(2F),
            text = item.cityName,
            fontSize = 16.sp
        )

        Row {
            Text(
                modifier = modifier.padding(end = 8.dp),
                text = item.minimumTemperature.toString(),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                modifier = modifier.padding(end = 16.dp),
                text = item.maximumTemperature.toString(),
                fontSize = 16.sp,
               // color = colorResource(id = R.color.manatee),
                fontWeight = FontWeight.Bold
            )

            Image(
                modifier = modifier.width(30.dp).height(24.dp),
                painter = painterResource(id = item.weatherIcon.image),
                contentDescription = item.weatherIcon.name
            )
        }
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
    showBackground = true
)
@Composable
fun previewScreen() {
    CityItemRow(item = CityElement("Тамбов", 10, 30, Icon.CLOUD))
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun previewScreenDark() {
    CityItemRow(item = CityElement("Тамбов", 10, 30, Icon.CLOUD))
}