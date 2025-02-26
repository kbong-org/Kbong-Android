package com.project.kbong

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.project.kbong.designsystem.theme.KBongGrayscaleGray2
import com.project.kbong.designsystem.theme.KBongGrayscaleGray4
import com.project.kbong.designsystem.theme.KBongGrayscaleGray9
import com.project.kbong.designsystem.theme.KBongTypography
import com.project.kbong.navigation.BottomNaviDestination
import com.project.kbong.navigation.KBongAppState
import com.project.kbong.navigation.MainNavHost
import com.project.kbong.navigation.rememberKBongAppState
import com.project.presentation.navigation.NavigationRoute

@Composable
fun KBongApp(
    appState: KBongAppState = rememberKBongAppState(),
) {
    Scaffold(
        containerColor = Color.Transparent,
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, KBongGrayscaleGray2, RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                    .background(Color.White)
            ) {
                NavigationBar(
                    modifier = Modifier.fillMaxWidth(),
                    containerColor =  Color.Transparent,
                    content = {
                        Row {
                            appState.bottomBarDestination.forEach { destination ->
                                val isSelected =
                                    appState.currentDestination.isSelectedBottomNaviPage(destination)
                                NavigationBarItem(
                                    selected = isSelected,
                                    onClick = {
                                        appState.navigateToBottomNavigationDestination(destination)
                                    },
                                    icon = {
                                        Icon(
                                            painter = if (isSelected) {
                                                painterResource(id = destination.selectedIcon)
                                            } else {
                                                painterResource(
                                                    id = destination.unselectedIcon,
                                                )
                                            },
                                            contentDescription = null,
                                            tint = Color.Unspecified
                                        )
                                    },
                                    colors = NavigationBarItemDefaults.colors(
                                        selectedIconColor = KBongGrayscaleGray9,
                                        unselectedIconColor = KBongGrayscaleGray4,
                                        indicatorColor = Color.Transparent,
                                    ),
                                    label = {
                                        Text(
                                            text = stringResource(destination.routeName),
                                            style = KBongTypography.Caption1Medium.copy(fontSize = 12.sp),
                                            color = if (isSelected) KBongGrayscaleGray9 else KBongGrayscaleGray4,
                                        )
                                    },
                                    alwaysShowLabel = true,
                                )
                            }
                        }
                    },
                )
            }
        }
    ) { innerPadding ->
        MainNavHost(
            appState = appState,
            modifier = Modifier.padding(innerPadding),
            startDestination = NavigationRoute.CalenderScreen.route
        )
    }
}

private fun NavDestination?.isSelectedBottomNaviPage(destination: BottomNaviDestination): Boolean {
    val result = this?.hierarchy?.any {
        val matches = it.route?.contains(destination.name, true) ?: false
        matches
    } ?: false

    return result
}

