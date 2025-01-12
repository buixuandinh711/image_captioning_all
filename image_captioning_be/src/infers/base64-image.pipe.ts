import {
  ArgumentMetadata,
  BadRequestException,
  Injectable,
  PipeTransform,
} from '@nestjs/common';
import * as sharp from 'sharp';

export type ImageRgbData = {
  shape: [number, number, number];
  data: number[][][];
};

@Injectable()
export class Base64ImagePipe implements PipeTransform {
  // eslint-disable-next-line @typescript-eslint/no-unused-vars
  async transform(value: unknown, metadata: ArgumentMetadata) {
    if (typeof value !== 'string') {
      throw new BadRequestException('Invalid string data');
    }

    const imageBuf = getBufferFromBase64(value);

    if (imageBuf == null) {
      throw new BadRequestException('Invalid base64 string data');
    }

    try {
      const imageRgbData = await toRgbData(imageBuf);
      return imageRgbData;
    } catch (error) {
      if ('message' in error) {
        throw new BadRequestException(
          `Failed to convert image to RGB with: ${error.message}`,
        );
      }

      throw new BadRequestException('Failed to convert image to RGB');
    }
  }
}

const getBufferFromBase64 = (base64Str: string): Buffer | null => {
  try {
    const buf = Buffer.from(base64Str, 'base64');
    return buf;
    // eslint-disable-next-line @typescript-eslint/no-unused-vars
  } catch (error) {
    return null;
  }
};

const toRgbData = async (imageBuffer: Buffer): Promise<ImageRgbData> => {
  const imageSharp = sharp(imageBuffer);

  const metadata = await imageSharp.metadata();
  const { width, height } = metadata;

  if (width == undefined || height == undefined) {
    throw new Error('Invalid image metadata');
  }

  const data = await imageSharp.removeAlpha().raw().toBuffer();

  const rgbArray: number[][][] = [];

  for (let i = 0; i < height; i++) {
    const row: number[][] = [];

    for (let j = 0; j < width; j++) {
      const pixelIndex = i * width * 3 + j * 3;

      row.push([data[pixelIndex], data[pixelIndex + 1], data[pixelIndex + 2]]);
    }

    rgbArray.push(row);
  }

  return {
    shape: [width, height, 3],
    data: rgbArray,
  };
};
