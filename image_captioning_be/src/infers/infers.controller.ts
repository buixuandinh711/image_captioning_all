import {
  Controller,
  Post,
  Body,
  InternalServerErrorException,
} from '@nestjs/common';
import { InfersService } from './infers.service';
import { Base64ImagePipe, ImageRgbData } from 'src/infers/base64-image.pipe';

@Controller('infers')
export class InfersController {
  constructor(private readonly infersService: InfersService) {}

  @Post('captioning')
  async getCaption(@Body('image', Base64ImagePipe) image: ImageRgbData) {
    try {
      const inferResult = await this.infersService.inferCaptioning(image);
      return inferResult;
    } catch (error) {
      if ('message' in error) {
        throw new InternalServerErrorException(
          `Failed to infer captioning with: ${error.message}`,
        );
      }

      throw new InternalServerErrorException('Failed to infer captioning');
    }
  }
}
