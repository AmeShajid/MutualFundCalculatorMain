import { TestBed } from '@angular/core/testing';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of, throwError, Observable } from 'rxjs';
import { AppComponent } from './app.component';
import { FundService, Fund, PredictionResponse } from './fund.service';

const MOCK_FUNDS: Fund[] = [
  { symbol: 'VFINX', name: 'Vanguard 500 Index Fund' },
  { symbol: 'FXAIX', name: 'Fidelity 500 Index Fund' }
];

const MOCK_RESULT: PredictionResponse = {
  futureValue: 12500,
  beta: 1.05,
  expectedReturn: 0.085,
  riskFreeRate: 0.045
};

describe('AppComponent', () => {
  let fundServiceSpy: jasmine.SpyObj<FundService>;

  beforeEach(async () => {
    fundServiceSpy = jasmine.createSpyObj('FundService', ['getFunds', 'predict']);
    fundServiceSpy.getFunds.and.returnValue(of(MOCK_FUNDS));
    fundServiceSpy.predict.and.returnValue(of(MOCK_RESULT));

    await TestBed.configureTestingModule({
      declarations: [AppComponent],
      imports: [CommonModule, FormsModule, HttpClientTestingModule],
      providers: [{ provide: FundService, useValue: fundServiceSpy }]
    }).compileComponents();
  });

  it('should create the app', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    expect(app).toBeTruthy();
  });

  it('should render the page title', () => {
    const fixture = TestBed.createComponent(AppComponent);
    fixture.detectChanges();
    const compiled = fixture.nativeElement as HTMLElement;
    expect(compiled.querySelector('h1')?.textContent).toContain('Mutual Fund Investment Calculator');
  });

  it('should load funds on init', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    fixture.detectChanges();
    expect(fundServiceSpy.getFunds).toHaveBeenCalledTimes(1);
    expect(app.funds).toEqual(MOCK_FUNDS);
    expect(app.fundsLoading).toBeFalse();
  });

  it('should show error when fund loading fails', () => {
    fundServiceSpy.getFunds.and.returnValue(throwError(() => new Error('network')));
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    fixture.detectChanges();
    expect(app.error).toBe('Failed to load funds. Is the backend running?');
    expect(app.fundsLoading).toBeFalse();
  });

  it('formValid should be false when fields are incomplete', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    fixture.detectChanges();
    expect(app.formValid).toBeFalse();
    app.selectedTicker = 'VFINX';
    app.principal = 10000;
    expect(app.formValid).toBeFalse();
    app.years = 5;
    expect(app.formValid).toBeTrue();
  });

  it('predict() should call FundService with correct payload and set result', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    fixture.detectChanges();

    app.selectedTicker = 'VFINX';
    app.principal = 10000;
    app.years = 5;
    app.predict();

    expect(fundServiceSpy.predict).toHaveBeenCalledWith({ ticker: 'VFINX', principal: 10000, years: 5 });
    expect(app.result).toEqual(MOCK_RESULT);
    expect(app.loading).toBeFalse();
    expect(app.submittedPrincipal).toBe(10000);
    expect(app.submittedYears).toBe(5);
  });

  it('predict() should set loading to true while the request is in flight', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    fixture.detectChanges();

    let resolveSubject!: (v: PredictionResponse) => void;
    const pending$ = new Promise<PredictionResponse>(res => { resolveSubject = res; });
    fundServiceSpy.predict.and.returnValue(
      new Observable((observer: any) => {
        pending$.then(v => { observer.next(v); observer.complete(); });
      })
    );

    app.selectedTicker = 'VFINX';
    app.principal = 10000;
    app.years = 5;
    app.predict();

    expect(app.loading).toBeTrue();
    expect(app.result).toBeNull();
    resolveSubject(MOCK_RESULT);
  });

  it('predict() should display a string error message on failure', () => {
    fundServiceSpy.predict.and.returnValue(throwError(() => ({ error: 'Bad request' })));
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    fixture.detectChanges();

    app.selectedTicker = 'VFINX';
    app.principal = 10000;
    app.years = 5;
    app.predict();

    expect(app.error).toBe('Bad request');
    expect(app.loading).toBeFalse();
  });

  it('predict() should extract message from error object', () => {
    fundServiceSpy.predict.and.returnValue(throwError(() => ({ error: { message: 'Fund not found' } })));
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    fixture.detectChanges();

    app.selectedTicker = 'VFINX';
    app.principal = 10000;
    app.years = 5;
    app.predict();

    expect(app.error).toBe('Fund not found');
  });

  it('reset() should clear result and error', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    fixture.detectChanges();

    app.selectedTicker = 'VFINX';
    app.principal = 10000;
    app.years = 5;
    app.predict();

    expect(app.result).toBeTruthy();
    app.reset();
    expect(app.result).toBeNull();
    expect(app.error).toBe('');
  });
});
