package com.geosilent.ai.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.geosilent.ai.domain.*

@Composable fun AppNavHost(nav: NavHostController) { NavHost(nav, "dashboard") { composable("dashboard") { Dashboard(nav) }; composable("rules") { Rules(nav) }; composable("history") { History() }; composable("settings") { Settings() } } }

@Composable private fun Dashboard(nav: NavHostController, vm: RulesViewModel = hiltViewModel()) { val rules by vm.rules.collectAsState(); Scaffold(bottomBar={ NavigationBar { listOf("dashboard" to "Home", "rules" to "Rules", "history" to "History", "settings" to "Settings").forEach { (route,label)-> NavigationBarItem(selected=false,onClick={nav.navigate(route)},icon={},label={Text(label)}) } } }) { p -> LazyColumn(Modifier.padding(p).padding(20.dp), verticalArrangement=Arrangement.spacedBy(16.dp)) { item { Text("GeoSilent AI", style=MaterialTheme.typography.headlineMedium); Text("Automation that respects your context.") }; item { Card { Column(Modifier.padding(18.dp)) { Text("Automation", style=MaterialTheme.typography.titleLarge); Text("${rules.count { it.enabled }} active rules"); Spacer(Modifier.height(12.dp)); Button(onClick={nav.navigate("rules")}) { Text("Manage rules") } } } }; item { Text("Recent activity", style=MaterialTheme.typography.titleMedium); Text("No executions yet. Your activity will appear here.") } } } }

@Composable private fun Rules(nav: NavHostController, vm: RulesViewModel = hiltViewModel()) { val rules by vm.rules.collectAsState(); Scaffold(topBar={TopAppBar({Text("Rules")})}, floatingActionButton={FloatingActionButton(onClick={vm.addSample()}){Text("+")}}) { p -> LazyColumn(Modifier.padding(p).padding(16.dp), verticalArrangement=Arrangement.spacedBy(10.dp)) { items(rules) { r -> Card { Row(Modifier.fillMaxWidth().padding(16.dp), horizontalArrangement=Arrangement.SpaceBetween) { Column(Modifier.weight(1f)){Text(r.name, style=MaterialTheme.typography.titleMedium);Text("${r.triggerType.name.lowercase().replace('_',' ')} → ${r.action.name.lowercase()}")} Switch(checked=r.enabled,onCheckedChange={vm.setEnabled(r.id,it)}) } } } } } }
@Composable private fun History() { Column(Modifier.padding(20.dp)){Text("History", style=MaterialTheme.typography.headlineMedium); Spacer(Modifier.height(12.dp)); Text("Execution history will appear here.")} }
@Composable private fun Settings() { Column(Modifier.padding(20.dp)){Text("Settings", style=MaterialTheme.typography.headlineMedium); Spacer(Modifier.height(12.dp)); Text("Permissions, notifications, theme, and default restore behavior.")} }
