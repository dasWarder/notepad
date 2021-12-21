import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TodayBarComponent } from './today-bar.component';

describe('TodayBarComponent', () => {
  let component: TodayBarComponent;
  let fixture: ComponentFixture<TodayBarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TodayBarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TodayBarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
