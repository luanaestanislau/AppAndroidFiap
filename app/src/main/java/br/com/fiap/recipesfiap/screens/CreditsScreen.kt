package br.com.fiap.recipesfiap.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.recipesfiap.ui.theme.MediBackground
import br.com.fiap.recipesfiap.ui.theme.MediCardBg
import br.com.fiap.recipesfiap.ui.theme.MediIconBg
import br.com.fiap.recipesfiap.ui.theme.MediPrimary
import br.com.fiap.recipesfiap.ui.theme.MediSubtext
import br.com.fiap.recipesfiap.ui.theme.MediSurface

private data class Developer(val name: String, val role: String, val initials: String)

private val developers = listOf(
    Developer("Luana Estanislau", "Desenvolvedora Fullstack", "LE"),
    Developer("Gabriel Santos", "Desenvolvedor Android", "GS")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreditsScreen(navController: NavController) {
    Scaffold(
        containerColor = MediBackground,
        topBar = {
            TopAppBar(
                title = { Text("Créditos", color = Color.White, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MediSurface)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 28.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            // Logo
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(MediIconBg),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Assignment,
                    contentDescription = null,
                    tint = MediPrimary,
                    modifier = Modifier.size(44.dp)
                )
            }

            Text("MediStock", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color.White)
            Text(
                text = "Gestão inteligente de insumos hospitalares com IA",
                fontSize = 14.sp,
                color = MediSubtext,
                textAlign = TextAlign.Center
            )

            // Versão e ano
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(MediCardBg)
                    .padding(horizontal = 20.dp, vertical = 10.dp)
            ) {
                Text("v1.0 · 2026", fontSize = 13.sp, color = MediPrimary, fontWeight = FontWeight.SemiBold)
            }

            // Sobre
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .background(MediCardBg)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("Sobre o Projeto", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color.White)
                Text(
                    text = "O MediStock é um sistema mobile de gestão inteligente de insumos hospitalares, " +
                            "desenvolvido como projeto acadêmico na FIAP. Utiliza Inteligência Artificial para " +
                            "previsão de demanda, monitoramento em tempo real e rastreamento de entregas.",
                    fontSize = 13.sp,
                    color = MediSubtext,
                    textAlign = TextAlign.Justify
                )
            }

            // Tecnologias
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .background(MediCardBg)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("Tecnologias", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color.White)
                val techs = listOf(
                    "Android · Kotlin",
                    "Jetpack Compose + Material3",
                    "Room (persistência local)",
                    "Retrofit (integração de API)",
                    "Navigation Compose"
                )
                techs.forEach { tech ->
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(6.dp)
                                .clip(CircleShape)
                                .background(MediPrimary)
                        )
                        Text(text = tech, fontSize = 13.sp, color = MediSubtext)
                    }
                }
            }

            // Desenvolvedores
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .background(MediCardBg)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text("Desenvolvedores", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color.White)
                developers.forEach { dev ->
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .clip(CircleShape)
                                .background(MediPrimary.copy(alpha = 0.3f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(dev.initials, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = MediPrimary)
                        }
                        Column {
                            Text(dev.name, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
                            Text(dev.role, fontSize = 12.sp, color = MediSubtext)
                        }
                    }
                }
            }

            // Rodapé
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text("FIAP · Faculdade de Informática e Administração Paulista", fontSize = 11.sp, color = MediSubtext, textAlign = TextAlign.Center)
                Text("© 2026 · Todos os direitos reservados", fontSize = 11.sp, color = MediSubtext)
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
