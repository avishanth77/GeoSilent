package com.geosilent.ai.di

import com.geosilent.ai.data.InMemoryRuleRepository
import com.geosilent.ai.domain.RuleRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module @InstallIn(SingletonComponent::class)
abstract class AppModule { @Binds @Singleton abstract fun bindRuleRepository(repo: InMemoryRuleRepository): RuleRepository }
