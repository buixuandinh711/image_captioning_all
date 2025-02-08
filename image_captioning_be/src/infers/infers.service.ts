import { Injectable } from '@nestjs/common';
import { ImageRgbData } from 'src/infers/base64-image.pipe';

@Injectable()
export class InfersService {
  private readonly tritonUrl: string;
  private readonly captioningModel: string;

  constructor(tritonUrl: string, captioningModel: string) {
    this.tritonUrl = tritonUrl;
    this.captioningModel = captioningModel;
  }

  async inferCaptioning(image: ImageRgbData) {
    const inferResult = await this.submitInfer(this.captioningModel, [
      {
        name: 'image',
        datatype: 'FP32',
        shape: image.shape,
        data: image.data,
      },
    ]);

    return inferResult.outputs[0].data;
  }

  private async submitInfer(model: string, inputs: any[]): Promise<any> {
    const myHeaders = new Headers();
    myHeaders.append('Content-Type', 'application/json');

    const raw = JSON.stringify({
      inputs,
    });

    const requestOptions = {
      method: 'POST',
      headers: myHeaders,
      body: raw,
    };

    const res = await fetch(
      `${this.tritonUrl}/v2/models/${model}/infer`,
      requestOptions,
    );

    if (!res.ok) {
      throw new Error(
        `Call inference failed with status: ${res.status}, message: ${await res.text()}`,
      );
    }

    const data = await res.json();

    return data;
  }
}
