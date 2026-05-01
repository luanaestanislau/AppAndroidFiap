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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import br.com.fiap.recipesfiap.model.AlertType
import br.com.fiap.recipesfiap.model.StockAlert
import br.com.fiap.recipesfiap.ui.theme.MediBackground
import br.com.fiap.recipesfiap.ui.theme.MediBlue
import br.com.fiap.recipesfiap.ui.theme.MediCardBg
import br.com.fiap.recipesfiap.ui.theme.MediError
import br.com.fiap.recipesfiap.ui.theme.MediSubtext
import br.com.fiap.recipesfiap.ui.theme.MediSurface
import br.com.fiap.recipesfiap.ui.theme.MediWarning

private val allAlerts = listOf(
    StockAlert(1, "Estoque baixo — Soro Fisiológico 500ml", "há 10 min", AlertType.LOW_STOCK),
    StockAlert(2, "Vencimento próximo — Luvas P (caixa)", "há 1h", AlertType.EXPIRING),
    StockAlert(3, "Atraso — Pedido #0041 MedSupply", "há 2h", AlertType.DELAY),
    StockAlert(4, "Estoque zerado — Omeprazol 20mg", "há 3h", AlertType.LOW_STOCK),
    StockAlert(5, "Estoque baixo — Máscara Cirúrgica", "há 4h", AlertType.LOW_STOCK),
    StockAlert(6, "Pedido #0039 confirmado — ForneceMed", "há 5h", AlertType.INFO),
    StockAlert(7, "Vencimento próximo — Soro Glicosado", "há 6h", AlertType.EXPIRING)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertasScreen(navController: NavController) {
    Scaffold(
        containerColor = MediBackground,
        topBar = {
            TopAppBar(
                title = { Text("Alertas", color = Color.White, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MediSurface)
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item { Spacer(modifier = Modifier.height(8.dp)) }
            items(allAlerts) { alert ->
                AlertCard(alert)
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

@Composable
private fun AlertCard(alert: StockAlert) {
    val alertColor = when (alert.type) {
        AlertType.LOW_STOCK -> MediError
        AlertType.EXPIRING -> MediWarning
        AlertType.DELAY -> MediBlue
        AlertType.INFO -> MediSubtext
    }
    val typeLabel = when (alert.type) {
        AlertType.LOW_STOCK -> "Estoque Baixo"
        AlertType.EXPIRING -> "Vencimento"
        AlertType.DELAY -> "Atraso"
        AlertType.INFO -> "Informação"
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(MediCardBg)
            .padding(14.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(alertColor.copy(alpha = 0.2f)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = when (alert.type) {
                    AlertType.INFO -> "i"
                    else -> "!"
                },
                color = alertColor,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(3.dp)) {
            Text(text = alert.title, fontSize = 13.sp, color = Color.White, fontWeight = FontWeight.Medium)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(alertColor.copy(alpha = 0.15f))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(text = typeLabel, fontSize = 10.sp, color = alertColor)
                }
                Text(text = alert.timeAgo, fontSize = 11.sp, color = MediSubtext)
            }
        }
    }
}
