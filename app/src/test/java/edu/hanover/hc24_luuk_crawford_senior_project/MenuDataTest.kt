package edu.hanover.hc24_luuk_crawford_senior_project

import edu.hanover.hc24_luuk_crawford_senior_project.data.Customization
import edu.hanover.hc24_luuk_crawford_senior_project.data.MenuData
import edu.hanover.hc24_luuk_crawford_senior_project.data.MenuItem
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test

class MenuDataTest {

    @Before
    fun doBefore(){
        MenuData.clearMenuContents()
    }

    @Test
    fun getEmptyMenuData(){
        val emptyMenuData = MenuData(mutableListOf<MenuItem>(), mutableMapOf<String, Customization>(),mutableMapOf<Long, MenuItem>())

        val menuData = MenuData.get()
        assertEquals(emptyMenuData,menuData)
    }

    @Test
    fun addMenuItem(){
        val emptyMenuData = MenuData(mutableListOf<MenuItem>(), mutableMapOf<String, Customization>(),mutableMapOf<Long, MenuItem>())
        assertEquals(emptyMenuData,MenuData.get())
        val exampleItem = MenuItem()
        MenuData.addMenuItem(exampleItem)
        assertNotEquals(emptyMenuData,MenuData.get())
    }

    @Test
    fun clearMenu(){
        MenuData.setItemTypeToCustomization("itemType", Customization(mutableListOf("side1"), mutableListOf("topping1")))
        MenuData.clearMenuContents()

        val emptyMenuData = MenuData(mutableListOf<MenuItem>(), mutableMapOf<String, Customization>(),mutableMapOf<Long, MenuItem>())
        assertEquals(emptyMenuData,MenuData.get())
    }

    @Test
    fun addMenuOptionTest(){
        val itemType = "blt"
        val customization = Customization(mutableListOf("side1","side2"), mutableListOf("topping1","topping2"))

        val expectedMap = mutableMapOf<String,Customization>()
        assertEquals(expectedMap,MenuData.get().customizationOptions)

        MenuData.setItemTypeToCustomization(itemType, customization)
        expectedMap[itemType] = customization
        assertEquals(expectedMap, MenuData.get().customizationOptions)

        val bltToppings = MenuData.get().customizationOptions.get(itemType)!!.toppings
        val bltSides = MenuData.get().customizationOptions.get(itemType)!!.sides
        assertEquals(mutableListOf("topping1","topping2"), bltToppings)
        assertEquals(mutableListOf("side1","side2"), bltSides)
    }

    @Test
    fun getCustomizationTypeOfItemIDTest(){
        val defaultItem = MenuItem()
        MenuData.addMenuItem(defaultItem)
        assertEquals("default type", MenuData.getCustomizationTypeOfItemID(defaultItem.id))

        val customItem = MenuItem("burger", 325, "custom3", "https something")
        MenuData.addMenuItem(customItem)
        assertEquals("custom3",MenuData.getCustomizationTypeOfItemID(customItem.id))

        assertEquals("default type", MenuData.getCustomizationTypeOfItemID(defaultItem.id))
    }

    @Test
    fun getCustomizationOptionsOfItemIDTest(){
        val customizationBurger = Customization(mutableListOf("side1","side2"), mutableListOf("toppingA", "toppingB"))
        MenuData.setItemTypeToCustomization("burgerType", customizationBurger)
        val customItemBurger = MenuItem("burger", 325, "burgerType", "https something")
        MenuData.addMenuItem(customItemBurger)

        val customizationPotato = Customization(mutableListOf("side3","side5"), mutableListOf("toppingX", "toppingY"))
        MenuData.setItemTypeToCustomization("customPotato", customizationPotato)
        val customItemPotato = MenuItem("Potato", 220, "customPotato", "https")
        MenuData.addMenuItem(customItemPotato)

        val burgerOptionsRecieved = MenuData.getCustomizationOptionsOfItemID(customItemBurger.id)
        val potatoOptionsRecieved = MenuData.getCustomizationOptionsOfItemID(220L)

        assertEquals(customizationBurger,burgerOptionsRecieved)
        assertEquals(customizationPotato, potatoOptionsRecieved)

        assertNotEquals(customizationBurger, potatoOptionsRecieved)
        assertEquals(customizationBurger.toppings.get(0), burgerOptionsRecieved.toppings.get(0))
    }

    @Test
    fun getMenuItemFromItemIDTest(){
        val customItemBurger = MenuItem("burger", 325, "burgerType", "https something")
        MenuData.addMenuItem(customItemBurger)

        val customItemPotato = MenuItem("Potato", 220, "customPotato", "https")


        val recievedItemBurger = MenuData.getMenuItemFromItemID(325)
        assertEquals(customItemBurger, recievedItemBurger)

        assertNotEquals(customItemPotato, recievedItemBurger)
    }

}