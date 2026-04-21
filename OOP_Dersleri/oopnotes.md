* # oop nesne yönelimli programlama *

oop nesne yönelimli programlama nedir bence en kısa anlatımı şu tanım olabilir gerçek hayatta gördüğüm nesneleri örnek (araba,ev,yemek vb) object yani nesne olarak gösterdiğimiz nesneleri programa modelleriz  ve bu modelleri şablonlandırdığımız class var bunlar kodlarda şu şekil gösterdim :

[house.kt](house.kt) 14-21. satırlar arası
` ` ` open class House(
    var city: String,
    var type: String,
    private var year: Int,
    var status: String,
    var rooms: Int,
    var floor: Int,
    var isInSite: Boolean ` ` `

>NOT: Ben iki dosya yapmayı tercih ettim çünkü böyle daha net anlatıp gösterebileceğimi düşündüm tek dosya daha prof durabilirdi  ama bu şekilde şöyle ayırdım 
House.kt → Classların bulunduğu  dosya
oop.kt → Nesnelerin oluşturulduğu ve çalıştığı main dosyası

ben kodumda şu örnekten yola çıktım çok fazla class seçeneği olan ev örneğini seçtim ,kodlarımda kullandıklarımda şu şekilde :
### Encapsulation → private + getter/setter
### Inheritance → : House
### Polymorphism → override fun info()  
### Abstraction → abstract class 

## Encapsulation 
ilk olarak encapsulation yani kapsülleme benim deyişimle hapsetme neden böyle diyorum çünkü girilen değer dışarıdan değiştirilmez , kapsülleme yaparken biz get ve set kullanıyoruz  kodumdaki örneği şu 

[house.kt](house.kt) 37-39. satırlar arası..
` ` ` open class House(
` ` ` fun getYear(): Int {
    return year
} ` ` `

[house.kt](house.kt) 41-47. satırlar arası.

` ` ` open class House(
` ` ` fun setYear(year: Int) {
    if (year < 1900 || year > 2026) {
        println("Geçersiz yıl")
        return
    }
    this.year = year
}` ` `

burdaki getyear veriyi güvenli şekilde dışarı aktarır içindeki hapsedilmiş private veriyi yani 

setyear ise veri bütünlüğü sağlayarak oraya girdiğim değerler arasında döndürür  if (year < 1900 || year > 2026)
 bunu oop dosyasında şöyle gösterdim 

[oop.kt](oop.kt) 23-24. satırlar arası
` ` ` open class House(
 ` ` `house1.setYear(2020)
)` ` `


## Inheritance (Kalıtım) 
Classlar birbirine bağlıdır ve bu classlar başka classtan özellik alması

RentalHouse → House class’ından türemiştir
SaleHouse → House class’ından türemiştir

Kullanımı oop.kt içinde:

var rental: RentalHouse = RentalHouse("Ankara", "2+1", 2015, 2, 3, false)

## Polymorphism (Çok biçimlilik)
Burda ovveriat kullanıp aynı classı farklı çalıştırıyoruz yine referans vermem gerekirse

[house.kt](house.kt) 49-50. satırlar arası
` ` ` open class House(
open fun info() {
    println("Bu bir evdir.")
} ` ` `

 Override edilen versiyon:

[house.kt](house.kt) 77-78. satırlar arası
` ` `override fun info() {
    println("Bu ev kiralıktır.")
}` ` `

## Abstraction (Soyutlama)  
Gereksiz fazlalıklar için kullandığımız gizleyip önemli olanları vurgulamak için yapılır


[house.kt](house.kt) 4-10. satırlar arası
` ` `abstract class Building {

    abstract fun build()

    fun ready() {
        println("Bina hazır.")
    }
}` ` `

Bu class direkt kullanılamaz, sadece kalıptır.

House class içinde implement edilir:


[house.kt](house.kt) 28-29. satırlar arası
` ` ` override fun build() {
    println("Ev inşa ediliyor.")
} ` ` `