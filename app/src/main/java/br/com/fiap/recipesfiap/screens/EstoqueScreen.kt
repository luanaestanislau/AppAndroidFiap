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
import br.com.fiap.recipesfiap.model.StockItem
import br.com.fiap.recipesfiap.ui.theme.*

private val sampleStock = listOf(
    StockItem(1, "Soro Fisiológico 0,9%", "Soluções", 15, 50, "unid", "CRITICAL", "2025-12-01"),
    StockItem(2, "Luvas P", "EPI", 200, 300, "pares", "LOW", "2025-06-30"),
    StockItem(3, "Seringa 10ml", "Descartáveis", 500, 200, "unid", "OK", "2026-03-15"),
    StockItem(4, "Álcool 70%", "Higienização", 80, 100, "litros", "LOW", "2025-09-20"),
    StockItem(5, "Curativo Transparente", "Curativos", 450, 200, "unid", "OK", "2026-01-10"),
    StockItem(6, "Omeprazol 20mg", "Medicamentos", 5, 50, "caixas", "CRITICAL", "2025-11-15"),
    StockItem(7, "Agulha 40x12", "Descartáveis", 1000, 500, "unid", "OK", "2026-05-01"),
    StockItem(8, "Dipirona 500mg", "Medicamentos", 30, 100, "frascos", "LOW", "2025-08-30")
)

@Composable
fun EstoqueScreen() {
    var search by remember { mutableStateOf("") }
    val filtered = sampleStock.filter { it.name.contains(search, ignoreCase = true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MediBackground)
            .padding(16.dp)
    ) {
        Text("Estoque", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = MediOnBackground)
        Text("Controle de insumos hospitalares", fontSize = 13.sp, color = MediOnSurface)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = search,
            onValueChange = { search = it },
            label = { Text("Buscar insumo...") },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MediPrimary,
                unfocusedBorderColor = MediOnSurface,
                focusedLabelColor = MediPrimary,
                unfocusedLabelColor = MediOnSurface,
                focusedTextColor = MediOnBackground,
                unfocusedTextColor = MediOnBackground,
                cursorColor = MediPrimary
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items(filtered) { item ->
                StockItemCard(item)
            }
        }
    }
}

@Composable
fun StockItemCard(item: StockItem) {
    val statusColor = when (item.status) {
        "CRITICAL" -> MediError
        "LOW" -> MediWarning
        else -> MediSuccess
    }
    val statusLabel = when (item.status) {
        "CRITICAL" -> "Crítico"
        "LOW" -> "Baixo"
        else -> "OK"
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
            Text(item.name, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = MediOnBackground)
            Text("${item.quantity} ${item.unit} · ${item.category}", fontSize = 12.sp, color = MediOnSurface)
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
