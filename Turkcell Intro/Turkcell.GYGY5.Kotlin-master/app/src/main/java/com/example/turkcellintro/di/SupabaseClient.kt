package com.example.turkcellintro.di

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest

object SupabaseClient {
    val supabaseClient = createSupabaseClient(
        supabaseUrl = "https://fnkiiswxomlsqohgepqz.supabase.co", // local.properties
        supabaseKey = "sb_publishable_EduMJZLvOWj8pBieMywHxg_l6JVjhTM"

    ) {
        install(plugin = Postgrest)
    }
}