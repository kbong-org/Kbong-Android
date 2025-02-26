package com.project.kbong.navigation

import com.project.kbong.R


enum class BottomNaviDestination(
    val selectedIcon: Int,
    val unselectedIcon: Int,
    val routeName: Int,
) {
    CALENDER(
        selectedIcon = R.drawable.bottom_calender_select,
        unselectedIcon = R.drawable.bottom_calender_unselect,
        routeName = R.string.calender,
    ),
    MY(
        selectedIcon = R.drawable.bottom_mypage_select,
        unselectedIcon = R.drawable.bottom_mypage_unselect,
        routeName = R.string.my,
    ),
}
