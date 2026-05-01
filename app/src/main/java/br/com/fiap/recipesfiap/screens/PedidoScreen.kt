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
import br.com.fiap.recipesfiap.ui.theme.*

data class Order(val number: String, val item: String, val qty: String, val status: String, val date: String)

private val orders = listOf(
    Order("#0043", "Soro Fisiológico 0,9%", "200 unid", "PENDING", "15/01/2025"),
    Order("#0042", "Omeprazol 20mg", "100 caixas", "APPROVED", "14/01/2025"),
    Order("#0041", "Luvas P", "500 pares", "DELAYED", "13/01/2025"),
    Order("#0040", "Álcool 70%", "150 litros", "DELIVERED", "12/01/2025"),
    Order("#0039", "Seringa 10ml", "1000 unid", "DELIVERED", "11/01/2025"),
    Order("#0038", "Dipirona 500mg", "200 frascos", "DELIVERED", "10/01/2025")
)

@Composable
fun PedidoScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MediBackground)
            .padding(16.dp)
    ) {
        Text("Pedidos", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = MediOnBackground)
        Text("Histórico e status de pedidos", fontSize = 13.sp, color = MediOnSurface)
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items(orders) { order ->
                val statusColor = when (order.status) {
                    "DELAYED" -> MediError
                    "DELIVERED" -> MediSuccess
                    "APPROVED" -> MediSecondary
                    else -> MediWarning
                }
                val statusLabel = when (order.status) {
                    "DELAYED" -> "Atrasado"
                    "DELIVERED" -> "Entregue"
                    "APPROVED" -> "Aprovado"
                    else -> "Pendente"
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
                        Text("Pedido ${order.number}", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = MediOnBackground)
                        Text(order.item, fontSize = 12.sp, color = MediOnSurface)
                        Text("${order.qty} · ${order.date}", fontSize = 11.sp, color = MediOnSurface.copy(alpha = 0.7f))
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
