package com.project.kbong.navigation

import com.project.kbong.R


enum class BottomNaviDestination(
    val selectedIcon: Int,
    val unselectedIcon: Int,
    val routeName: Int,
) {
    CALENDAR(
        selectedIcon = R.drawable.bottom_calendar_select,
        unselectedIcon = R.drawable.bottom_calendar_unselect,
        routeName = R.string.calendar,
    ),
    MY(
        selectedIcon = R.drawable.bottom_mypage_select,
        unselectedIcon = R.drawable.bottom_mypage_unselect,
        routeName = R.string.my,
    ),
}
