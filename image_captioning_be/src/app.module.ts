import { Module } from '@nestjs/common';
import { InfersModule } from './infers/infers.module';

@Module({
  imports: [InfersModule],
})
export class AppModule {}
