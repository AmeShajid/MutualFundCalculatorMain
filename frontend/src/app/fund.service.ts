import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Fund {
  symbol: string;
  name: string;
}

export interface PredictionRequest {
  ticker: string;
  principal: number;
  years: number;
}

export interface PredictionResponse {
  futureValue: number;
  beta: number;
  expectedReturn: number;
  riskFreeRate: number;
}

@Injectable({ providedIn: 'root' })
export class FundService {
  private baseUrl = '/api';

  constructor(private http: HttpClient) {}

  getFunds(): Observable<Fund[]> {
    return this.http.get<Fund[]>(`${this.baseUrl}/funds`);
  }

  predict(request: PredictionRequest): Observable<PredictionResponse> {
    return this.http.post<PredictionResponse>(`${this.baseUrl}/predict`, request);
  }
}
