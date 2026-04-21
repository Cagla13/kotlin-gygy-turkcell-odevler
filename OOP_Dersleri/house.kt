package com.example.kotlin.models


abstract class Building {

    abstract fun build()

    fun ready() {
        println("Bina kullanıma hazır.")
    }
}


open class House(
     var city: String,
     var type: String,
    private var year: Int,
    var status: String,
    var rooms: Int,
     var floor: Int,
     var isInSite: Boolean
) : Building() {

    init {
        println("House nesnesi .")
    }

     override fun build() {
        println("Ev inşa ediliyor.")
    }

      fun publish() {
        println("İlan: $city | $type | $rooms oda | $floor. kat | Site: $isInSite | Durum: $status")
    }

  
    fun getYear(): Int {
        return year
    }

     fun setYear(year: Int) {
        if (year < 1900 || year > 2026) {
            println("Geçersiz ")
            return
        }
        this.year = year
    }

     open fun info() {
        println("Bu bir evdir.")
    }

   
    fun age(): Int {
        return 2026 - year
    }

    fun isOld(): Boolean {
        return age() > 20
    }
}


class RentalHouse(
    city: String,
    type: String,
    year: Int,
    rooms: Int,
    floor: Int,
    isInSite: Boolean
) : House(city, type, year, "Kiralık", rooms, floor, isInSite) {

    fun rent() {
        println("Ev kiralandı.")
    }

    override fun info() {
        println("Bu ev kiralıktır.")
    }
}


class SaleHouse(
    city: String,
    type: String,
    year: Int,
    rooms: Int,
    floor: Int,
    isInSite: Boolean
) : House(city, type, year, "Satılık", rooms, floor, isInSite) {

    fun sell() {
        println("ev satıldı.")
    }

    override fun info() {
        println("Bu ev satılıktır.")
    }
}


class HouseType(var name: String) {
    fun showType() {
        println("ev tipi: $name")
    }
}


class Location(var city: String, var district: String) {
    fun showLocation() {
        println("konum: $city / $district")
    }
}

class RealEstateAgent(var name: String) {

    fun listHouse(house: House) {
        println("$name bir ev listeledi:")
        house.publish()
    }
}