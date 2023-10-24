package edu.hanover.hc24_luuk_crawford_senior_project

data class MenuData(
    var menuItemList: MutableList<MenuItem>,
    var customizationOptions: MutableMap<String, Customization>
) {
    companion object {
        private var myMenu = MenuData(mutableListOf<MenuItem>(), mutableMapOf<String, Customization>())

        fun set(
            menuItemList: MutableList<MenuItem>,
            customizationOptions: MutableMap<String, Customization>
        ) {
            myMenu = MenuData(menuItemList,customizationOptions)
        }
        fun get(): MenuData {
            return myMenu
        }

        fun clear() {
            myMenu = MenuData(mutableListOf<MenuItem>(), mutableMapOf<String, Customization>())
        }

        fun addMenuItem(menuItem: MenuItem) {
            myMenu.menuItemList.add(menuItem)

        }
    }
}
/*
fun getMenu(): MenuData {
    return myMenu
}
fun setMenu(newMenuList: MutableList<MenuItem>, newCustomizations: MutableMap<String, Customization>) {
    myMenu.menuItemList = newMenuList
    myMenu.customizationOptions = newCustomizations
}*/