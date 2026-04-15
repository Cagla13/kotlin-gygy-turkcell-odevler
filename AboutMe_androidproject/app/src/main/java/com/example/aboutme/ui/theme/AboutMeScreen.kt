package com.example.aboutme.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aboutme.R
import com.example.aboutme.UserProfile

@Composable
fun AboutMeScreen(user: UserProfile) {
    val scrollState = rememberScrollState()


    Box(modifier = Modifier.fillMaxSize()) {


        Image(
            painter = painterResource(id = R.drawable.arka_plan),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            alpha = 0.3f
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .border(3.dp, user.favoriteColor, CircleShape)
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profil_resmi),
                    contentDescription = "Profil Fotoğrafı",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(12.dp))
            Text(user.name, fontSize = 26.sp, fontWeight = FontWeight.ExtraBold, color = user.favoriteColor)
            Text(user.profession, fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color.DarkGray)

            Spacer(modifier = Modifier.height(20.dp))


            InfoCard(
                title = "Eğitim & kişisel bilgi ve mevcut Durum",
                content = "${user.university}\n${user.department}\n\n ${user.startupNote}",
                color = user.favoriteColor
            )

            SectionTitle("Yetenekler & Sanat")
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(user.skills) { skill ->
                    FilterChip(
                        selected = true,
                        onClick = {},
                        label = { Text(skill) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = user.favoriteColor,
                            selectedLabelColor = Color.White
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))


            SectionTitle("Yarışmalar & Projeler")
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.85f))
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    user.experiences.forEach { exp ->
                        Text("• $exp", fontSize = 14.sp, modifier = Modifier.padding(vertical = 2.dp))
                    }
                }
            }


            SectionTitle("Bilinmeyenler & Süper Güçler")
            user.funFacts.forEach { fact ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.Star, contentDescription = null, tint = user.favoriteColor, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(fact, fontSize = 14.sp, lineHeight = 20.sp)
                }
            }


            SectionTitle("Favori Sanatçılar")
            LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                items(user.artists) { artist ->
                    Card(
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.9f))
                    ) {
                        Text(artist, modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp), fontSize = 12.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black
    )
}

@Composable
fun InfoCard(title: String, content: String, color: Color) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, color.copy(alpha = 0.3f)),
        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.85f))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(title, fontWeight = FontWeight.Bold, color = color)
            Spacer(modifier = Modifier.height(4.dp))
            Text(content, fontSize = 14.sp, lineHeight = 20.sp)
        }
    }
}