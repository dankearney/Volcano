import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CardEditPageComponent } from './card-edit-page.component';

describe('CardEditPageComponent', () => {
  let component: CardEditPageComponent;
  let fixture: ComponentFixture<CardEditPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CardEditPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CardEditPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
