package br.com.fiap.recipesfiap.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.recipesfiap.model.AlertItem
import br.com.fiap.recipesfiap.model.Delivery
import br.com.fiap.recipesfiap.navigation.Destination
import br.com.fiap.recipesfiap.ui.theme.*

data class NavItem(val label: String, val icon: ImageVector, val route: String)

val bottomNavItems = listOf(
    NavItem("Home", Icons.Default.Home, Destination.Dashboard.route),
    NavItem("Estoque", Icons.Default.List, Destination.Estoque.route),
    NavItem("IA", Icons.Default.Star, Destination.IA.route),
    NavItem("Logística", Icons.Default.LocalShipping, Destination.Logistica.route),
    NavItem("Alertas", Icons.Default.Notifications, Destination.Alertas.route)
)

@Composable
fun DashboardScreen(navController: NavController, currentTab: String = "home") {
    Scaffold(
        containerColor = MediBackground,
        bottomBar = {
            NavigationBar(containerColor = MediSurface) {
                bottomNavItems.forEach { item ->
                    val selected = when (currentTab) {
                        "home" -> item.route == Destination.Dashboard.route
                        "estoque" -> item.route == Destination.Estoque.route
                        "alertas" -> item.route == Destination.Alertas.route
                        "ia" -> item.route == Destination.IA.route
                        "logistica" -> item.route == Destination.Logistica.route
                        "pedido" -> item.route == Destination.Pedido.route
                        else -> false
                    }
                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            if (item.route != navController.currentDestination?.route) {
                                navController.navigate(item.route) {
                                    popUpTo(Destination.Dashboard.route) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        },
                        icon = { Icon(item.icon, contentDescription = item.label) },
                        label = { Text(item.label, fontSize = 10.sp) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MediPrimary,
                            selectedTextColor = MediPrimary,
                            unselectedIconColor = MediOnSurface,
                            unselectedTextColor = MediOnSurface,
                            indicatorColor = MediCard
                        )
                    )
                }
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when (currentTab) {
                "home" -> HomeTabContent(navController)
                "estoque" -> EstoqueScreen()
                "alertas" -> AlertasScreen()
                "ia" -> IAScreen()
                "logistica" -> LogisticaScreen()
                "pedido" -> PedidoScreen()
                else -> HomeTabContent(navController)
            }
        }
    }
}

@Composable
fun HomeTabContent(navController: NavController) {
    val sampleAlerts = listOf(
        AlertItem(1, "Estoque baixo — Soro Fisiológico", "Nível abaixo do mínimo", "há 10 min", "WARNING"),
        AlertItem(2, "Vencimento próximo — Luvas P", "Vencimento em 3 dias", "há 1h", "WARNING"),
        AlertItem(3, "Atraso — Pedido #0041", "Fornecedor MedSupply com atraso", "há 2h", "CRITICAL")
    )
    val sampleDeliveries = listOf(
        Delivery(1, "#0039", "ForneceMed", "Hoje 14h", "IN_ROUTE"),
        Delivery(2, "#0041", "MedSupply", "Atrasado", "DELAYED")
    )
    val barHeights = listOf(60, 90, 45, 110, 75, 130, 95)
    val barDays = listOf("Seg", "Ter", "Qua", "Qui", "Sex", "Sáb", "Dom")

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MediBackground)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text("MediStock", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = MediOnBackground)
            Text("Dashboard", fontSize = 14.sp, color = MediOnSurface)
        }
        item {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                StatCard(modifier = Modifier.weight(1f), title = "Estoque crítico", value = "8", label = "Crítico", valueColor = MediError)
                StatCard(modifier = Modifier.weight(1f), title = "Entregas hoje", value = "5", label = "Em rota", valueColor = MediSecondary)
            }
        }
        item {
            Text("Alertas recentes", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = MediOnBackground)
        }
        items(sampleAlerts) { alert ->
            AlertCard(alert)
        }
        item {
            Text("Previsão de demanda — 7 dias", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = MediOnBackground)
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MediCard, RoundedCornerShape(12.dp))
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.Bottom
                ) {
                    barHeights.forEachIndexed { index, height ->
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Box(
                                modifier = Modifier
                                    .width(28.dp)
                                    .height(height.dp)
                                    .background(MediPrimary, RoundedCornerShape(4.dp))
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(barDays[index], fontSize = 10.sp, color = MediOnSurface)
                        }
                    }
                }
            }
        }
        item {
            Text("Entregas em andamento", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = MediOnBackground)
        }
        items(sampleDeliveries) { delivery ->
            DeliveryCard(delivery)
        }
        item { Spacer(modifier = Modifier.height(8.dp)) }
    }
}

@Composable
fun StatCard(modifier: Modifier = Modifier, title: String, value: String, label: String, valueColor: Color) {
    Box(
        modifier = modifier
            .background(MediCard, RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        Column {
            Text(title, fontSize = 12.sp, color = MediOnSurface)
            Spacer(modifier = Modifier.height(4.dp))
            Text(value, fontSize = 28.sp, fontWeight = FontWeight.Bold, color = valueColor)
            Text(label, fontSize = 12.sp, color = valueColor)
        }
    }
}

@Composable
fun AlertCard(alert: AlertItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MediCard, RoundedCornerShape(10.dp))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(32.dp)
                .background(MediWarning.copy(alpha = 0.2f), RoundedCornerShape(50)),
            contentAlignment = Alignment.Center
        ) {
            Text("i", color = MediWarning, fontWeight = FontWeight.Bold, fontSize = 14.sp)
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(alert.title, fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = MediOnBackground)
            Text(alert.timeAgo, fontSize = 11.sp, color = MediOnSurface)
        }
    }
}

@Composable
fun DeliveryCard(delivery: Delivery) {
    val statusColor = if (delivery.status == "DELAYED") MediError else MediSuccess
    val statusLabel = if (delivery.status == "DELAYED") "Atrasado" else "Em rota"
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MediCard, RoundedCornerShape(10.dp))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text("Pedido ${delivery.orderNumber}", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = MediOnBackground)
            Text(delivery.supplier, fontSize = 11.sp, color = MediOnSurface)
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
