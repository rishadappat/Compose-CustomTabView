package ae.adpolice.tabviewsample

import ae.adpolice.tabviewsample.ui.theme.*
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TabViewSampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center) {
                        CustomTabView(modifier = Modifier.padding(20.dp)) {

                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CustomTabView(modifier: Modifier = Modifier, selectedItem: (Int)->Unit)
{
    val list = listOf(stringResource(id = R.string.kotlin), stringResource(id = R.string.java))
    var selectedIndex by remember { mutableStateOf(0) }
    Box(modifier = modifier) {
        TabRow(selectedTabIndex = selectedIndex,
            backgroundColor = TabViewBg,
            modifier = Modifier
                .height(50.dp)
                .clip(RoundedCornerShape(50))
                .border(
                    width = 2.dp,
                    color = TabViewBorder,
                    shape = RoundedCornerShape(50)
                ),
            indicator = { tabPositions: List<TabPosition> ->
                Box(
                    modifier = Modifier
                        .zIndex(1f)
                        .tabIndicatorOffset(tabPositions[selectedIndex])
                        .height(50.dp)
                        .shadow(
                            5.dp,
                            clip = true,
                            shape = RoundedCornerShape(50),
                            ambientColor = TabViewBorder,
                            spotColor = TabViewBorder,
                        )
                        .padding(horizontal = 5.dp)
                        .padding(vertical = 5.dp)
                        .background(
                            shape = RoundedCornerShape(50),
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    TabViewIndicatorTop,
                                    TabViewIndicatorBottom
                                )
                            )
                        )
                )
            }
        ) {
            list.forEachIndexed { index, text ->
                val selected = selectedIndex == index
                Tab(
                    modifier = Modifier
                        .background(Color.Transparent)
                        .clip(RoundedCornerShape(50))
                        .zIndex(2f),
                    selected = selected,
                    onClick = {
                        selectedIndex = index
                        selectedItem(selectedIndex)
                    },
                    text = {
                        Text(text = text,
                            style = TextStyle(fontWeight = if (selected) FontWeight.Bold else FontWeight.SemiBold),
                            color = if (selected) TabViewTextSelected else TabViewTextDeselected)}
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TabViewSampleTheme {
        CustomTabView {

        }
    }
}