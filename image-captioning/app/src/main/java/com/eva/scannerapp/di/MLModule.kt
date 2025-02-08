package com.eva.scannerapp.di

import com.google.mlkit.vision.label.ImageLabeler
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MLModule {

	@get:Provides
	@Singleton
	val labelerOptions: ImageLabelerOptions = ImageLabelerOptions.Builder()
		.setConfidenceThreshold(.7f)
		.build()

	@Provides
	@Singleton
	fun providesImageLabeler(options: ImageLabelerOptions): ImageLabeler =
		ImageLabeling.getClient(options)

}