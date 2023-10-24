package edu.hanover.hc24_luuk_crawford_senior_project

import edu.hanover.hc24_luuk_crawford_senior_project.data.MenuItem
import junit.framework.TestCase
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

internal class MenuItemTest{

    @Test
    fun test(){
        TestCase.assertEquals(3, 3)
    }

    @Test
    fun createDefaultItem(){
        val itemExample = MenuItem()
        assertEquals("default item",itemExample.name)
        assertEquals(0,itemExample.id)
        assertEquals("default type", itemExample.customizationType)
        assertEquals("https://i.imgur.com/BDa36Zp.jpeg", itemExample.imageLink)
    }
    @Test
    fun createItem(){

        val itemExample = MenuItem(
            name = "VeggieBurgerTest",
            id = 101,
            customizationType = "Burger",
            imageLink = "https://i.imgur.com/K6alfDv.jpeg"
        )
        assertEquals("VeggieBurgerTest",itemExample.name)
        assertEquals(101, itemExample.id)
        assertEquals("Burger", itemExample.customizationType)
        assertEquals("https://i.imgur.com/K6alfDv.jpeg", itemExample.imageLink)
    }


}