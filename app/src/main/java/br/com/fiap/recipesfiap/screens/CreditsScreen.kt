package br.com.fiap.recipesfiap.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.recipesfiap.ui.theme.*

@Composable
fun CreditsScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MediBackground)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.CheckCircle,
            contentDescription = null,
            tint = MediPrimary,
            modifier = Modifier.size(64.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text("MediStock", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = MediOnBackground)
        Text("Gestão inteligente de insumos hospitalares", fontSize = 13.sp, color = MediOnSurface, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(32.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MediCard, RoundedCornerShape(12.dp))
                .padding(24.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                Text("Desenvolvido por:", fontSize = 14.sp, color = MediOnSurface)
                Spacer(modifier = Modifier.height(12.dp))
                Text("Luana Estanislau", fontSize = 18.sp, fontWeight = FontWeight.SemiBold, color = MediPrimaryVariant)
                Spacer(modifier = Modifier.height(24.dp))
                HorizontalDivider(color = MediSurface)
                Spacer(modifier = Modifier.height(16.dp))
                Text("Ano: 2026", fontSize = 14.sp, color = MediOnSurface)
                Spacer(modifier = Modifier.height(8.dp))
                Text("FIAP — Faculdade de Informática e Administração Paulista", fontSize = 12.sp, color = MediOnSurface.copy(alpha = 0.7f), textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.height(8.dp))
                Text("v1.0 · LGPD Compliant", fontSize = 11.sp, color = MediOnSurface.copy(alpha = 0.5f))
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        OutlinedButton(
            onClick = { navController.popBackStack() },
            colors = ButtonDefaults.outlinedButtonColors(contentColor = MediPrimary),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Voltar")
        }
    }
}
