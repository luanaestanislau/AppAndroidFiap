package br.com.fiap.recipesfiap.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.recipesfiap.model.Delivery
import br.com.fiap.recipesfiap.ui.theme.*

private val deliveries = listOf(
    Delivery(1, "#0039", "ForneceMed", "Hoje 14h", "IN_ROUTE"),
    Delivery(2, "#0041", "MedSupply", "Atrasado", "DELAYED"),
    Delivery(3, "#0038", "PharmaLog", "Entregue ontem", "DELIVERED"),
    Delivery(4, "#0042", "BioPharma", "Amanhã 10h", "IN_ROUTE"),
    Delivery(5, "#0043", "MedSupply", "Em 2 dias", "IN_ROUTE"),
    Delivery(6, "#0037", "ForneceMed", "Entregue", "DELIVERED")
)

@Composable
fun LogisticaScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MediBackground)
            .padding(16.dp)
    ) {
        Text("Logística", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = MediOnBackground)
        Text("Entregas e rastreamento", fontSize = 13.sp, color = MediOnSurface)
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items(deliveries) { delivery ->
                val statusColor = when (delivery.status) {
                    "DELAYED" -> MediError
                    "DELIVERED" -> MediSuccess
                    else -> MediSecondary
                }
                val statusLabel = when (delivery.status) {
                    "DELAYED" -> "Atrasado"
                    "DELIVERED" -> "Entregue"
                    else -> "Em rota"
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MediCard, RoundedCornerShape(10.dp))
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Pedido ${delivery.orderNumber}", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = MediOnBackground)
                        Text(delivery.supplier, fontSize = 12.sp, color = MediOnSurface)
                        Text("ETA: ${delivery.eta}", fontSize = 11.sp, color = MediOnSurface.copy(alpha = 0.7f))
                    }
                    Box(
                        modifier = Modifier
                            .background(statusColor.copy(alpha = 0.2f), RoundedCornerShape(6.dp))
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Text(statusLabel, color = statusColor, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                    }
                }
            }
        }
    }
}
