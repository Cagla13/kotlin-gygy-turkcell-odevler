package com.example.kotlin

import com.example.kotlin.models.*

fun main() {

   
       var house1: House = House(
        "İstanbul",
        "Daire",
        2010,
        "Satılık",
        3,
        5,
         true
    )

    house1.publish()
    println("Şehir: ${house1.city}")
     println("Ev yaşı: ${house1.age()}")
    println("Eski mi?: ${house1.isOld()}")

    house1.setYear(2020)
    println("Yeni yıl: ${house1.getYear()}")

     house1.build()
     house1.ready()
    house1.info()

    println("")

   
    var rental: RentalHouse = RentalHouse(
        "Ankara",
        "2+1",
        2015,
        2,
        3,
        false
    )

    rental.publish()
    rental.rent()
    rental.info()

    println("")

    
    var sale: SaleHouse = SaleHouse(
        "İzmir",
        "Villa",
        2018,
        5,
        2,
        true
    )

    sale.publish()
    sale.sell()
    sale.info()

    println("")

    
    var location = Location("İstanbul", "Kadıköy")
    location.showLocation()

    var type = HouseType("Lüks Villa")
    type.showType()

    var agent = RealEstateAgent("Mehmet Emlak")

    var house2 = SaleHouse(
        "Bursa",
        "Villa",
        2012,
        4,
        1,
        false
    )

    agent.listHouse(house2)

    println("")

    var name: String = "CALA"
    println("Merhaba $name")
}