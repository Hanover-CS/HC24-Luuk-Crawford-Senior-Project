package edu.hanover.hc24_luuk_crawford_senior_project.data

/**
 * All the data the menu has.
 * @param menuItemList list of items on menu.
 * @param customizationOptions map of item TYPE (string) to customization associated to it.
 * @param idToMenuItem map of item id to MenuItem associated to it.
 */
data class MenuData(
    var menuItemList: MutableList<MenuItem>,
    var customizationOptions: MutableMap<String, Customization>,
    var idToMenuItem: MutableMap<Long, MenuItem> ) {
    companion object {
        private var myMenu = MenuData(mutableListOf<MenuItem>(), mutableMapOf<String, Customization>(),mutableMapOf<Long, MenuItem>())

        fun get(): MenuData {
            return myMenu
        }

        fun clearMenuContents() {
            myMenu = MenuData(mutableListOf<MenuItem>(), mutableMapOf<String, Customization>(), mutableMapOf<Long, MenuItem>())
        }

        fun addMenuItem(menuItem: MenuItem) {
            myMenu.menuItemList.add(menuItem)
            myMenu.idToMenuItem[menuItem.id] = menuItem
        }

        /**
         * add name of item and its available toppings to menu
         * @param itemType name of item type (string)
         * @param customization avialable toppings (Customization type)
         * */
        fun setItemTypeToCustomization(itemType: String, customization: Customization){
            myMenu.customizationOptions[itemType] = customization
        }

        fun getCustomizationTypeOfItemID(id: Long): String{
            return myMenu.idToMenuItem[id]!!.customizationType
        }

        fun getCustomizationOptionsOfItemID(id: Long): Customization{
            val customizationType = getCustomizationTypeOfItemID(id)
            return myMenu.customizationOptions[customizationType]!!
        }

        fun getMenuItemFromItemID(id: Long): MenuItem{
            return myMenu.idToMenuItem[id]!!
        }
    }
}