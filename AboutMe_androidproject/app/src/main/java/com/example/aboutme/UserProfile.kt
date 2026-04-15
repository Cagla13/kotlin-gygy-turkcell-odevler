package com.example.aboutme

import androidx.compose.ui.graphics.Color

data class UserProfile(
    val name: String,
    val university: String,
    val department: String,
    val profession: String,
    val favoriteColor: Color,
    val skills: List<String>,
    val artists: List<String>,
    val experiences: List<String>,
    val funFacts: List<String>,
    val startupNote: String
)

val myProfile = UserProfile(
    name = "Çağla Kandemir",
    university = "İstanbul Aydın Üniversitesi",
    department = "Yazılım Geliştirme (Son Sınıf + 1 Yıl Hazırlık)",
    profession = "Startup Founder & Lead Developer",
    favoriteColor = Color(0xFF2E7D32),
    skills = listOf("Kotlin", "Java", "C", "C++", "Python", "HTML", "Karakalem"),
    artists = listOf("Ajda Pekkan", "Miley Cyrus", "Charlotte Cardin", "Mae Muller", "Arctic Monkeys", "Dove Cameron"),
    experiences = listOf("Turkcell ai developer", "Canada Bluesense iş ve yazılım geliştirici", "YTÜ Teknopark -iş ve yazılım geliştirici", "Teknofest KKTC-pm", "TÜBİTAK 1512-pm","eybey-yazılım geliştirici", "çocuk akademi-grafiker"),
    funFacts = listOf(
        "yüzmeyi boğularak öğrendim , yaratıcılığım ve empati düzeyim çok yüksek olduğu için 3 kere ıq testine girdim ama tabi süper zeka çıkmadım ortalamanın üstü eq a sahip olduğumu öğrendik ,gördüğüm mimikleri veya duyduğum sesleri birebir taklit edebilirim , 3 badminton şampiyonluğum var ,elastik biriyim parmaklarımı bileğime zorlanmadan değdirebilirim , osmanlıca okuyabilir ve yazabilirim ."
    ),
    startupNote = "13/12/2002 doğumluyum ,yay burcuyum  ve burçlara inanırım ,Şu an anlaşmalı olduğumuz teknoparkla kuluçka dönemindeyiz. Hisse karşılığı hibe almayı hedefliyoruz."
)