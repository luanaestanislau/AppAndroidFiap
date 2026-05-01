package br.com.fiap.recipesfiap.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.recipesfiap.model.AlertItem
import br.com.fiap.recipesfiap.ui.theme.*

private val allAlerts = listOf(
    AlertItem(1, "Estoque baixo — Soro Fisiológico", "Nível abaixo do mínimo crítico", "há 10 min", "WARNING"),
    AlertItem(2, "Vencimento próximo — Luvas P", "Vencimento em 3 dias", "há 1h", "WARNING"),
    AlertItem(3, "Atraso — Pedido #0041", "MedSupply com atraso de 4h", "há 2h", "CRITICAL"),
    AlertItem(4, "Estoque crítico — Omeprazol 20mg", "Apenas 5 caixas restantes", "há 3h", "CRITICAL"),
    AlertItem(5, "Entrega confirmada — Pedido #0039", "ForneceMed entregou com sucesso", "há 5h", "INFO"),
    AlertItem(6, "Estoque baixo — Álcool 70%", "80 litros — mínimo 100", "há 6h", "WARNING")
)

@Composable
fun AlertasScreen() {
    var selectedFilter by remember { mutableStateOf("TODOS") }
    val filters = listOf("TODOS", "CRITICAL", "WARNING", "INFO")

    val filtered = if (selectedFilter == "TODOS") allAlerts else allAlerts.filter { it.severity == selectedFilter }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MediBackground)
            .padding(16.dp)
    ) {
        Text("Alertas", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = MediOnBackground)
        Text("Monitoramento em tempo real", fontSize = 13.sp, color = MediOnSurface)
        Spacer(modifier = Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            filters.forEach { filter ->
                FilterChip(
                    selected = selectedFilter == filter,
                    onClick = { selectedFilter = filter },
                    label = { Text(filter, fontSize = 11.sp) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = MediPrimary,
                        selectedLabelColor = MediOnBackground
                    )
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items(filtered) { alert ->
                val severityColor = when (alert.severity) {
                    "CRITICAL" -> MediError
                    "WARNING" -> MediWarning
                    else -> MediSecondary
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MediCard, RoundedCornerShape(10.dp))
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .background(severityColor, RoundedCornerShape(50))
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(alert.title, fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = MediOnBackground)
                        Text(alert.description, fontSize = 12.sp, color = MediOnSurface)
                        Text(alert.timeAgo, fontSize = 11.sp, color = MediOnSurface.copy(alpha = 0.7f))
                    }
                }
            }
        }
    }
}
