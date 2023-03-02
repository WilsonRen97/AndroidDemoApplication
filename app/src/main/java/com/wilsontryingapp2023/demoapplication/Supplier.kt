package com.wilsontryingapp2023.demoapplication

class Room(val name: String, val picture: Int)

class Supplier {
    private val roomNames = arrayOf(
        "The Bank",
        "The Gold Room",
        "The Green Room",
        "The Machine Room",
        "The Milk Room",
        "The Office",
        "The Pink Room",
        "The Warm Room",
        "The White Room"
    )

    private val imageIDs = intArrayOf(
        R.drawable.bank,
        R.drawable.gold_room,
        R.drawable.green_room,
        R.drawable.machine_room,
        R.drawable.milk_room,
        R.drawable.office,
        R.drawable.pink_room,
        R.drawable.warm_room,
        R.drawable.white_room
    )

    val rooms: Array<Room?> = arrayOfNulls(roomNames.size)

    init {
        for (i in 0 until roomNames.size) {
            rooms[i] = Room(roomNames[i], imageIDs[i])
        }
    }
}