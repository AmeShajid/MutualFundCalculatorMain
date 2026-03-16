import { Component, OnInit } from '@angular/core';
import { FundService, Fund, PredictionResponse } from './fund.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  standalone: false,
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
  funds: Fund[] = [];
  selectedTicker = '';
  principal: number | null = null;
  years: number | null = null;

  result: PredictionResponse | null = null;
  loading = false;
  error = '';
  fundsLoading = true;
  showMethodology = false;
  submittedPrincipal: number | null = null;
  submittedYears: number | null = null;

  constructor(private fundService: FundService) {}

  ngOnInit(): void {
    this.fundService.getFunds().subscribe({
      next: (funds) => {
        this.funds = funds;
        this.fundsLoading = false;
      },
      error: () => {
        this.error = 'Failed to load funds. Is the backend running?';
        this.fundsLoading = false;
      }
    });
  }

  get selectedFundName(): string {
    const fund = this.funds.find(f => f.symbol === this.selectedTicker);
    return fund ? fund.name : '';
  }

  get formValid(): boolean {
    return !!this.selectedTicker
      && this.principal !== null && this.principal > 0
      && this.years !== null && this.years > 0;
  }

  predict(): void {
    if (!this.formValid) return;
    this.loading = true;
    this.error = '';
    this.result = null;
    this.submittedPrincipal = this.principal;
    this.submittedYears = this.years;

    this.fundService.predict({
      ticker: this.selectedTicker,
      principal: this.principal!,
      years: this.years!
    }).subscribe({
      next: (res) => {
        this.result = res;
        this.loading = false;
      },
      error: (err) => {
        const raw = err.error;
        this.error = typeof raw === 'string' ? raw : raw?.message || err.message || 'Prediction failed. Please try again.';
        this.loading = false;
      }
    });
  }

  reset(): void {
    this.result = null;
    this.error = '';
  }

  get growthPercent(): number {
    if (!this.result || !this.submittedPrincipal) return 0;
    return ((this.result.futureValue - this.submittedPrincipal) / this.submittedPrincipal) * 100;
  }

  formatCurrency(value: number): string {
    return new Intl.NumberFormat('en-US', {
      style: 'currency',
      currency: 'USD',
      minimumFractionDigits: 2
    }).format(value);
  }

  formatPercent(value: number): string {
    return (value * 100).toFixed(2) + '%';
  }
}
