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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
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
import br.com.fiap.recipesfiap.ui.theme.MediBackground
import br.com.fiap.recipesfiap.ui.theme.MediBlue
import br.com.fiap.recipesfiap.ui.theme.MediCardBg
import br.com.fiap.recipesfiap.ui.theme.MediError
import br.com.fiap.recipesfiap.ui.theme.MediPrimary
import br.com.fiap.recipesfiap.ui.theme.MediSubtext
import br.com.fiap.recipesfiap.ui.theme.MediSuccess
import br.com.fiap.recipesfiap.ui.theme.MediSurface
import br.com.fiap.recipesfiap.ui.theme.MediWarning

data class PedidoItem(
    val id: String,
    val supplier: String,
    val items: Int,
    val totalValue: String,
    val status: String,
    val date: String
)

private val mockPedidos = listOf(
    PedidoItem("#P-2026-041", "MedSupply", 5, "R$ 3.200,00", "Atrasado", "28/04/2026"),
    PedidoItem("#P-2026-040", "PharmaBR", 12, "R$ 7.850,00", "Aprovado", "27/04/2026"),
    PedidoItem("#P-2026-039", "ForneceMed", 8, "R$ 4.100,00", "Em rota", "26/04/2026"),
    PedidoItem("#P-2026-038", "MedTech", 3, "R$ 1.500,00", "Entregue", "20/04/2026"),
    PedidoItem("#P-2026-037", "ChemLab", 6, "R$ 2.300,00", "Entregue", "15/04/2026"),
    PedidoItem("#P-2026-036", "ForneceMed", 10, "R$ 5.600,00", "Cancelado", "10/04/2026")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PedidoScreen(navController: NavController) {
    Scaffold(
        containerColor = MediBackground,
        topBar = {
            TopAppBar(
                title = { Text("Pedidos", color = Color.White, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MediSurface)
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                containerColor = MediPrimary
            ) {
                Icon(Icons.Default.Add, contentDescription = "Novo pedido", tint = Color.White)
            }
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
            items(mockPedidos) { pedido ->
                PedidoCard(pedido)
            }
            item { Spacer(modifier = Modifier.height(80.dp)) }
        }
    }
}

@Composable
private fun PedidoCard(pedido: PedidoItem) {
    val statusColor = when (pedido.status) {
        "Atrasado" -> MediError
        "Aprovado" -> MediBlue
        "Em rota" -> MediBlue
        "Entregue" -> MediSuccess
        "Cancelado" -> MediWarning
        else -> MediSubtext
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(MediCardBg)
            .padding(14.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${pedido.id} — ${pedido.supplier}",
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .background(statusColor.copy(alpha = 0.2f))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(text = pedido.status, fontSize = 11.sp, color = statusColor, fontWeight = FontWeight.Bold)
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "${pedido.items} itens · ${pedido.totalValue}", fontSize = 12.sp, color = MediSubtext)
            Text(text = pedido.date, fontSize = 11.sp, color = MediSubtext)
        }
    }
}
