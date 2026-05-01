package br.com.fiap.recipesfiap.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.recipesfiap.ui.theme.*

data class IAPrediction(val item: String, val current: Int, val predicted: Int, val unit: String)

private val predictions = listOf(
    IAPrediction("Soro Fisiológico 0,9%", 15, 120, "unid"),
    IAPrediction("Luvas P", 200, 450, "pares"),
    IAPrediction("Seringa 10ml", 500, 800, "unid"),
    IAPrediction("Omeprazol 20mg", 5, 80, "caixas"),
    IAPrediction("Álcool 70%", 80, 150, "litros"),
    IAPrediction("Dipirona 500mg", 30, 120, "frascos")
)

private val weekBars = listOf(80, 120, 95, 140, 110, 160, 130)
private val weekDays = listOf("Seg", "Ter", "Qua", "Qui", "Sex", "Sáb", "Dom")

@Composable
fun IAScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MediBackground)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text("Inteligência Artificial", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = MediOnBackground)
            Text("Previsão de demanda para os próximos 7 dias", fontSize = 13.sp, color = MediOnSurface)
        }
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MediCard, RoundedCornerShape(12.dp))
                    .padding(16.dp)
            ) {
                Column {
                    Text("Consumo previsto — 7 dias", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = MediOnBackground)
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        weekBars.forEachIndexed { index, height ->
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Box(
                                    modifier = Modifier
                                        .width(28.dp)
                                        .height(height.dp)
                                        .background(MediSecondary, RoundedCornerShape(4.dp))
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(weekDays[index], fontSize = 10.sp, color = MediOnSurface)
                            }
                        }
                    }
                }
            }
        }
        item {
            Text("Previsões por insumo", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = MediOnBackground)
        }
        items(predictions) { pred ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MediCard, RoundedCornerShape(10.dp))
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(pred.item, fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = MediOnBackground)
                    Text("Atual: ${pred.current} ${pred.unit}", fontSize = 12.sp, color = MediOnSurface)
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text("Previsto", fontSize = 11.sp, color = MediOnSurface)
                    Text("${pred.predicted} ${pred.unit}", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = MediPrimaryVariant)
                }
            }
        }
    }
}
