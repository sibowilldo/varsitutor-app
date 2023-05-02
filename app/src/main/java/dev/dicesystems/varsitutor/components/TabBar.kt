package dev.dicesystems.varsitutor.components

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.TabPosition
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import dev.dicesystems.varsitutor.viewmodels.MainViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeTabBar(navController: NavController,
               viewModel: MainViewModel
) {
    val pagerState = rememberPagerState()
    val pages = listOf("ALL", "MY APPLICATIONS")
    val indicator = @Composable { tabPositions: List<TabPosition> ->
        CustomIndicator(tabPositions, pagerState)
    }
    Column() {
        ScrollableTabRow(
            backgroundColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.04f),
            modifier = Modifier
                .height(50.dp)
                .clip(shape = RoundedCornerShape(16.dp))
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(16.dp)
                ),
            selectedTabIndex = pagerState.currentPage,
            indicator = indicator
        ) {

            val coroutineScope = rememberCoroutineScope()
            pages.forEachIndexed { index, title ->
                Tab(
                    modifier = Modifier
                        .zIndex(6f)
                        .clip(shape = RoundedCornerShape(16.dp)),
                    text = { Text(text = title, color = MaterialTheme.colorScheme.onBackground) },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                )
            }
        }

        HorizontalPager(
            contentPadding = PaddingValues(top = 30.dp),
            modifier = Modifier.fillMaxSize(),
            count = pages.size,
            state = pagerState,
        ) { page ->
            when (page) {
                0 -> {
                    VacancyList(navController = navController, viewModel=viewModel)
                }

                1 -> {
                    ApplicationList(navController = navController, viewModel = viewModel)
                }
            }
        }
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
private fun CustomIndicator(tabPositions: List<TabPosition>, pagerState: PagerState) {
    val transition = updateTransition(pagerState.currentPage, label = "")
    val indicatorStart by transition.animateDp(
        transitionSpec = {
            if (initialState < targetState) {
                spring(dampingRatio = 1f, stiffness = 50f)
            } else {
                spring(dampingRatio = 1f, stiffness = 1000f)
            }
        }, label = ""
    ) {
        tabPositions[it].left
    }

    val indicatorEnd by transition.animateDp(
        transitionSpec = {
            if (initialState < targetState) {
                spring(dampingRatio = 1f, stiffness = 1000f)
            } else {
                spring(dampingRatio = 1f, stiffness = 50f)
            }
        }, label = ""
    ) {
        tabPositions[it].right
    }

    Box(
        Modifier
            .offset(x = indicatorStart)
            .wrapContentSize(align = Alignment.BottomStart)
            .width(indicatorEnd - indicatorStart)
            .padding(2.dp)
            .fillMaxSize()
            .background(color = Color(0xFFFDAE9D), RoundedCornerShape(30))
            .zIndex(1f)
    )
}