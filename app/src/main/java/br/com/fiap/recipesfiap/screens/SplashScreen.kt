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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.recipesfiap.navigation.Destination
import br.com.fiap.recipesfiap.ui.theme.*

@Composable
fun SplashScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MediBackground)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(48.dp))

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = "MediStock Icon",
                tint = MediPrimary,
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "MediStock",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = MediOnBackground
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Gestão inteligente de insumos hospitalares com IA",
                fontSize = 14.sp,
                color = MediOnSurface,
                textAlign = TextAlign.Center
            )
        }

        Column(modifier = Modifier.fillMaxWidth()) {
            FeatureRow(dotColor = MediSecondary, text = "Monitoramento de estoque em tempo real")
            Spacer(modifier = Modifier.height(12.dp))
            FeatureRow(dotColor = MediSecondary, text = "Previsão de demanda com inteligência artificial")
            Spacer(modifier = Modifier.height(12.dp))
            FeatureRow(dotColor = MediSuccess, text = "Rastreamento de entregas e alertas automáticos")
        }

        Column(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = { navController.navigate(Destination.Login.route) },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MediPrimary),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Entrar", color = Color.White, fontWeight = FontWeight.SemiBold)
            }
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedButton(
                onClick = { navController.navigate(Destination.Login.route) },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = MediPrimary)
            ) {
                Text("Saiba mais", fontWeight = FontWeight.SemiBold)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "v1.0 · Seguro e conforme com a LGPD",
                fontSize = 11.sp,
                color = MediOnSurface,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun FeatureRow(dotColor: Color, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(10.dp)
                .background(dotColor, shape = RoundedCornerShape(50))
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(text = text, color = MediOnSurface, fontSize = 14.sp)
    }
}
