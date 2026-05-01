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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Psychology
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.recipesfiap.model.DemandForecast
import br.com.fiap.recipesfiap.ui.theme.MediBackground
import br.com.fiap.recipesfiap.ui.theme.MediBlue
import br.com.fiap.recipesfiap.ui.theme.MediCardBg
import br.com.fiap.recipesfiap.ui.theme.MediError
import br.com.fiap.recipesfiap.ui.theme.MediIconBg
import br.com.fiap.recipesfiap.ui.theme.MediPrimary
import br.com.fiap.recipesfiap.ui.theme.MediSubtext
import br.com.fiap.recipesfiap.ui.theme.MediSuccess
import br.com.fiap.recipesfiap.ui.theme.MediSurface

private val forecast7Days = listOf(
    DemandForecast("Seg", 0.65f),
    DemandForecast("Ter", 0.80f),
    DemandForecast("Qua", 0.55f),
    DemandForecast("Qui", 0.95f),
    DemandForecast("Sex", 0.70f),
    DemandForecast("Sáb", 1.0f, true),
    DemandForecast("Dom", 0.60f)
)

data class ForecastItem(val name: String, val prediction: String, val confidence: Float, val recommendation: String)

private val forecastItems = listOf(
    ForecastItem("Soro Fisiológico 500ml", "+320 unid. em 7 dias", 0.91f, "Fazer pedido urgente"),
    ForecastItem("Luvas P (caixa)", "+15 caixas em 7 dias", 0.85f, "Repor em 3 dias"),
    ForecastItem("Seringa 10ml", "+120 unid. em 7 dias", 0.78f, "Estoque adequado"),
    ForecastItem("Máscara Cirúrgica", "+20 caixas em 7 dias", 0.82f, "Repor em 5 dias"),
    ForecastItem("Dipirona Sódica 500mg", "+500 comprimidos em 7 dias", 0.74f, "Estoque adequado")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrevisaoIAScreen(navController: NavController) {
    Scaffold(
        containerColor = MediBackground,
        topBar = {
            TopAppBar(
                title = { Text("Previsão IA", color = Color.White, fontWeight = FontWeight.Bold) },
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
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Spacer(modifier = Modifier.height(4.dp))

            // Header IA
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .background(MediCardBg)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(MediIconBg)
                        .padding(10.dp)
                ) {
                    Icon(Icons.Default.Psychology, contentDescription = null, tint = MediPrimary, modifier = Modifier.padding(2.dp))
                }
                Column {
                    Text("Previsão de Demanda", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    Text("Modelo treinado com histórico de 12 meses", fontSize = 12.sp, color = MediSubtext)
                }
            }

            // Gráfico 7 dias
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .background(MediCardBg)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text("Demanda prevista — próximos 7 dias", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
                val maxHeight = 100.dp
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(maxHeight + 24.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.Bottom
                ) {
                    forecast7Days.forEach { day ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Bottom,
                            modifier = Modifier.weight(1f)
                        ) {
                            Box(
                                modifier = Modifier
                                    .width(18.dp)
                                    .height(maxHeight * day.demandPercent)
                                    .clip(RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp))
                                    .background(
                                        if (day.isHighDemand) MediError
                                        else MediPrimary.copy(alpha = 0.5f + day.demandPercent * 0.5f)
                                    )
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = day.dayLabel, fontSize = 10.sp, color = MediSubtext)
                        }
                    }
                }
            }

            // Previsões por item
            Text("Previsões por insumo", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
            forecastItems.forEach { item ->
                ForecastItemCard(item)
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun ForecastItemCard(item: ForecastItem) {
    val confidenceColor = when {
        item.confidence >= 0.9f -> MediSuccess
        item.confidence >= 0.75f -> MediBlue
        else -> MediSubtext
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(MediCardBg)
            .padding(14.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(item.name, fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = Color.White, modifier = Modifier.weight(1f))
            Text("${(item.confidence * 100).toInt()}% confiança", fontSize = 11.sp, color = confidenceColor)
        }
        Text(item.prediction, fontSize = 12.sp, color = MediSubtext)
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(6.dp))
                .background(MediPrimary.copy(alpha = 0.15f))
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Text("💡 ${item.recommendation}", fontSize = 12.sp, color = MediPrimary)
        }
    }
}
