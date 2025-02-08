import { Module } from '@nestjs/common';
import { InfersService } from './infers.service';
import { InfersController } from './infers.controller';
import 'dotenv/config';

@Module({
  controllers: [InfersController],
  providers: [
    {
      provide: InfersService,
      useFactory: () =>
        new InfersService(
          process.env.TRITON_REST_URL ?? 'http://127.0.0.1:8000',
          process.env.CAPTIONING_MODEL ?? 'git',
        ),
    },
  ],
})
export class InfersModule {}
